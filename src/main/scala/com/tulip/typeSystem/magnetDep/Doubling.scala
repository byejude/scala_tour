class Doubling {
  def double(magnet: DoubleMagnet): magnet.Result = magnet()
}

// Magnet Interface
trait DoubleMagnet {
  type Result

  def apply(): Result
}

// Implicit Conversions
object DoubleMagnet {

  implicit class fromInt(x: Int) extends DoubleMagnet {
    override type Result = Int

    override def apply(): Result = x * 2
  }

  implicit class fromListInt(ls: List[Int]) extends DoubleMagnet {
    override type Result = List[Int]

    override def apply(): Result = ls.map(_ * 2)
  }

  implicit class fromListString(ls: List[String]) extends DoubleMagnet {
    override type Result = List[String]

    override def apply(): Result = ls ++ ls
  }

  // overloading with different number of parameters
  implicit class fromStringIntTuple(para: Tuple2[String, Int]) extends DoubleMagnet {
    override type Result = String

    override def apply(): String = para._1 * para._2
  }

}

object App extends App {
  val doubling = new Doubling()
  println(doubling.double(2))
  println(doubling.double(List(1, 2, 3)))
  println(doubling.double(List("a", "b", "c")))
  println(doubling.double(("a", 5)))
}