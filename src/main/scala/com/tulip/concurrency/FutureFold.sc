import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

val futures = (1 to 10) map {
  i =>
    Future {
      val s = i.toString
      println(s)
      s
    }
}

val f = Future.reduceLeft(futures)((s1, s2) => s1 + s2)
val n = Await.result(f, Duration.Inf)
println(n)