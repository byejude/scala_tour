val nonEmptySeq = Seq(1, 2, 3, 4, 5)
val emptySeq = Seq.empty[Int]
val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList = Nil
val nonEmptyVector = Vector(1, 2, 3, 4, 5)
val emptyVector = Vector.empty[Int]
val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
val emptyMap = Map.empty[String, Int]

def seqToString[T](seq: Seq[T]): String = seq match {
  //+: 元头列尾
  //  case head +: tail => {
  //    println(s"head is $head and tail is $tail")
  //    s"$head +: " + seqToString(tail)
  //  }

  //:+ 列头元尾
  // case head :+ tail => {
  //   println(s"head is $head and tail is $tail")
  //   s"$tail +: " + seqToString(head)
  // }

  //函数表达
  case +:(head, tail) => {
    println(s"head is $head and tail is $tail")
    s"$head +: " + seqToString(tail)
  }
  case Nil => "Nil"
}
for (seq <- Seq(
  nonEmptySeq, emptySeq, nonEmptyList, emptyList,
  nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq)) {
  println(seqToString(seq))
}