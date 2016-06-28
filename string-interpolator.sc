val name = ("Buck", "Trends")
println(s"Hello, ${name._1} ${name._2}")


scala> StringContext("Hello","ll","oo").s(name._1, name._2)
res28: String = HelloBuckllTrendsoo


scala> StringContext("Hello","ll","oo")
res29: StringContext = StringContext(WrappedArray(Hello, ll, oo))


//  Build a json interpolator  //src/main/scala/progscala2/implicits/custom-string-interpolator.sc

import scala.util.parsing.json._

object Interpolators {
  implicit class jsonForStringContext(val sc: StringContext) {
    def json(values: Any*): JSONObject = {
      val keyRE = """^[\s{,]*(\S+):\s*""".r
      val keys = sc.parts map {
        case keyRE(key) => key
        case str => str
      }
      val kvs = keys zip values
      JSONObject(kvs.toMap)
    }
  }
}

import Interpolators._

val name = "Ernest Hemingway"
val book = "Islands in the Stream"

val jsonobj = json"{name: $name, book: $book}"

println(jsonobj)


// zip method on collections is handy way to line up values between two collections, like a zipper
val keys = List("a", "b", "c", "d") // keys: List[String] = List(a, b, c, d)
val values = List("A", 123, 3.14159) // values: List[Any] = List(A, 123, 3.14159)

val keysValues = keys zip values // keysValues: List[(String, Any)] = List((a,A), (b,123), (c,3.14159))




