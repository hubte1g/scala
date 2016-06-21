//p45
import scala.annotation.tailrec
@tailrec
 def fibonacci(i: Int): Long = {
  if (i <= 1) 1L
  else fibonacci(i-2) + fibonacci(i-1)
 }







