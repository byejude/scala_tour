package com.tulip.traits.ui2

import com.tulip.traits.Subject

/**
 * Author: Tulip
 * Date: 2019/12/16 19:33
 */
trait ObservableClicks extends Clickable with Subject[Clickable]{
  //TODO abstract 关键字提醒编译器（和读者）：尽管 ObservableClicks.click方法提供了方法体，但 click 方法并没有完全实现。
  abstract override def click(): Unit = {
    super.click()
    notifyObservers(this)
  }

  /**
   * TODO
   * 只有在满足下面条件的情况下，我们才应在 trait 中定义的某一方法之前添加
   * abstract 关键字：该方法调用了 super 对象中的另一个方法，但是被调用的
   * 这个方法在该 trait 的父类中尚未定义具体的实现方法。
   *
   * */
}
