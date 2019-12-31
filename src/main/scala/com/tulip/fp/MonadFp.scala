package com.tulip.fp

/**
 * Author: Tulip
 * Date: 2019/12/31 22:30
 */
trait MonadFp[M[_]] {
  def flatMap[A, B](fa: M[A])(f: A => M[B]): M[B]

  def unit[A](a: => A): M[A]

  def bind[A, B](fa: M[A])(f: A => M[B]): M[B] = flatMap(fa)(f)

  def >>=[A, B](fa: M[A])(f: A => M[B]): M[B] = flatMap(fa)(f)

  def pure[A](a: => A): M[A] = unit(a)

  def `return`[A](a: => A): M[A] = unit(a) // 添加反引号，避免与关键字冲突
}

object SeqM extends MonadFp[Seq] {
  def flatMap[A, B](seq: Seq[A])(f: A => Seq[B]): Seq[B] = seq flatMap f

  def unit[A](a: => A): Seq[A] = Seq(a)
}

object OptionM extends MonadFp[Option] {
  def flatMap[A, B](opt: Option[A])(f: A => Option[B]): Option[B] = opt flatMap f

  def unit[A](a: => A): Option[A] = Option(a)
}

object AppMonadFp extends App {
  val seqf: Int => Seq[Int] = i => 1 to i
  val optf: Int => Option[Int] = i => Option(i + 1)
  println(SeqM.flatMap(List(1, 2, 3))(seqf)) // Seq[Int]: List(1,1,2,1,2,3)
  println(SeqM.flatMap(List.empty[Int])(seqf)) // Seq[Int]: List()
  println(OptionM.flatMap(Some(2))(optf)) // Option[Int]: Some(3)
  println(OptionM.flatMap(Option.empty[Int])(optf)) // Option[Int]: None
  println(SeqM.unit(List(1, 2, 3)))
}


