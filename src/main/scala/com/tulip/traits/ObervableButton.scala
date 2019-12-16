package com.tulip.traits

/**
 * Author: Tulip
 * Date: 2019/12/14 23:13
 */
class ObservableButton(name: String)
  extends Button(name) with Subject[Button] {

  override def click(): Unit = {
    super.click()
    notifyObservers(this)
  }
}
