Pattern matching in Scala is a natural and intuitive way of expressing branched logic and can greatly simplify complex branched logic in terms of readability but what if you need to branch within a branch? This can result in unsightly code and duplication of logic. Let’s see this first hand with an example.

Let’s try to code a function removeAt(n, List) which removes the zero indexed nth element from the supplied list and returns the resulting list. Our first attempt, as we transcribe our thoughts step by step into code, may look as follows.

1
2
3
4
5
6
7
8
9
10
def removeAt[T](n: Int, xs: List[T]): List[T] = n match {
  case 0 => xs match {
    case Nil => Nil
    case y :: ys => removeAt(n - 1, ys)
  }
  case _ => xs match {
    case Nil => Nil
    case y :: ys => y :: removeAt(n - 1, ys)
  }
}
Here you can see that, although the logic is correct and reasonably easy to interpret, there is duplication of code and the function could be made even easier to read and a little shorter. On our second attempt we may try to extract the duplicate Nil => Nil cases outside of the match as below.

1
2
3
4
5
6
7
8
9
def removeAt[T](n: Int, xs: List[T]): List[T] =
  if (xs.isEmpty) Nil else n match {
    case 0 => xs match {
      case y :: ys => removeAt(n - 1, ys)
    }
    case _ => xs match {
      case y :: ys => y :: removeAt(n - 1, ys)
    }
  }
This also works and has in fact achieved our goals – the function is shorter and does not duplicate code. However, at this point, it is blending if/else logic with pattern matching and most importantly producing warnings in Scala IDE in Eclipse. The warnings state that in our secondary (internal) pattern match we do not cater for the Nil case and that’s correct.

Description	Resource	Path	Location	Type
match may not be exhaustive. It would fail on the following input: Nil	ListUtils.scala	/scala-test/src/week5	line 13	Scala Problem
match may not be exhaustive. It would fail on the following input: Nil	ListUtils.scala	/scala-test/src/week5	line 16	Scala Problem
Even though Scala IDE may not see that in the bigger picture the logic is actually correct it is correct in saying what it is saying specifically and I also consider it bad practice to leave warnings in code so let’s go through one more simplification attempt. How else can we restructure this function to achieve the same goals?

1
2
3
4
5
6
7
8
def removeAt[T](n: Int, xs: List[T]): List[T] =
  xs match {
    case Nil => Nil
    case y :: ys => n match {
      case 0 => removeAt(n - 1, ys)
      case _ => y :: removeAt(n - 1, ys)
    }
  }
The answer is to invert the order of the high level pattern match subjects by making the subject that’s being pattern matched redundantly rise to become the primary pattern match with the other subject then becoming secondary. And there we have the end result of the simplification.

Addendum

A couple of after thoughts for completion follow here. Firstly – the above progression in implementation strategies was chosen to allow illustration of how to incrementally simplify a function and does not suggest that one cannot in fact end up with the final solution the first time. Secondly – the implementation algorithm above which is needlessly verbose has also been chosen to be illustrative of nested pattern matching and isn’t the simplest and most concise possible solution. For that see below.

1
2
def removeAt[T](n: Int, xs: List[T]): List[T] =
  (xs take n) ::: (xs drop n+1)
