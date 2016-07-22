 // Queue - dequeue
 
  scala> import scala.collection.mutable.Queue                
  import scala.collection.mutable.Queue

  scala> val queue = new Queue[String]
  queue: scala.collection.mutable.Queue[String] = Queue()

  scala> queue += "a"

  scala> queue ++= List("b", "c")

  scala> queue
  res21: scala.collection.mutable.Queue[String] = Queue(a, b, c)

  scala> queue.dequeue
  res22: String = a

  scala> queue
  res23: scala.collection.mutable.Queue[String] = Queue(b, c)

// Stakc push - pop

  scala> import scala.collection.mutable.Stack
  import scala.collection.mutable.Stack

  scala> val stack = new Stack[Int]           
  stack: scala.collection.mutable.Stack[Int] = Stack()

  scala> stack.push(1)

  scala> stack
  res1: scala.collection.mutable.Stack[Int] = Stack(1)

  scala> stack.push(2)

  scala> stack
  res3: scala.collection.mutable.Stack[Int] = Stack(1, 2)

  scala> stack.top
  res8: Int = 2

  scala> stack
  res9: scala.collection.mutable.Stack[Int] = Stack(1, 2)

  scala> stack.pop    
  res10: Int = 2

  scala> stack    
  res11: scala.collection.mutable.Stack[Int] = Stack(1)
