object <object name> {
   implicit class <class name>(<Variable>: Data type) {
      def <method>(): Unit =
   }
}

// Objects are used to hold single instances of a class. Often used for factories.

object Timer {
   var count = 0
   
   def currentCount(): Long = {
      count += 1
      count
   }
} // defined module Timer

Timer.currentCount() // res10: Long = 1

