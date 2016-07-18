//#11 Avoid mutable local Variables

/* Writing code in a purely functional style, without using side-effecting operations.
 * You can often rewrite code that uses mutable local variables
 * to code with helper functions that take accumulators.
 */

//Instead of:
def fib(n: Int): Int = {
  var a = 0
  var b = 1
  var i = 0
  while (i < n) {
    val prev_a = a
    a = b
    b = prev_a + b
    i = i + 1
  }
  a
}

// prefer:
def fib(n: Int): Int = {
  def fibIter(i: Int, a: Int, b: Int): Int =
    if (i == n) a else fibIter(i+1, b, a+b)
  fibIter(0, 0, 1)
}
