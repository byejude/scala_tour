//偏函数相关:https://www.jianshu.com/p/0a8a15dbb348
val pf1: PartialFunction[Any, Any] = {
  case s: String => "YES"
  case d: Double => "YES"
}
val pf2: PartialFunction[Any, String] = {
  case d: Double => "YES"
}
val pf = pf1 orElse pf2
def tryPF(x: Any, f: PartialFunction[Any, Any]): String =
  try {
    f(x,x).toString
  } catch {
    case _: MatchError => "ERROR!"
  }
def d(x: Any, f: PartialFunction[Any, Any]) =
  f.isDefinedAt(x).toString
println("******| pf1 - String | pf2 - Double | pf - All")
println("x**** | def? | pf1(x) | def? | pf2(x) | def? | pf(x)")
println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
List("str", 3.14, 10) foreach { x =>
  printf("%-5s | %-5s | %-6s | %-5s | %-6s | %-5s | %-6s\n", x.toString,
    d(x, pf1), tryPF(x, pf1), d(x, pf2), tryPF(x, pf2), d(x, pf), tryPF(x, pf))
}