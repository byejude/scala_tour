// 永远不要用FLoat类型表示货币:
def calcTax(amount: Float)(implicit ratex: Float): Float = amount * ratex

object SimpleStateSalesTax {
  implicit val rate: Float = 0.05F
}

case class ComplicatedSalesTaxData(
                                    baseRate: Float,
                                    isTaxHoliday: Boolean,
                                    storeId: Int)

object ComplicatedSalesTax {
  private def extraTaxRateForStore(id: Int): Float = {
    // 可以通过id推断出商铺所在地，之后再计算附加税……
    0.0F
  }

  implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float =
    if (cstd.isTaxHoliday) 0.0F
    else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
}
{
  import SimpleStateSalesTax.rate
  val amount = 100F
  println(s"Tax on $amount = ${calcTax(amount)}")
}
{
  import ComplicatedSalesTax.rate
  implicit val myStore = ComplicatedSalesTaxData(0.06F, false, 1010)
  val amount = 100F
  println(s"Tax on $amount = ${calcTax(amount)}")
}


import math.Ordering
case class MyList[A](list: List[A]) {
  def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): List[A] =
    list.sortBy(f)(ord)
  def sortBy2[B : Ordering](f: A => B): List[A] =
    list.sortBy(f)(implicitly[Ordering[B]])
}
val list = MyList(List(1,3,5,2,4))
list sortBy1 (i => -i)
list sortBy2 (i => -i)