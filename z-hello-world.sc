/** A Scala Hello World! Application
  *	
  * This application also includes a method that squares each 
  * element of an integer collection.
  * 
  * @author AUTHOR
  *
  */
	

object Scala_HelloWorld {
	def main(args: Array[String]) = {
		println("Hello world!")
		val squared_array = square_elements(Array(1,2,3,4,5))
		///val squares_of_squared_array = square_of_squared_elements(squared_array)
		// print out the squared elements of the array
		squared_array.foreach(println)
		///squares_of_squared_array foreach println
	}
        
	/** Square each element of an array of integers
	  * @param x = array of integers to square
	  * @return squared array of integers
	  */
	def square_elements(x:  Array[Int]):  Array[Int] = {
		x.map(x => x * x)
	}
	/*def square_of_squared_elements(m: Array[Int]): Array[Int] = {
	  squared_array.map(m => m * m)
	}*/
}	
