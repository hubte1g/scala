// A Thread takes a Runnable. You have to call `start` on a thread in order for it to run the Runnable.
// https://twitter.github.io/scala_school/concurrency.html

val hello = new Thread(new Runnable {
  def run() {
    println("hello world")
}
})  // hello: Thread = Thread[Thread-81,5,main]

/**
 * Something single-threaded
 **/

import java.net.{Socket, ServerSocket}
import java.util.concurrent.{Executors, ExecutorService}
import java.util.Date

class Handler(socket: Socket) extends Runnable {
  def message = (Thread.currentThread.getName() + "\n").getBytes

  def run() {
    socket.getOutputStream.write(message)
    socket.getOutputStream.close()
  }
}

class NetworkService(port: Int, poolSize: Int) extends Runnable {
  val serverSocket = new ServerSocket(port)

  def run() {
    while (true) {
      // This will block until a connection comes in.
      val socket = serverSocket.accept()
      (new Handler(socket)).run()
    }
  }
}

(new NetworkService(2020, 2)).run



