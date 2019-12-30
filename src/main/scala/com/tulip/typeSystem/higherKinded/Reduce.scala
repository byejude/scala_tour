package com.tulip.typeSystem.higherKinded

/**
 * Author: Tulip
 * Date: 2019/12/30 20:49
 */
trait Reduce[-M[T],T] {
  def reduce(m:M[T])(f: (T,T) => T): T
}

object Reduce{
  implicit def seqReduce[T] = new Reduce[Seq,T] {
    override def reduce(m: Seq[T])(f: (T, T) => T): T = m.reduce(f)
  }

  implicit def optionReduce[T] = new Reduce[Option,T] {
    override def reduce(m: Option[T])(f: (T, T) => T): T = m.reduce(f)
  }
}

object AppTrait extends App{
  def sumTrait[T: Add, M[T]](container:M[T])(implicit reduce: Reduce[M,T]):T =
    reduce.reduce(container)(implicitly[Add[T]].add(_,_))

  println(sumTrait(Vector(1 -> 10, 2 -> 20, 3 -> 30)))
  println(sumTrait(1 to 10) )// 结果值: 55
  println(sumTrait(Option(2))) // 结果值: 2
  //sumTrait[Int,Option](None)
}
