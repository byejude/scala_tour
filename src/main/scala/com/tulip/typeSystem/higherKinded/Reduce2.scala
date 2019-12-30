package com.tulip.typeSystem.higherKinded

/**
 * Author: Tulip
 * Date: 2019/12/30 21:33
 */
trait Reduce1[-M[_]] {
  def reduce[T](m: M[T])(f: (T, T) => T): T
}
object Reduce1 {
  implicit val seqReduce = new Reduce1[Seq] {
    def reduce[T](seq: Seq[T])(f: (T, T) => T): T = seq reduce f
  }
  implicit val optionReduce = new Reduce1[Option] {
    def reduce[T](opt: Option[T])(f: (T, T) => T): T = opt reduce f
  }
}

object App1 extends App{
  def sumTrait[T: Add, M[_]](container:M[T])(implicit reduce: Reduce[M,T]):T =
    reduce.reduce(container)(implicitly[Add[T]].add(_,_))

  println(sumTrait(Vector(1 -> 10, 2 -> 20, 3 -> 30)))
  println(sumTrait(1 to 10) )// 结果值: 55
  println(sumTrait(Option(2))) // 结果值: 2
  //sumTrait[Int,Option](None)
}