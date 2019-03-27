// json4s : https://riptutorial.com/scala/example/13543/json-with-json4s
val bodyJson = parse(body)
val records = (bodyJson \ "Records").extract[List[JValue]]

// src.main.scala.progscala2.implicits/toJSON-type-class.sc

case class Address(street: String, city: String)
case class Person(name: String, address: Address)

trait ToJSON {
  def toJSON(level: Int = 0): String
  
  val INDENTATION = "  "
  def indentation(level: Int = 0): (String,String) = 
    (INDENTATION * level, INDENTATION * (level+1))
}

implicit class AddressToJSON(address: Address) extends ToJSON {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}"
      |$outdent}""".stripMargin
    }
  }
  
implicit class PersonToJSON(person: Person) extends ToJSON {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"name":   "${person.name}",
      |${indent}"address": ${person.address.toJSON(level + 1)}
      |$outdent}""".stripMargin
    }
  }

val a = Address("1 Scala Lane", "Anytown")
val p = Person("Joshua Lichteig", a)

println(a.toJSON())
println()
println(p.toJSON())
