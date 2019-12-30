package com.tulip.typeSystem.higherKinded

/**
 * Author: Tulip
 * Date: 2019/12/30 20:31
 */
trait Add[T] {
  def add(t1: T, t2: T): T
}

object Add {
  implicit val addInt = new Add[Int] {
    override def add(t1: Int, t2: Int): Int = t1 + t2
  }

  implicit val addIntIntPair = new Add[(Int, Int)] {
    override def add(t1: (Int, Int), t2: (Int, Int)): (Int, Int) =
      (t1._1 + t2._1, t1._2 + t2._2)
  }
}

object App extends App {
  def sumSeq[T: Add](seq: Seq[T]): T = // ➋
    seq reduce (implicitly[Add[T]].add(_, _))

  println(sumSeq(Vector(1 -> 10, 2 -> 20, 3 -> 30))) // 结果值: (6,60)
  println(sumSeq(1 to 10)) // 结果值: 55
  // sumSeq(Option(2))
}


