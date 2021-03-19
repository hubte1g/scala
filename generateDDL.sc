
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Row, SQLContext, SparkSession}
import org.apache.spark.sql.Row
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

val conf = new SparkConf()
conf.setMaster("local[*]").setAppName("Test App")
val sc = new SparkContext(conf)
val spark = SparkSession.builder.getOrCreate()
val sqlContext = new SQLContext(sc)
import spark.implicits._

import scala.io.Source
val dataPath = "/Users/src/test/resources/1539111862935-49588230703571866721589628290029539692074279100336832514"
val rawOrderRdd = sc.parallelize(Source.fromFile(dataPath).getLines().toSeq, 2)
val payloadDF = sqlContext.read.json(rawOrderRdd)
val order_header_formatted = payloadDF.transform(formatOrderHeaderRecords("ts1"))

def dataFrameToDDL(df: DataFrame, env: String, schema: String, tableName: String, partCol0: String, partCol1: String ): String = {
  val columns = df.schema.map { field =>
    "  `" + field.name + "` " + field.dataType.simpleString.toLowerCase + " COMMENT ''"
  }
  s"""CREATE EXTERNAL TABLE $tableName
     |(\n${columns.mkString(",\n")}\n)
     |PARTITIONED BY (
     |     `$partCol0` string COMMENT '',
     |     `$partCol1` string COMMENT '')
     |   ROW FORMAT SERDE
     |     'org.apache.hadoop.hive.ql.io.parquet.serde.ParquetHiveSerDe'
     |   STORED AS INPUTFORMAT
     |     'org.apache.hadoop.hive.ql.io.parquet.MapredParquetInputFormat'
     |   OUTPUTFORMAT
     |     'org.apache.hadoop.hive.ql.io.parquet.MapredParquetOutputFormat'
     |   LOCATION
     |     's3://digital-secure-stage-east/cde/$env/$schema/$tableName/'
     |   TBLPROPERTIES (
     |     'transient_lastDdlTime'='1541538180');
     |
   """.stripMargin
}

val a = dataFrameToDDL(st, "dev", "stg_schema", "test_table", "process_date", "event")
sc.parallelize(Seq(a)).coalesce(1).saveAsTextFile("ddl.txt")



