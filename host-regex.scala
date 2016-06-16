//groups of interest: bssg, dba, prodcntl, usa
/*
See cleansed file:
W:\IT\Vulnerability Management\PAG\Privileged Access Review Q2 2016\Unix\cleansedUnixHostFile.xlsx
Ran the process for groups: bssg, dba, prodcntl, usa
Columns in cleansed file:
Name, OS, Group, Version, ID, and True or False based if ID is in password column.
Note that if you see 'null' for ID that means that name/OS had no IDs.
*/

//This is different because virtual and partition fields are not in the file.

//create variables to be used:
//define variables in file with a new class. (all string type for this use case)
case class Host(Name: String, OS: String, pswd: String, grpName: String, ver: String, IDs: String)

//file to source
val txt = "Unix_Local_Privileged_Groups.txt"

//method defines regex pattern for file.  File is tab delimited.
//fileName: pass name of file to run pattern matching and grouping.
//regex is currently set up to find 'prodcntl' groupings.
def parseLogFile(fileName: String): Iterator[Host] = {
    val p = """([^\t]*)\t([^\t]*)\t([^\t]*)\t"(?:[^\t]*\n(usa):.:(\d*[^:]):(?:(.*[^\n|"])|[^"])[^\t]*|[^\t]*)\t*\W""".r
    p.findAllMatchIn(fileName).map(
        m => Host(m.group(1), m.group(2), m.group(3), "Group: " + m.group(4), "ver: " + m.group(5), "IDs:," + m.group(6))
    )
}

//set path to get file(s) and map through log file method.
val groupedRecords = sc.wholeTextFiles("/kohls/eim/lab/ers/host").flatMap{case (_, txt) => parseLogFile(txt)}

//map over the RDD and split out ID column to be an array of IDs.
val nestedIDs = groupedRecords.collect.map { x =>  (x.Name, x.OS, x.pswd, x.grpName, x.ver, x.IDs.split(",")) }

//filter for prodcntl group only.  This will filter out any groups that have null.  
val filteredGroup = nestedIDs.filter(x => x._4.contains("usa"))

//printing function to write to file.
import java.io._
def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
  val p = new java.io.PrintWriter(f)
  try { op(p) } finally { p.close() }}

//cleaner printing function
printToFile(new File("/home/tkmatd3/hostClean_usa.txt"))(p => { 
	var j = 0; 
	var i = 0;  
	while ( j < filteredGroup.length) { 
		i = 1; 
		val oneRecord = Array(filteredGroup(j)); 
		while ( i < oneRecord(0)._6.length) { 
			oneRecord.map{ x  => x._1 + "," + x._2 + "," + x._4 + "," + x._5 + "," + x._6(i) + "," + x._3.contains(x._6(i)) }.foreach(p.println);
			 i += 1; 
		}; 
		j += 1; 
	}
})
