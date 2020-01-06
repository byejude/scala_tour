package com.tulip.dsls.payroll.internal

import scala.language.postfixOps
import com.tulip.dsls.payroll.Common._


/**
 * Author: Tulip
 * Date: 2020/1/6 21:54
 */
object Payroll {

  import dsl._

  def main(args: Array[String]): Unit = {
    val biweeklyDeductions = biweekly { deduct =>
      deduct federal_tax (25.0 percent)
      deduct state_tax (5.0 percent)
      deduct insurance_premiums (500.0 dollars)
      deduct retirement_savings (10.0 percent)
    }

    println(biweeklyDeductions)
    val annualGross = 100000.0
    val gross = biweeklyDeductions.gross(annualGross)
    val net = biweeklyDeductions.net(annualGross)
    print(f"Biweekly pay (annual: $$${annualGross}%.2f): ")
    println(f"Gross: $$${gross}%.2f, Net: $$${net}%.2f")

  }
}

object dsl {

  def biweekly(f: DeductionsBuilder => Deductions) =
    f(new DeductionsBuilder("Biweekly", 26.0))

  class DeductionsBuilder(
                           name: String,
                           divisor: Double = 1.0,
                           deducts: Vector[Deduction] = Vector.empty) extends Deductions(
    name, divisor, deducts) {

    def federal_tax(amount: Amount): DeductionsBuilder = {
      deductions = deductions :+ Deduction("federal taxes", amount)
      // 返回自己 给下一个调用继续用
      this
    }

    def state_tax(amount: Amount): DeductionsBuilder = {
      deductions = deductions :+ Deduction("state taxes", amount)
      this
    }

    def insurance_premiums(amount: Amount): DeductionsBuilder = {
      deductions = deductions :+ Deduction("insurance premiums", amount)
      this
    }

    def retirement_savings(amount: Amount): DeductionsBuilder = {
      deductions = deductions :+ Deduction("retirement savings", amount)
      this
    }
  }


}
