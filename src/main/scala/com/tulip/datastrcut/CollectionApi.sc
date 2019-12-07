val list: List[Int] = List(1,2,3,4)
//TODO  反转集合
//println(list.reverse) //List(6, 5, 4, 3, 2, 1)   //set，map没有反转
list.max
list.min
list.sum //求和
list.product //乘积

// TODO 排序
//val listStr = List("1", "5", "9", "11")
//println("排序 = " + listStr.sorted) //排序 = List(1, 11, 5, 9) 按照字典顺序；  数字、字符串都是按字符串的字典顺序排序

val stringList = List("11", "55", "13", "22")
// 1, 5, 3, 2
// 11 22 13 55
//  排序 使用自定义排序规则进行排序
println(stringList.sortBy(x => {
  x.substring(1, 2)  //List(11, 22, 13, 55)
}))
//  排序：升序，降序
println("升序= " + stringList.sortWith{  //升序= List(11, 13, 22, 55)
  case (left, right) => {
    left < right
  }
})
println("降序= " + stringList.sortWith{  //降序= List(55, 22, 13, 11)
  case (left, right) => {
    left > right
  }
})


//TODO 分组= Map(1 -> List(1, 3, 5), 0 -> List(2, 4, 6))
println("分组= " +List(1, 3, 5).groupBy(_ % 2))

//TODO Map 转换，映射  map方法将集合中的每一个元素进行转换后放置到新的集合中
println(list.map(_ * 2)) //List(2, 4, 6, 8, 10, 12)
println(list.map((_, 1))) //List((1,1), (2,1), (3,1), (4,1), (5,1), (6,1))

//TODO  flatMap 扁平化  数->可迭代 整体，拆成一个个的个体变成可迭代的集合
val list2 = List(List(1,2), List(3, 4), List(5, 6)) // ==> List(1,2,3,4,5,6)
// in : List, out : Iterator
println(s"list2 is $list2") //List(List(1, 2), List(3, 4), List(5, 6))
println("扁平化=" + list2.flatMap(x => x)) //扁平化=List(1, 2, 3, 4, 5, 6) 集合中整体拆成一个个的

//TODO reduce 化简，也称归约
val list3 = List(1,2,3,4,5,6,7)
println(list3.reduce((_ + _))) //28 两两聚合
println(list3.reduce(_ - _)) //-26
println(list3.reduceLeft(_ - _))//-26 同上，底层调用是一样的  ((((((1-2)-3)-4)-5)-6)-7)
println(list3.reduceRight(_ - _)) //4 从右边开始计算         (1-(2-(3-(4-(5-(6-7))))))

//TODO fold　折叠foldLeft    foldRight 缩写分别为 /： 和 ：\
//scala中两个map的合并
val list4: List[Int] = List(1,2,3,4)
//  fold fold和reduce比较类似，仅仅是多了一个集合之后的初始值
println(list4.fold(10)(_ - _)) // -0   底层本质上就是调用的foldLeft(z)(op)
println(list4.foldLeft(10)(_ - _)) //-0  (((10[INIT]-1)-2)-3)-4)

// 底层运算时，将集合反转后相邻的元素交互调用逻辑
println(list4.foldRight(10)(_ - _)) //8              (1-(2-(3-(4-10[INIT]))))
//reverse.foldLeft(z)((right, left) => op(left, right))

/*  foldLeft             foldRight
        OP                   OP
        /\                   /\
      OP  coll(2)    coll(n-3) OP
      /\                        /\
    op  coll(1)         coll(n-2) OP
   /\                              /\
init coll(0)               coll(n-1) init
 */

//TODO 扫描 scanLeft
//扫描，即对某个集合的所有元素做fold操作，但是会把产生的所有中间结果放置于一个集合中保存
def minus(num1: Int, num2: Int): Int = {
  num1 - num2
}
val i1 = (1 to 5).scanLeft(5)(minus) //1 2 3 4 5
//5 4 2 -1 -5 -10
println(i1)  //Vector(5, 4, 2, -1, -5, -10)

def add( num1 : Int, num2 : Int ) : Int = {
  num1 + num2
}
val i2 = (1 to 5).scanLeft(5)(add) //1 2 3 4 5
//5 6 8 11 15 20 (5,add(1,5),add(add(1,5),2) .....)
println(i2) //Vector(5, 6, 8, 11, 15, 20)

val i3 = (1 to 5).scanRight(5)(minus) //1 2 3 4 5
//-2 3 -1 4 0 5    (minus(1,minus(2,minus(3,minus(4,minus(5,5)))))),............
//                      -2        3        -1       4         0
println(i3) //Vector(-2, 3, -1, 4, 0, 5)

//TODO 集合交Intersect、差diff、合union
// SET 会去重  LIST不会
val lis1 = List(1,2,3,4)
val lis2 = List(3,4,5,6,7)
// 两个集合合并
println(lis1.union(lis2)) //List(1, 2, 3, 4, 3, 4, 5, 6, 7)
// 两个集合交集
println(lis1.intersect(lis2)) //List(3, 4)
// 两个集合差集
println(lis2.diff(lis1)) //List(5, 6, 7)

//TODO ZIP 拉链，将两个集合进行 对偶元组合并，可以使用拉链
println(lis1.zip(lis2)) //List((1,3), (2,4), (3,5), (4,6))

//TODO 滑动窗口 sliding
// 滑动窗口window 截取长度为x的数组为intses[x],intses最后一个元素为滑到lis2尾部元素为止
val intses: Iterator[List[Int]] = lis2.sliding(4)
for (elem <- intses) {
  println(elem) //List(1, 2, 3)
  //List(2, 3, 4)
}

//TODO 过滤filter   a.filter(_%2 == 0)
val a = List(1, 2, 3, 4)
def test(i: Int): Boolean = {
  i % 2 == 0
}
println("过滤=" + a.filter(test)) //简化如下
println(a.filter(_ % 2 == 1))


//TODO 对后面的元素进行操作，并聚合结果
val rdd1 = List(1,2,3,4,5)
val result: Int = rdd1.aggregate(0)(
  (acc, number) => {
    val res1 = acc + number
    println("par    " + acc + " + " + number+" = "+res1)
    res1
  },
  (par1, par2) => {
    val res2 = par1 + par2
    println("com    " + par1 + " + " + par2+" = "+res2)
    res2
  }
)
println(result)
