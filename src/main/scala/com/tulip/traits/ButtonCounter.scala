package com.tulip.traits

/**
 * Author: Tulip
 * Date: 2019/12/14 23:14
 */

class ButtonCountObserver extends Observer[Button] {
  var count = 0

  def receiveUpdate(state: Button): Unit = {
    println("I receive it! and count!")
    count += 1
  }
}

object ButtonCounter {
  def main(args: Array[String]): Unit = {
    val button = new ObservableButton("Click Me!")
    val bco1 = new ButtonCountObserver
    val bco2 = new ButtonCountObserver

    button.addObserver(bco1)
    button.addObserver(bco2)

    (1 to 5) foreach (_ => button.click())

    println(require(bco1.count == 5, s"its counted 5 times"))
    println(assert(bco2.count == 5))
  }
}
