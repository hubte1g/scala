import com.amazonaws.services.dynamodbv2.document.{DynamoDB, Table, Item}
import com.amazonaws.services.dynamodbv2.model._
import com.amazonaws.services.dynamodbv2.{AmazonDynamoDB, AmazonDynamoDBClient, AmazonDynamoDBClientBuilder}

def dynamoDB: AmazonDynamoDB = {
    val client = new AmazonDynamoDBClient()
    client.setEndpoint("https://dynamodb.us-east-1.amazonaws.com")
    client
  }

  //dynamoDB.describeTable("table_name")

  import scala.collection.JavaConverters._

//  val key = Map(
//    "source_id" -> new AttributeValue().withS("999999999"),
//    "source_cd" -> new AttributeValue().withS("category")
//  ).asJava
//  val identifier_map = Map("#language" -> "language").asJava
//  //val clientConfig = new ClientConfiguration()
//  val client = AmazonDynamoDBClientBuilder.standard().build()
//  val request = new GetItemRequest().withTableName("table_name").withKey(key)//.withProjectionExpression(retrnCols).withExpressionAttributeNames(identifier_map)
//  val response = client.getItem(request)
//  val s = response.getItem()


// Builder approach

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.xspec.ExpressionSpecBuilder.S
import com.amazonaws.services.dynamodbv2.xspec.{ExpressionSpecBuilder, UpdateItemExpressionSpec}
import com.amazonaws.services.dynamodbv2.document.{DynamoDB, Item}

def dynamoClient(awsProfile: String) = {
      val dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient())
      dynamoDB
      }

//dynamoClient: (awsProfile: String)com.amazonaws.services.dynamodbv2.document.DynamoDB



// Get table item count
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
    import com.amazonaws.services.dynamodbv2.document.DynamoDB
    val client = AmazonDynamoDBClientBuilder.standard().build()

    val dynamoDB = new DynamoDB(client)
    val tableDescription = dynamoDB.getTable("table name").describe().getItemCount()


// Batch get item

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.{BatchGetItemOutcome, DynamoDB, TableKeysAndAttributes}
import com.amazonaws.services.dynamodbv2.model.{AttributeValue, KeysAndAttributes}
import org.apache.spark.sql.Row

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

val client = AmazonDynamoDBClientBuilder.standard.build
val dynamoDB = new DynamoDB(client)

val tableName = "a_table"

val _columns = List("c1","c2").asJava

val forumTableKeysAndAttributes = new TableKeysAndAttributes(tableName)

val lkpList = Array("hkVal1", "rkVal1", "hkVal2", "rkVal2", "hkVal3", "rkVal3")

forumTableKeysAndAttributes.withHashAndRangeKeys(
  "hk_id","rk_cd").withAttributeNames(
  identity_columns).addHashAndRangePrimaryKeys(
  "hk_id", "rk_cd",
  lkpList:_*
)

//val threadTableKeysAndAttributes = new TableKeysAndAttributes("another_table")
//threadTableKeysAndAttributes.addHashAndRangePrimaryKeys("ForumName", "Subject", "Amazon DynamoDB", "DynamoDB Thread 1", "Amazon DynamoDB", "DynamoDB Thread 2", "Amazon S3", "S3 Thread 1")

val outcome = dynamoDB.batchGetItem(forumTableKeysAndAttributes)
outcome.getTableItems.keySet

//https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/JavaDocumentAPIItemCRUD.html
//https://github.com/aws/aws-sdk-java/blob/master/src/samples/AmazonDynamoDBDocumentAPI/quick-start/com/amazonaws/services/dynamodbv2/document/quickstart/H_BatchGetItemTest.java




//Relevant for using https://github.com/seratch/AWScala/blob/master/src/main/scala/awscala/dynamodbv2/AttributeValue.scala

import com.amazonaws.services.dynamodbv2.model.AttributeValue
def toJavaValue(v: Any): AttributeValue = {
    val value = new AttributeValue
    v match {
      case null => null
      case s: String => value.withS(s)
      case n: java.lang.Number => value.withN(n.toString)
      case b: ByteBuffer => value.withB(b)
      case xs: Seq[_] => xs.headOption match {
        case Some(s: String) => value.withSS(xs.map(_.asInstanceOf[String]).asJava)
        case Some(n: java.lang.Number) => value.withSS(xs.map(_.toString).asJava)
        case Some(s: ByteBuffer) => value.withBS(xs.map(_.asInstanceOf[ByteBuffer]).asJava)
        case Some(v) => value.withSS(xs.map(_.toString).asJava)
        case _ => null
      }
      case _ => null
    }
  }
