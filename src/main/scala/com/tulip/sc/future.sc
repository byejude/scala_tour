import scala.util._
import concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def sleep(millis: Long) = {
  Thread.sleep(millis)
}

// Busy work
def doWork(index: Int) = {
  sleep((math.random * 1000).toLong)
  index
}

(1 to 5) foreach { index =>
  val future = Future {
    doWork(index)
  }
  future onComplete {
    case Success(x) =>  println(s"success and return is $x")
    case Failure(t) => println(s"An error has occured: $t"  )
  }
}

sleep(1000)  // Wait long enough for the "work" to finish.
println("Finito!")
