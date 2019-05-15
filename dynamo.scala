import com.amazonaws.services.dynamodbv2.document.{DynamoDB, Table, Item, DynamoDB}
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
