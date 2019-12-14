package com.tulip.traits

/**
 * Author: Tulip
 * Date: 2019/12/14 23:13
 */
class ObservableButton(name: String)                                 // <1>
  extends Button(name) with Subject[Button] {                      // <2>

  override def click(): Unit = {                                     // <3>
    super.click()                                                    // <4>
    notifyObservers(this)                                            // <5>
  }
}
