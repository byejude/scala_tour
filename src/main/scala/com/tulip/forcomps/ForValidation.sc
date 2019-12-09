//TODO 使用scalaz的Validation来收集一连串的错误

import scalaz._,std.AllInstances._
import scalaz.Validation.FlatMap._
def positiveValidx(i: Int): Validation[List[String], Int] = {
  if (i > 0) Success(i)
  else Failure(List(s"Nonpositive integer $i"))
}
for {
  i1 <- positiveValidx(5)
  i2 <- positiveValidx(10 * i1)
  i3 <- positiveValidx(25 * i2)
  i4 <- positiveValidx(2 * i3)
} yield (i1 + i2 + i3 + i4)
// 返回值: scalaz.Validation[List[String],Int] = Success(3805)

for {i1 <- positiveValidx(5)
     i2 <- positiveValidx(-1 * i1) // 错误!  combinators遭遇到异常便会退出
     i3 <- positiveValidx(25 * i2)
     i4 <- positiveValidx(-2 * i3) // 错误!
     } yield (i1 + i2 + i3 + i4)

positiveValidx(5) +++ positiveValidx(10) +++ positiveValidx(25)
positiveValidx(5) +++ positiveValidx(-10) +++ positiveValidx(25) +++ positiveValidx(-30)