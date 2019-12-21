trait AbstractT2Lazy {
  println("In AbstractT2:")
  val value: Int
  lazy val inverse = 1.0 / value
  //todo 内部不能使用print等操作lazy字段
  // println("AbstractT2: value = "+value+", inverse = "+inverse)
}

val obj = new AbstractT2Lazy {
  println("In obj:")
  val value = 10
}
println("obj.value = " + obj.value + ", inverse = " + obj.inverse)
