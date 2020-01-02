case class ThatsOdd(i: Int) extends RuntimeException(s"odd $i received！")

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

val doComplete: PartialFunction[Try[String], Unit] = {
  case s@Success(value) => println(s"its success! $value")
  case f@Failure(exception) => println(s"its failure! $exception")
}

val futures = (0 to 9) map {
  case i if i % 2 == 0 => Future.successful(i.toString)
  case i => Future.failed(ThatsOdd(i))
}

futures.map(f => f.onComplete(doComplete))