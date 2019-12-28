package com.tulip.typeSystem.structuralTypes

/**
 * Author: Tulip
 * Date: 2019/12/28 17:09
 */
trait SubjectStruct {

  import scala.language.reflectiveCalls

  type State

  type ObserverStruct = {
    def receiveUpdate(state: Any): Unit
  }

  private var observers: List[ObserverStruct] = Nil

  def addObserver(observer: ObserverStruct): Unit =
    observers ::= observer

  def notifyObservers(state: State): Unit =
    observers foreach (_.receiveUpdate(state))

}
