case class Complex(real: Double, imag: Double) {
  //TODO  unary_ 前缀定义一元操作符
  // 注意 -和 : 之间的空格，空格在这里是必须的，它可以告诉编译器方法名以 - 而不是 : 结尾！
  def unary_~ : Complex = Complex(-real, imag)

  def ~(other: Complex) = Complex(real - other.real, imag - other.imag)
}

val c1 = Complex(1.1, 2.2)
val c2 = ~c1 // Complex(-1.1, 2.2)
val c3 = c1.unary_~ // Complex(-1.1, 2.2)
val c4 =  ~ Complex(0.5, 1.0) // Complex(0.6, 1.2)
val c5 =  c1 ~ Complex(0.5, 1.0) // Complex(0.6, 1.2)