scala> (x: String) => (x.length < 5): Boolean
res5: String => Boolean = <function1>

scala> def myfun(s: String, f: String => Boolean): Unit = println(f(s))
myfun: (s: String, f: String => Boolean)Unit

scala> myfun("cat", (x: String) => (x.length < 5): Boolean)
true
