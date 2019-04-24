// Median function

def findMedian(vs: Iterable[(Int,Int)]): Int = {
    def s = vs.unzip._2.toArray.sorted
    val mode: Int = s.length / 2
    if (s.length % 2 == 0) {
      (s(mode) + s(mode - 1)) / 2
    } else {
      s(mode)
    }
  }

//interpolated strings -- use an Int expression in a Float context (pads with zeroes)

val i = 200
f"${i}%.2f

//instance method called 'format', called on the format string itself , then passed as arguments the values to be incorporated into the string. Asks for two digit integer padded with leading zeroes
val j = "%02d: name = %s".format(5, "Joshua Lickteig")
j: String = 05: name = Joshua Lickteig

val name = "Joshua Lickteig"
s"123\n$name\n456"

res2: String =
123
Joshua Lickteig
456

//use raw format option that doesn't expand control characters
raw"123\n$name\n456"
res3: String = 123\nJoshua Lickteig\n456


// Trailing commas, periods, and operators indicate more code on the next line.

def commas(s1: String,
           s2: String) = Console.
    println("comma: " + s1 + ", " + s2)

// if-then
val x = 7

if ( x > 5 ) {
     | println( "x = " + x + " is greater than 5")
     | } else {
     | println("x = " + x + " is less than 5")
     | }
     
x = 7 is greater than 5

// match

val x = 2; val y = 4
val result = (x + y) match {
           case 5 => " this is five"
           case 6 => " this is six"
           case 7 => {" this is seven"}
println(result)
}

// for loop
for (i <- 1 to 20 by 3 if i % 2 == 0) {  // i mod to (only equal to even)
           println(i)
}

// for loop
int i; 
for (i = 0; i < 10; i++) {} 
int x = i;

// function

def computeSum (x: Int, y: Int) : Int = {
           println("Input values" + x + "and" + y)
           return x+y
           }
println("Result is" + computeSum(2,4)) // computeSum: (x: Int, y: Int)Int
