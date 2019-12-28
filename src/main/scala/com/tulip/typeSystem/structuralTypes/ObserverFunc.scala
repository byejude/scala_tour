package com.tulip.typeSystem.structuralTypes

/**
 * Author: Tulip
 * Date: 2019/12/28 17:52
 */

import scala.language.reflectiveCalls

object ObserverFunc {
  val observer: Int => Unit = (state: Int) => println("got one!" + state)

  def main(args: Array[String]): Unit = {
    val subject = new SubjectFunc {
      type State = Int
      protected var count = 0

      def increment(): Unit = {
        count += 1
        notifyObservers(count)
      }
    }

    subject.increment()
    subject.increment()
    subject.addObserver(observer)
    subject.increment()
    subject.increment()
  }

}
