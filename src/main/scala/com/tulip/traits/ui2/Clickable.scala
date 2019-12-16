package com.tulip.traits.ui2

/**
 * Author: Tulip
 * Date: 2019/12/16 19:24
 */
trait Clickable {

  protected def updateUI(): Unit

  def click()= updateUI()

}
