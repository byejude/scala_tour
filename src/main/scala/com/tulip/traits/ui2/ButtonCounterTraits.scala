package com.tulip.traits.ui2


import com.tulip.traits._


/**
 * Author: Tulip
 * Date: 2019/12/16 19:35
 */
class ClickCounterObserver extends Observer[Clickable] {
  var count = 0

  override def receiveUpdate(state: Clickable): Unit = {
    println("receiveUpdate !")
    count += 1
  }
}

object ButtonCounterTraits {
  def main(args: Array[String]): Unit = {
    val button = new ButtonTraits("Click ME! and notify") with ObservableClicks
    val button2 = new ButtonTraits("Click ME!")
    val counter = new ClickCounterObserver

    button.addObserver(counter)
    (1 to 5) foreach (_ => button.click())
    println(counter.count)
    (1 to 5) foreach (_ => button2.click())
  }
}
