object Doubler {


  def double(seq: Seq[_]): Seq[Int] = seq match {
    case Nil => Nil
    case head +: tail => (toInt(head) * 2) +: double(tail)
  }

  private def toInt(x: Any): Int = x match {
    case i: Int => i
    case s: String => s.toInt
    case x => throw new RuntimeException(s"Unexpected list element $x")
  }
}

object DoublerTest{
  def main(args: Array[String]): Unit = {
    val stringList = List("11", "55", "13", "22")
    println("test "+Doubler.double(stringList))
  }
}