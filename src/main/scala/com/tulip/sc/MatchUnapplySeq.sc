val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList = Nil
val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)

def windows[T](seq: Seq[T]): String = seq match {
  //Seq(a, b, _*) 其中a,b表示Seq中规定筛选出来的元素，_*表示后续截断的Seq
  case Seq(head1, head2, _*) =>
    s"($head1, $head2), " + windows(seq.tail)
  case Seq(head, _*) =>
    s"($head, _), " + windows(seq.tail)
  case Nil => "Nil"
}
for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
  println(windows(seq))
}