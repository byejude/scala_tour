package com.tulip.traits.ui2

import com.tulip.traits.Widget

/**
 * Author: Tulip
 * Date: 2019/12/16 19:29
 */
class ButtonTraits(val label: String) extends Widget with Clickable {
  override protected def updateUI(): Unit = {
    println("its updateUI")
  }
}
