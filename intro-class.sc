// src/main/scala/progscala2/introscala/upper1.sc

scala> class Upper {
     | def upper(strings: String*): Seq[String] = {
     | strings.map((s:String) => s.toUpperCase())
     | }
     | }
defined class Upper

val up = new Upper

scala> class Lower {
     | def lower(strings:String*): Seq[String] = {
     | strings.map((s:String) => s. toLowerCase())
     | }
     | }
defined class Lower

scala> println(up.upper("Upper Test"))
ArrayBuffer(UPPER TEST)

scala> println(low.lower("Lower Test"))
ArrayBuffer(lower test)

