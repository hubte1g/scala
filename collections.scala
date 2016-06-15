scala> ((1 to 10) fold "") ((s1, s2) => s"$s1 - $s2")
res2: Any = " - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10"



scala> (1 to 25).toArray.foldLeft(0)(_ + _)
res8: Int = 325

