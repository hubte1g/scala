//
sealed abstract class Perhaps[A]
case class YesItIs[A](value: A) extends Perhaps[A]
case object Nope extends Perhaps[Nothing]
val y3 = YesItIs(3)
val y4 = YesItIs(4)
val n = Nope

//
sealed abstract class Perhaps[A] {
  def foreach(f: A => Unit): Unit
}
case class YesItIs[A](value: A) extends Perhaps[A] {
  override def foreach(f: A => Unit): Unit = f(value)
}

case object Nope extends Perhaps[Nothing] {
  override def foreach(f: Nothing => Unit): Unit = ()
}

//
sealed abstract class Perhaps[+A] {
  def foreach(f: A => Unit): Unit
  def map[B](f: A => B): Perhaps[B]
}
case class YesItIs[A](value: A) extends Perhaps[A] {
  override def foreach(f: A => Unit): Unit = f(value)
  override def map[B](f: A => B): Perhaps[B] = YesItIs(f(value))
}

case object Nope extends Perhaps[Nothing] {
  override def foreach(f: Nothing => Unit): Unit = ()
  override def map[B](f: Nothing => B): Perhaps[B] = this
}

//
y3.map(a => a * a)

//
sealed abstract class Perhaps[+A] {
  def foreach(f: A => Unit): Unit
  def map[B](f: A => B): Perhaps[B]
  def flatMap[B](f: A => Perhaps[B]): Perhaps[B]
}
case class YesItIs[A](value: A) extends Perhaps[A] {
  override def foreach(f: A => Unit): Unit = f(value)
  override def map[B](f: A => B): Perhaps[B] = YesItIs(f(value))
  override def flatMap[B](f: A => Perhaps[B]): Perhaps[B] = f(value)
}

case object Nope extends Perhaps[Nothing] {
  override def foreach(f: Nothing => Unit): Unit = ()
  override def map[B](f: Nothing => B): Perhaps[B] = this
  override def flatMap[B](f: Nothing => Perhaps[B]): Perhaps[B] =
    this
}

// . equiv to : y4.map(a => Try(100 / a).map(b => s"100/$a=$b"))
y3.flatMap(a => y4.map(b => a * b))

for {
  a <- y4 // YesItIs(4)
} yield for {
  b <- Try(100 / a)
} yield s"100/$a=$b"

//
sealed abstract class Perhaps[+A] {
  def foreach(f: A => Unit): Unit
  def map[B](f: A => B): Perhaps[B]
  def flatMap[B](f: A => Perhaps[B]): Perhaps[B]
  def withFilter(f: A => Boolean): Perhaps[A]
}
case class YesItIs[A](value: A) extends Perhaps[A] {
  override def foreach(f: A => Unit): Unit = f(value)
  override def map[B](f: A => B): Perhaps[B] = YesItIs(f(value))
  override def flatMap[B](f: A => Perhaps[B]): Perhaps[B] = f(value)
  override def withFilter(f: A => Boolean): Perhaps[A] = 
    if (f(value)) this else Nope
}

case object Nope extends Perhaps[Nothing] {
  override def foreach(f: Nothing => Unit): Unit = ()
  override def map[B](f: Nothing => B): Perhaps[B] = this
  override def flatMap[B](f: Nothing => Perhaps[B]): Perhaps[B] =   
    this
  override def withFilter(f: Nothing => Boolean): Perhaps[Nothing] =
    this
}

//
y3.withFilter(a => a > 1).flatMap(a => y4.map(b => a * b))
