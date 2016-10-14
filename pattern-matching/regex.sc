val p = """(\d\d\d\d)-(\d\d)-(\d\d)""".r
Option("2014-08-07") match {
  case Some(p(year,month,day)) => println("Yay")
  case None => println("Boo")
}

// but it's really better to do the two-step thing if there's any way to get a non-string in there. In particular, if you don't know it's an Option[String] but just an Any, figuring out that it's a string is an important separate step. You can write an extractor for it:

object IsString {
  def unapply(a: Any): Option[String] = a match {
    case s: String => Some(s)
    case _ => None
  }
}
// Then Some(IsString(p(y,m,d)))
// If you don't need to validate the three groups that you match, p(_*) will just test for the presence of the pattern.
