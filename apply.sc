class Thing {} // defined class Thing

object ThingMaker {
  def apply() = new Thing // apply: ()Thing // ALTERNATIVE: = 0
  }  // defined module ThingMaker
  
val newThingA = ThingMaker() // newThingA: Thing = $iwC$$iwC$Thing@70fe33fa
val newThingB = ThingMaker() // newThingB: Thing = $iwC$$iwC$Thing@70fe33fa

OR

class Thing {
  def apply() = 0 // apply: ()Int // ALTERNATIVE = new Thing
  } // defined class Thing

val newThingA = new Thing // newThingA: Thing = $iwC$$iwC$Thing@5d200dce

newThingA() // res1: Int = 0



  
  
