package com.tulip.fp

/**
 * Author: Tulip
 * Date: 2019/12/31 21:10
 */
trait FunctorFp[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object SeqF extends FunctorFp[Seq] {
  override def map[A, B](fa: Seq[A])(f: A => B): Seq[B] = fa.map(f)
}

object OptionF extends FunctorFp[Option] {
  override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
}

object FunctorFpObj {
  def map[A, A2, B](func: A => A2)(f: A2 => B): A => B = {
    val functorFp = new FunctorFp[({type r[q] = A => q})#r] {
      override def map[A3, B](fa: A => A3)(f: A3 => B): A => B = (a: A) => f(fa(a))
    }
    functorFp.map(func)(f)
  }
}

object AppFunctorFp extends App {
  val fii: Int => Int = i => i * 2
  val fid: Int => Double = i => 2.1 * i
  val fds: Double => String = d => d.toString
  SeqF.map(List(1, 2, 3, 4))(fii) // Seq[Int]: List(2, 4, 6, 8)
  SeqF.map(List.empty[Int])(fii) // Seq[Int]: List()
  OptionF.map(Some(2))(fii) // Option[Int]: Some(4)
  OptionF.map(Option.empty[Int])(fii) // Option[Int]: None
  val fa = FunctorFpObj.map(fid)(fds)
  fa(2) // String: 4.2
  // val fb = FunctionF.map(fid)(fds)
  val fb = FunctorFpObj.map[Int, Double, String](fid)(fds)
  fb(2)
  val fc = fds compose fid
  fc(2) // String: 4.2
}