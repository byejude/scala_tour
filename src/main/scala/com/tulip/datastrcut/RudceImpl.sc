//todo  以下俩个例子考虑递归的方向
def reduceLeft[A, B](s: Seq[A])(f: A => B): Seq[B] = {
  @annotation.tailrec
  def rl(accum: Seq[B], s2: Seq[A]): Seq[B] = s2 match {
    case head +: tail => {
      println("accum prc" + accum)
      rl(accum :+ f(head), tail)
    }
    case _ => {
      println("accum" + accum)
      accum
    }
  }

  rl(Seq.empty[B], s)
}
//def reduceRight[A,B](s: Seq[A])(f: A => B): Seq[B] = s match {
//  case head +: tail => f(head) +: reduceRight(tail)(f)
//  case _ => Seq.empty[B]
//}

def reduceRight[A, B](s: Seq[A])(f: A => B): Seq[B] = {
  //@annotation.tailrec
  //todo 尾递归要保证整个函数循环调用只是本身
  def rr(accum: Seq[B], s2: Seq[A]): Seq[B] = s2 match {
    case head +: tail => f(head) +: reduceRight(tail)(f)
    case _ => Seq.empty[B]
  }

  rr(Seq.empty[B], s)
}
val list = List(1, 2, 3, 4, 5, 6)
reduceLeft(list)(i => 2 * i)
// => List(2, 4, 6, 8, 10, 12)
reduceRight(list)(i => 2 * i)
// => List(2, 4, 6, 8, 10, 12)