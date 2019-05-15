lazy val times: Stream[Long] = {
      def next(n: Long): Stream[Long] = n #:: next(n + 200l)
      next(System.currentTimeMillis())
      }
//times: Stream[Long] = <lazy>

lazy val times: Stream[Long] = 0 #:: System.currentTimeMillis #:: times.tail.map(_+1)
times.drop(1).take(2)
