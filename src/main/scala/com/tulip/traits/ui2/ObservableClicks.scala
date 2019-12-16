package com.tulip.traits.ui2

import com.tulip.traits.Subject

/**
 * Author: Tulip
 * Date: 2019/12/16 19:33
 */
trait ObservableClicks extends Clickable with Subject[Clickable]{
  override def click(): Unit = {
    super.click()
    notifyObservers(this)
  }
}
