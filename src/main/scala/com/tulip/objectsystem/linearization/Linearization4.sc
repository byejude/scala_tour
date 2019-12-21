// src/main/scala/progscala2/objectsystem/linearization/linearization4.sc

class C1 {
  print("C1 init! ")
  def m = print("C1 ")
}

trait T1 extends C1 {
  print("T1 init! ")
  override def m = {
    print("T1 ")
    super.m
  }
}

trait T2 extends C1 {
  print("T2 init! ")
  override def m = {
    print("T2 ")
    super.m
  }
}

trait T3 extends C1 {
  print("T3 init! ")
  override def m = {
    print("T3 ")
    super.m
  }
}

class C2A extends T2 {
  print("C2A init! ")
  override def m = {
    print("C2A ");
    super.m
  }
}

class C2 extends C2A with T1 with T2 with T3 {
  print("C2 init! ")
  override def m = {
    print("C2 ");
    super.m
  }
}

def calcLinearization(obj: C1, name: String) = {
  print(s"$name: ")
  obj.m
  print("AnyRef ")
  println("Any")
}

calcLinearization(new C2, "C2 ")
// C1 init! T2 init! C2A init! T1 init! T3 init! C2 init! C2 : C2 T3 T1 C2A T2 C1 AnyRef Any
println("*****************************")
calcLinearization(new T3 {}, "T3 ")
// C1 init! T3 init! T3 : T3 C1 AnyRef Any
println("*****************************")
calcLinearization(new T2 {}, "T2 ")
// C1 init! T2 init! T2 : T2 C1 AnyRef Any
println("*****************************")
calcLinearization(new T1 {}, "T1 ")
// C1 init! T1 init! T1 : T1 C1 AnyRef Any
println("*****************************")
calcLinearization(new C2A, "C2A")
// C1 init! T2 init! C2A init! C2A: C2A T2 C1 AnyRef Any
println("*****************************")
calcLinearization(new C1, "C1 ")
// C1 init! C1 : C1 AnyRef Any

//TODO 调用重写父类方法以及super父类 遵循线性化算法

/**
 * 线性化算法
 * (1) 当前实例的具体类型会被放到线性化后的首个元素位置处。
 * (2) 按照该实例父类型的顺序从右到左的放置节点，针对每个父类型执行线性化算法，
 * 并将执行结果合并。（我们暂且不对 AnyRef 和 Any 类型进行处理。）
 * (3) 按照从左到右的顺序，对类型节点进行检查，如果类型节点在该节点右边出现过，
 * 那么便将该类型移除。
 * (4) 在类型线性化层次结构末尾处添加 AnyRef 和 Any 类型。
 * 如果是对价值类执行线性化算法，请使用 AnyVal 类型替代 AnyRef 类型。
 *
 *
 *
 */
/
