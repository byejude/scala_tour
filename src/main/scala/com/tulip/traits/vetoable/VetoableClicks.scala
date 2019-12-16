package com.tulip.traits.vetoable

import com.tulip.traits.ui2.Clickable

/**
 * Author: Tulip
 * Date: 2019/12/16 20:05
 */
trait VetoableClicks extends Clickable{
  //默认的允许点击数
  val maxAllowed = 1
  private var count = 0
  abstract override def click(): Unit = {
    if (count < maxAllowed){
      println("its allowed "+count)
      super.click()
      count += 1
    }

  }
}
