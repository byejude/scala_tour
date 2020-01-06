package com.tulip.dsls.payroll.parsercomb

import com.tulip.dsls.payroll.Common._
import scala.util.parsing.combinator._

/**
 * Author: Tulip
 * Date: 2020/1/6 22:21
 */

object PayrollComb {

  import dsl.PayrollParser

  def main(args: Array[String]): Unit = {
    val input =
      """biweekly {
      federal tax          20.0  percent,
      state tax            3.0   percent,
      insurance premiums   250.0 dollars,
      retirement savings   15.0  percent
    }"""
    val parser = new PayrollParser
    val biweeklyDeductions = parser.parseAll(parser.biweekly, input).get

    println(biweeklyDeductions)
    val annualGross = 100000.0
    val gross = biweeklyDeductions.gross(annualGross)
    val net = biweeklyDeductions.net(annualGross)
    print(f"Biweekly pay (annual: $$${annualGross}%.2f): ")
    println(f"Gross: $$${gross}%.2f, Net: $$${net}%.2f")
  }
}

object dsl {

  class PayrollParser extends JavaTokenParsers {

    /** @return Parser[(Deductions)] */
    def biweekly = "biweekly" ~> "{" ~> deductions <~ "}" ^^ { ds => // <2>
      Deductions("Biweekly", 26.0, ds)
    }

    /** @return Parser[Vector[Deduction]] */
    def deductions = repsep(deduction, ",") ^^ { ds => // <3>
      ds.toVector
    }

    /** @return Parser[Deduction] */
    def deduction = federal_tax | state_tax | insurance | retirement // <4>

    /** @return Parser[Deduction] */
    def federal_tax = parseDeduction("federal", "tax") // <5>
    def state_tax = parseDeduction("state", "tax")

    def insurance = parseDeduction("insurance", "premiums")

    def retirement = parseDeduction("retirement", "savings")

    private def parseDeduction(word1: String, word2: String) = // <6>
      word1 ~> word2 ~> amount ^^ {
        amount => Deduction(s"${word1} ${word2}", amount)
      }

    /** @return Parser[Amount] */
    def amount = dollars | percentage // <7>

    /** @return Parser[Dollars] */
    def dollars = doubleNumber <~ "dollars" ^^ { d => Dollars(d) }

    /** @return Parser[Percentage] */
    def percentage = doubleNumber <~ "percent" ^^ { d => Percentage(d) }

    def doubleNumber = floatingPointNumber ^^ (_.toDouble)
  }

}

