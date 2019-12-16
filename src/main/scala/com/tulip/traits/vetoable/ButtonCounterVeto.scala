package com.tulip.traits.vetoable

import com.tulip.traits._
import com.tulip.traits.ui2.{Clickable, ObservableClicks}

/**
 * Author: Tulip
 * Date: 2019/12/16 20:20
 */

class ClickCountObserver extends Observer[Clickable] {
  var count = 0

  override def receiveUpdate(state: Clickable): Unit = {
    println("receiveUpdate!")
    count += 1
  }
}

object ButtonCounterVeto {
  def main(args: Array[String]): Unit = {
    val button =
      new Button("Click Me!") with ObservableClicks with VetoableClicks {
        override val maxAllowed = 2
      }

    val bco1 = new ClickCountObserver
    val bco2 = new ClickCountObserver
    button addObserver bco1
    button addObserver bco2
    (1 to 5) foreach (_ => button.click())
    assert(bco1.count == 2, s"bco1.count ${bco1.count} != 2")
    assert(bco2.count == 2, s"bco2.count ${bco2.count} != 2")
    println("Success!")
  }
}
