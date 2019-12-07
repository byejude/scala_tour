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
} yield (key,i10)

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


val ignoreRegex = """^\s*(#.*|\s*)$""".r
val kvRegex = """^\s*([^=]+)\s*=\s*([^#]+)\s*.*$""".r
val properties = """
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

