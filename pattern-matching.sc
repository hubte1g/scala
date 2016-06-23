// Matching on values   →

val times = 1

times match {
  case 1 => "ONE"
  case 2 => "TWO"
  case 3 => "THREE"
  case _ => "OTHER/NA"
  } // res16: String = ONE

// Matching with guards

times match {
  case i if i == 1 => "ONE" // capture value in variable i
  case i if i == 2 => "TWO"
  case _ => "OTHER/NA" // _ is a wildcard and ensures that we can handle any statement. Runtime err if number passed that has no match.
  } // res18: String = ONE
  
// Matching on type (handling values of different types)

def bigger(o: Any): Any = {
  o match {
    case i: Int if i < 0 => i - 1
    case d: Double => d + .01
    case text: String => text + "s"
  }
} // bigger: (o: Any)Any


