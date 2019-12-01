package com.tulip.implicits.payroll

/**
 * Author: Tulip
 * Date: 2019/12/1 20:47
 */
sealed trait PreTaxDeductions
sealed trait PostTaxDeductions
sealed trait Final

case class Employee(
                   name: String,
                   annualSalary: Float,    //年薪
                   taxRate: Float,         //税率
                   insurancePremiumsPerPayPeriod: Float, //保险预扣除款
                   _401kDeductionRate: Float,   //401k税率
                   postTaxDeductions: Float     //
                   )

case class Pay[Step](employee: Employee,netPay: Float)

object Payroll{
  //固定俩周一次发薪
  def start(employee: Employee): Pay[PreTaxDeductions] =
    Pay[PreTaxDeductions](employee,employee.annualSalary / 26.0F)

  def minusInsurance(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - pay.employee.insurancePremiumsPerPayPeriod
    pay copy(netPay = newNet)
  }

  def minus401k(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - pay.employee._401kDeductionRate*pay.netPay
    pay copy(netPay = newNet)
  }

  def minusTax(pay: Pay[PreTaxDeductions]): Pay[PostTaxDeductions] = {
    val newNet = pay.netPay - pay.employee.taxRate*pay.netPay
    pay copy(netPay = newNet)
  }

  def minusFinalDeuctions(pay: Pay[PostTaxDeductions]): Pay[Final] = {
    val newNet = pay.netPay - pay.employee.postTaxDeductions
    pay copy(netPay = newNet)
  }
}
