package com.tulip.traits

/**
 * Author: Tulip
 * Date: 2019/12/14 23:10
 */
class Button(val label: String) extends Widget {
  //TODO 类中定义的方法不能被未继承traits重写
  def click(): Unit = updateUI()

  def updateUI(): Unit = {
    /* logic to change GUI appearance */
  }
}
