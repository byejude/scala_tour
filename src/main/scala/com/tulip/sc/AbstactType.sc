import java.io._

//abstract class BulkReader {
//  type In
//  val source: In
//
//  def read: String
//}
//
//class StringBulkReader(val source: String) extends BulkReader {
//  override type In = String
//
//  override def read: String = source
//}
//
//class FileBulkReader(val source: File) extends BulkReader {
//  override type In = File
//
//  override def read: String = {
//    val in = new BufferedInputStream(new FileInputStream(source))
//    val numBytes = in.available()
//    val bytes = new Array[Byte](numBytes)
//    in.read(bytes, 0, numBytes)
//    new String(bytes)
//  }
//}

//simplify
abstract class BulkReader[In] {
  val source: In

  def read: String
}

class StringBulkReader(val source: String) extends BulkReader[String] {

  override def read: String = source
}

class FileBulkReader(val source: File) extends BulkReader[File] {

  override def read: String = {
    val in = new BufferedInputStream(new FileInputStream(source))
    val numBytes = in.available()
    val bytes = new Array[Byte](numBytes)
    in.read(bytes, 0, numBytes)
    new String(bytes)
  }
}

def getFiles(inputs: File*): collection.Seq[File] = {
  inputs.filter(_.isFile) ++
    inputs.filter(_.isDirectory).flatMap(dir => getFiles(dir.listFiles: _*))
}


println(getFiles(new File("./")))
println(new StringBulkReader("hello sweetie~").read)
println(new FileBulkReader(new File("./src/main/scala/com/tulip/sc/Factorial.sc")).read)