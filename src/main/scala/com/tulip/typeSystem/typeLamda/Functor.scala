package com.tulip.typeSystem.typeLamda

/**
 * Author: Tulip
 * Date: 2019/12/30 22:19
 */
trait Functor[A,+M[_]] {
  def map2[B](f: A => B): M[B]
}

object Functor {
  implicit class SeqFunctor[A](seq: Seq[A]) extends Functor[A,Seq] {
    override def map2[B](f: A => B): Seq[B] = seq.map(f)
  }

  implicit class OptionFunctor[A](opt: Option[A]) extends Functor[A,Option] {
    override def map2[B](f: A => B): Option[B] = opt.map(f)
  }

  implicit class MapFunctor[K,V1](mapKV1: Map[K,V1]) extends Functor[V1,({type r[a] = Map[K,a]})#r] {
    override def map2[B](f: V1 => B): Map[K, B] = mapKV1 map {
      case (k,v) => (k,f(v))
    }
  }

}

object AppFunc extends App {
  import com.tulip.typeSystem.typeLamda.Functor._
  println(List(1,2,3) map2 (_ * 2)) // List(2, 4, 6)
  println(Option(2) map2 (_ * 2)) // Some(4)
  val m = Map("one" -> 1, "two" -> 2, "three" -> 3)
  println(m map2 (_ * 2)) // Map(one -> 2, two -> 4, three -> 6)
}
