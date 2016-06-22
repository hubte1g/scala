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



