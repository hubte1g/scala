//p45
import scala.annotation.tailrec
@tailrec
def fibonacci(i: Int): Long = {
  if (i <= 1) 1L
  else fibonacci(i-2) + fibonacci(i-1)
 }

<console>:23: error: could not optimize @tailrec annotated method fibonacci: it is neither private nor final so can be overridden
        def fibonacci(i: Int): Long = {...
        
        

// src/main/scala/progscala2/typelessdomore/count-to.sc
def countTo(n: Int): Unit = {
 def count(i: Int): Unit = {
  if (i <= n) {println(i); count(i + 1) }
 }
 count(1)
}


//p44 -- Nesting Method Definitions and Recursion
// src/main/scala/progscala2/typelessdomore/factorial.sc
def factorial (i: Int): Long = {
 def fact(i: Int, accumulator: Int): Long = {
  if ( i <= 1) accumulator
  else fact(i-1, i * accumulator)
 }
 
 fact(i,1)
}

(0 to 5) foreach ( i => println(factorial(i)) )




