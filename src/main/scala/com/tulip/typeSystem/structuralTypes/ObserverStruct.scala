package com.tulip.typeSystem.structuralTypes

/**
 * Author: Tulip
 * Date: 2019/12/28 17:33
 */

import scala.language.reflectiveCalls

object ObserverStruct {
  //todo 方法名要符合SubjectStruct中结构化类名的方法名 后续需要使用反射来写
  def receiveUpdate(state: Any): Unit = println("got one!" + state)

  def main(args: Array[String]): Unit = {
    val subject = new SubjectStruct {
      override type State = Int
      protected var count = 0

      def increment(): Unit = {
        count += 1
        notifyObservers(count)
      }
    }

    subject.increment()
    subject.increment()
    subject.addObserver(ObserverStruct)
    subject.increment()
    subject.increment()
  }
}

