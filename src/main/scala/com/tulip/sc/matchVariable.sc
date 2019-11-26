
for {x <- Seq(1, 2, 2.343, "one", "Bob", true, 'A')} {
  val str = x match {
    case 1 => "int 1"
    case i: String => s"String $i"
    case b: Boolean => s"Boolean $b"
    case unexpected => "unexpected " + unexpected
  }
  println(str)
}

def checkY(y: Int, s: Seq[Any]): Unit = {
  for {x <- s} {
    val str = x match {
      case `y` => s"i got $y"
      case i: Int => s"int $i"
      case unexpected => "unexpected " + unexpected
    }
    println(str)
  }
}

checkY(100,Seq(1,2,3,4,100,99))

