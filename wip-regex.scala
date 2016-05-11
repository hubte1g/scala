package scala

import org.apache.spark.{SparkConf, SparkContext}

import scala.util.matching.Regex

/**
 * This application cleanses a sample
 * file from Tenable.  Regex is used to group
 * the valuable fields that can be used for
 * analysis.
 *
 * Author: Justin Mauss
 * Date Last Modified: 5/08/2016
 */
object test {
  //!!!for local machine run only
  System.setProperty("hadoop.home.dir", "c:\\winutils\\")

  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("sparking")
    val sc = new SparkContext(sparkConf)

    //val localFile = "C:\\Users\\TKMATD3\\Documents\\Spark_work\\sample\\2015_February_3sample.csv"
    val localFile = "/home/tkmatd3/demo/2015_February_3sample.csv"

    val pattern = """(?:"[^"]*"|[^,]*)\,""" //standard pattern for each field
    val lastCol = """(?:"[^"]*"|[^,]*)\n""" //pattern for last field in the list

    //columns that need date format logic applied
    val dateLogicNeeded = List("First Discovered", "Last Observed", "Mitigated On", "Vuln Publication Date",
                            "Patch Publication Date", "Plugin Publication Date", "Plugin Modification Date")
    //columns that need new lines replaced with spaces
    val flattenLogicNeeded = List("CPE", "Solution", "Plugin Text")

    //agreed columns to capture
    val agreedColumns = List("Plugin", "Plugin Name", "Family", "Severity", "IP Address", "Port", "Exploit?",
      "Repository", "MAC Address", "DNS Name", "NetBIOS Name", "Plugin Text",
      "CVSS Base Score", "CPE", "Solution", "First Discovered", "Last Observed",
      "Mitigated On", "Vuln Publication Date", "Patch Publication Date",
      "Plugin Publication Date", "Plugin Modification Date", "Exploit Ease",
      "Exploit Frameworks", "Tower")

    //open file to source
    val source = scala.io.Source.fromFile(localFile)
    //take first line of file and format for file's field list
    val towerSchema = try source.getLines.take(1).mkString.trim.stripPrefix(""""""").stripSuffix(""""""").split("""","""").toList finally source.close()

    //builds out regex string pattern based on num of columns
    //returns: completed regex string for matching
    def buildRegex(numOfCols: Integer): String = {
      var i = 1
      var regexPattern = ""
      while(i < numOfCols){
        regexPattern = regexPattern + pattern
        i = i + 1
      }
      regexPattern.concat(lastCol)
    }

    //set tenable format that we will be handling
    val tenableDateFormat = new java.text.SimpleDateFormat("MMM d, yyyy hh:mm:ss a")
    //format to whatever I want:
    implicit def str2date1(str: String) = {
      try {
        val dateFormat = tenableDateFormat.parse(str)
        new java.text.SimpleDateFormat("yyyy-MM-dd").format(dateFormat)
      } catch {
        case e:java.text.ParseException => "N/A".toString
      }
    }

    //opening source file again
    val dataSource = scala.io.Source.fromFile(localFile)
    //this time the whole file is put into a string and closed.
    val lines = try dataSource.mkString finally source.close()

    //get count of columns and create new regex pattern
    val colCount = towerSchema.length
    val p = new Regex(buildRegex(colCount))

    //make a list of containing each tenable record (defined by regex)
    val towerRecords = sc.parallelize(p.findAllIn(lines).toList).collect()
    //for each tenable record split the string to be an array of its columns
    val towerRecordArray = towerRecords.map( x => x.split("""","""")).toList
    //drop first element of top level list to remove column names
    val towerData = towerRecordArray.drop(1)

    //initialize two integer lists for storing indices that
    //need logic applied to them
    var dateLogic = List[Int]()
    var flattenLogic = List[Int]()

    //Loop through each towerSchema field and check if it is in approved column list.
    //Then check if logic needs to be done and store index in respective list.
    towerSchema.foreach{ column =>
      if(agreedColumns.contains(column))
        if (dateLogicNeeded.contains(column))
          dateLogic = towerSchema.indexOf(column) :: dateLogic
        else if (flattenLogicNeeded.contains(column))
          flattenLogic = towerSchema.indexOf(column) :: flattenLogic
    }

    for ( i <- 0 until towerData.length ) {
      for (j <- 0 until flattenLogic.length)
        towerData(i).update(flattenLogic(j), towerData(i)(flattenLogic(j)).replace("\n",","))
      for (h <- 0 until dateLogic.length)
        towerData(i).update(flattenLogic(h), towerData(i)(dateLogic(h)).str2date)
    }


    //use this on all
    towerData(0)(23).trim.stripSuffix(""""""")
    //Use this on all"
    towerData(0)(0).trim.stripPrefix(""""""")

    def search(target: String, arr: List[String]): List[(Int, Int)] = {
      val indices = for{
        (a, i) <- arr.iterator.zipWithIndex
        (c, j) <- a.iterator.zipWithIndex
        if( c == target )
      } yield i -> j
      indices.toList
    }

    sc.stop()
  }
}
