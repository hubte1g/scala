To express that a method
takes a set of its kind to as set
of its kind, that is an empty
set to an empty set and a nonempty
set to a nonempty set.

def <method>[SUBTYPE <: SomeSubType](set: SUBTYPE): SUBTYPE = ...


SUBTYPE 'of' SomeSubType

"<: SomeSubType" is an 'upper bound' of the type parameter SUBTYPE:

It means that SUBTYPE can be instantiated only to types that conform to SomeSubType.

General notation:

S <: SomeT means S is a subtype of T  -- 'upper bound'
S >: T means S is a supertype of T, or T is a subtype of S -- 'lower bound'

S >: below <: above-- 'mixed bounds'
