val joshua = Person("Josh", 33)
val joshua2 = Person("Josh2", 15)
val persons = joshua :: joshua2 :: Nil
for ( p <- persons if p.age > 20 ) yield p.name
