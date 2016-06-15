scala> ((1 to 10) fold "") ((s1, s2) => s"$s1 - $s2")
res2: Any = " - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10"

scala> (1 to 25).toArray.foldLeft(0)(_ + _)
res8: Int = 325

//non-idiomatic
scala> ('A' to 'Z').toSet.union(('a' to 'b').toSet).union(('0' to '9').toSet)
res17: scala.collection.immutable.Set[Char] = Set(E, X, 8, 4, 9, N, T, Y, J, U, F, A, a, 5, M, I, G, 6, 1, V, Q, L, b, B, P, 0, 2, C, H, W, 7, K, R, 3, O, D, Z, S)

//idiomatic
scala> ('a' to 'z').toSet ++ ('A' to 'Z') ++ ('0' to '9')
res18: scala.collection.immutable.Set[Char] = Set(E, e, X, s, x, 8, 4, n, 9, N, j, y, T, Y, t, J, u, U, f, F, A, a, 5, m, M, I, i, v, G, 6, 1, V, q, Q, L, b, g, B, l, P, p, 0, 2, C, H, c, W, h, 7, r, K, w, R, 3, k, O, D, Z, o, z, S, d)


