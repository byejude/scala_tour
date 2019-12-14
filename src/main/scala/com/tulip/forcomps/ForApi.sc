val states = List("Alabama", "Alaska", "Virginia", "Wyoming")
//双循环,先循环第一组条件 再循环第二组条件
for {
  s <- states;
  c <- s
} yield println(s"$c-$c")
// 结果值: List("A-A", "l-L", "a-A", "b-B", ...)
//todo 双循环 相当于对第一组循环进行散列
states flatMap (_ map (c => s"$c-${c.toUpper}"))
//List[Char] = List(A, l, a, b, a, m, a, A, l, a, s, k, a, V, i, r, g, i, n, i, a, W, y, o, m, i, n, g)
states flatMap (_.toSeq)

// for循环中定义变量
for {
  s <- states
  c <- s
  if c.isLower
  c2 = println(s"$c-${c.toUpper} ")
} yield c2
// 结果值: List("l-L", "a-A", "b-B", ...)
//todo withFilter不返回新列表
states flatMap (_.toSeq withFilter (_.isLower) map { c =>
  val c2 = s"$c-${c.toUpper} "
  c2
})

//todo for循环中定义表达式等效于嵌套for循环
val map = Map("one" -> 1, "two" -> 2)
val list1 = for {
  (key, value) <- map
  i10 = value + 10
} yield (key, i10)

println(list1)

val list2 = for {
  (i, i10) <- for {
    (key, value) <- map
  } yield {
    val i10 = value + 10
    (key, i10)
  }
} yield (i, i10)
println(list2)

//TODO for循环的模式匹配
val ignoreRegex = """^\s*(#.*|\s*)$""".r
val kvRegex = """^\s*([^=]+)\s*=\s*([^#]+)\s*.*$""".r
val properties =
  """
    |# Book properties
    |
    |book.name = Programming Scala+Second Edition # A comment
    |book.authors = Dean Wampler and Alex Payne
    |book.publisher = O'Reilly
    |book.publication-year = 2014
    |""".stripMargin
println(properties)
val kvPairs = for {
  prop <- properties.split("\n");
  if ignoreRegex.findFirstIn(prop) == None
  kvRegex(key, value) = prop
} yield (key.trim, value.trim)

//TODO for 与Option 当处理Seq[Option[Int]] 可以使用Some(i)去接也可以去直接使用变量x去接
val results: Seq[Option[Int]] = Vector(Some(10), None, None, Some(21), Some(33))
val result2 = Vector(10, 0, 0, 21, 33)
//如果for循环返回的some(i) 则withFilter改写case None = true 与否结果都会去过滤None
val result3 = for {
  x <- results.withFilter {
    case Some(i) => {
      println("Some")
      true
    }
    case None => {
      println("None")
      true
    }
  }
} yield x

println("result3" + result3)

//TODO for处理Seq中的None 但是不处理单个None
def positive(i: Int): Option[Int] =
  if (i > 0) Some(i) else None
for {
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2 * i3)
} yield (i1 + i2 + i3 + i4)

for {
  i1 <- Seq(positive(5), positive(-1), positive(5))
  // i2 <- positive(-2)
} println(i1)

//TODO 使用either 容器抛出非检查型异常
def addInts2(s1: String, s2: String): Either[NumberFormatException, Int] =
  try {
    Right(s1.toInt + s2.toInt)
  } catch {
    case nfe: NumberFormatException => Left(nfe)
  }

println(addInts2("1", "2").getOrElse("x"))
println(addInts2("1", "x").isLeft)



//TODO 使用try容器抛出异常

import scala.util.{Success, Try}

def positiveTry(i: Int): Try[Int] = Try {
  assert(i > 0, s"nonpositive number $i")
  i
}
for {
  i1 <- positiveTry(5)
  i2 <- positiveTry(10 * i1)
  i3 <- positiveTry(25 * i2)
  i4 <- positiveTry(2 * i3)
} yield (i1 + i2 + i3 + i4)
// 返回值: scala.util.Try[Int] = Success(3805)
for {
  i1 <- positiveTry(5)
  i2 <- positiveTry(-1 * i1) // 失败!
  i3 <- positiveTry(25 * i2)
  i4 <- positiveTry(-2 * i3) // 失败!
} yield (i1 + i2 + i3 + i4)
// 返回值: scala.util.Try[Int] = Failure(
// java.lang.AssertionError: assertion failed: nonpositive number -5)



