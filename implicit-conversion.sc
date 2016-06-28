// p. 149 "Progrmamming Scala"

// Create a pair, several ways


//// Literal idiom

(1, "one") // res8: (Int, String) = (1,one)

scala> 1 -> "one" // res7: (Int, String) = (1,one)

Tuple2(1, "one") // res9: (Int, String) = (1,one)


//// Literal idiom for Map: method called expects a variable argument list of pairs; def apply[A,B](elems: (A,B)*): Map[A,B]

Map("one" -> 1, "two" -> 2, "three" -> 3) // res12: scala.collection.immutable.Map[String,Int] = Map(one -> 1, two -> 2, three -> 3)

//// Scala's 'wrapper object' that has -> defined (because a -> b is not a literal syntax for tuples)

implicit final class ArrowAssoc[A](val self: A) {
  def -> [B](y: B): Tuple2[A,B] = Tuple2(self,y)
  } // could be used to create a Map

// Verbose
Map(new ArrowAssoc("one") -> 1, new ArrowAssoc("two") -> 2) // res15: scala.collection.immutable.Map[String,Int] = Map(one -> 1, two -> 2)

// Lean
Map( ("one", 1), ("two", 2)) // res16: scala.collection.immutable.Map[String,Int] = Map(one -> 1, two -> 2)
