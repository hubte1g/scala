UPDATE SEABORN / NONLOCAL KNOWLEDGE OF THE TREE / 'ACCIDENTALLY WELL-ROUNDED'

There was a quadratic explosion of class-
Ification and access methods.

To express that a method
takes a set of its kind to as set
of its kind, that is an empty
set to an empty set and a nonempty
set to a nonempty set.

Leaving arrays covariant in Java
Produced a hole in the type system,

Which had to be patched by a run-time
Check. And you might ask why, and

It has to do with the desire to sort
an object array for any type. This

Was solved by creating generic types.

def <method>[SUBTYPE <: SomeSubType](set: SUBTYPE): SUBTYPE = ...


SUBTYPE 'of' SomeSubType

"<: SomeSubType" is an 'upper bound' of the type parameter SUBTYPE:

It means that SUBTYPE can be instantiated only to types that conform to SomeSubType.

General notation:

S <: SomeT means S is a subtype of T  -- 'upper bound'
S >: T means S is a supertype of T, or T is a subtype of S -- 'lower bound'

S >: below <: above-- 'mixed bounds'
