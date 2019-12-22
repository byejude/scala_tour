import scala.collection.mutable

class ListBuilder[T] extends mutable.Builder[T,List[T]] {
  private var storage = Vector.empty[T]
  override def addOne(elem: T) = {
    storage = storage :+ elem
    this
  }
  def clear(): Unit = { storage = Vector.empty[T] }
  def result(): List[T] = storage.toList
}

val lb = new ListBuilder[Int]
(1 to 3) foreach (i => lb += i)
lb.result
// 结果: List(1, 2, 3)