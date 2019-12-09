package com.tulip.implicits.payroll

/**
 * Author: Tulip
 * Date: 2019/12/1 21:29
 */

object Pipeline {
  implicit class toPiped[V](value: V) {
     def flow [R] (f: V => R)= f(value)
  }
}

object CalculatePayroll2 {
  def main(args: Array[String]) = {
    import Pipeline._
    import Payroll._
    val e = Employee("Buck Trends", 100000.0F, 0.25F, 200F, 0.10F, 0.05F)
    val pay = start(e) flow
      minus401k flow
      minusInsurance flow
      minusTax flow
      minusFinalDeuctions
     // pay1 flow minus401k  == minus401k(pay1)
    //val xpay4 = minusFinalDeuctions(minusTax(minusInsurance(minus401k(start(e)))))
    val twoWeekGross = e.annualSalary / 26.0F
    val twoWeekNet = pay.netPay
    val percent = (twoWeekNet / twoWeekGross) * 100
    println(s"For ${e.name}, the gross vs. net pay every 2 weeks is:")
    println(
      f" $$${twoWeekGross}%.2f vs. $$${twoWeekNet}%.2f or ${percent}%.1f%%")
  }
}
