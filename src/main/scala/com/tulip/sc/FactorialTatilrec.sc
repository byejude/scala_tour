import scala.annotation.tailrec

def factorial(i: BigInt): BigInt = {
  @tailrec
  def fact(i: BigInt, accumlator: BigInt): BigInt = {
    if (i == 1) accumlator
    else fact(i - 1, i * accumlator)
  }

  fact(i, 1)
}

for (i <- 1 to 1000000)
  println(s"$i:\t ${factorial(i)}")

// @tailrec  factorial在此没有进行尾递归调用 因此不能用此注解
//def factorial(i: BigInt): BigInt = {
//  if (i == 1) i
//  else i * factorial(i - 1)
//
//}
//for (i <- 1 to 1000000)
//  println(s"$i:\t${factorial(i)}")