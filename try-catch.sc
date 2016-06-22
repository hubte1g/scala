
// method that throws exception
def throwsException() {
  //throw new IllegalStateException("Exception thrown");
  throw new IllegalArgumentException("illegal arg. exception")
  }
  
//throwsException: ()Unit

// try
try{
    throwsException();
    println("this line is never executed");
} catch {
  case e: Exception => println("exception caught: " + e);
  case e: IllegalArgumentException => println("illegal arg. exception");
  case e: IllegalStateException    => println("illegal state exception");
  //case e: IOException              => println("IO exception");
}

//exception caught: java.lang.IllegalStateException: Exception thrown

try {
    throwsException();
} finally {
    println("this code is always executed");
}
--------------------------

def throwF() {

throw new ObjF

//http://tutorials.jenkov.com/scala/exception-try-catch-finally.html



object ObjVal {
   implicit class ObjValClass(f: String) {
      def fMethod(): Unit =
   }
}




object ObjVal extends Enumeration {
 type ObjVal = s"Fs thrown"
 val f = "F thrown"
 
 }
 import ObjVal._
 
 //def main(args: Array[String]) 
 //val loc = new Location(10, 20, 15);






---------------------------
object ObjValFail extends Enumeration {
 type ObjValFail = Value
 val f = Value("F thrown")
 }
 import ObjF._
 
 throw new ObjValFail
 scala> throw new ObjValFail
<console>:25: error: class Value is abstract; cannot be instantiated
              throw new ObjValFail
                    ^
