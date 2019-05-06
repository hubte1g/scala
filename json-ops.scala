//DefineaverysimpleJSONAST -- source scala-with-cats.pdf

sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json


final case class JsString(get: String) extends Json

final case class JsNumber(get:Double) extends Json

case object JsNull extends Json

// "serialize to json" behavior encoded in trait
trait JsonWriter[A] {
     def write(value: A): Json
     }
