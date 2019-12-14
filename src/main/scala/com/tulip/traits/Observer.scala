package com.tulip.traits

/**
 * Author: Tulip
 * Date: 2019/12/14 23:09
 */
trait Observer[-State] {                                             // <1>
  def receiveUpdate(state: State): Unit
}

trait Subject[State] {                                               // <2>
  private var observers: List[Observer[State]] = Nil                 // <3>

  def addObserver(observer:Observer[State]): Unit =                  // <4>
    observers ::= observer                                           // <5>

  def notifyObservers(state: State): Unit =                          // <6>
    observers foreach (_.receiveUpdate(state))
}
