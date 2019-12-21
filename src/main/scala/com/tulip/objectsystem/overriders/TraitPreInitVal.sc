
trait AbstractT2 {
  println("In AbstractT2:")
  val value: Int
  val inverse = 1.0/value
  println("AbstractT2: value = "+value+", inverse = "+inverse)
}

val obj = new {
  // println("In obj:")
  val value = 10
} with AbstractT2

println("obj.value = "+obj.value+", inverse = "+obj.inverse)
