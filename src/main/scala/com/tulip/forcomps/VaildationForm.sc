import scalaz._, std.AllInstances._
/** 对用户名进行验证；用户名必须非空并且只能包含字母。 */
def validName(key: String, name: String):
Validation[List[String], List[(String,Any)]] = {
  val n = name.trim // remove whitespace
  if (n.length > 0 && n.matches("""^\p{Alpha}$""")) Success(List(key -> n))
  else Failure(List(s"Invalid $key <$n>"))
}
/** 验证字符串能否转换为大于0的整数。 */
def positive(key: String, n: String):
Validation[List[String], List[(String,Any)]] = {
  try {
    val i = n.toInt
    if (i > 0) Success(List(key -> i))
    else Failure(List(s"Invalid $key $i"))
  } catch {
    case _: java.lang.NumberFormatException =>
      Failure(List(s"$n is not an integer"))
  }
}
def validateForm(firstName: String, lastName: String, age: String):
Validation[List[String], List[(String,Any)]] = {
  validName("first-name", firstName) +++ validName("last-name", lastName) +++
    positive("age", age)
}
validateForm("Dean", "Wampler", "29")
// 返回值: Success(List((first-name,Dean), (last-name,Wampler), (age,29)))
validateForm("", "Wampler", "29")
// 返回值: Failure(List(Invalid first-name <>))
validateForm("D e a n", "Wampler", "29")
// 返回值: Failure(List(Invalid first-name <D e a n>))
validateForm("D1e2a3n_", "Wampler", "29")
// 返回值: Failure(List(Invalid first-name <D1e2a3n_>))
validateForm("Dean", "", "29")
// 返回值: Failure(List(Invalid last-name <>))
validateForm("Dean", "Wampler", "0")
// 返回值: Failure(List(Invalid age 0))
validateForm("Dean", "Wampler", "xx")
// 返回值: Failure(List(xx is not an integer))
validateForm("", "Wampler", "0")
// 返回值: Failure(List(Invalid first-name <>, Invalid age 0))
validateForm("Dean", "", "0")
//返回值: Failure(List(Invalid last-name <>, Invalid age 0))
validateForm("D e a n", "", "29")
// 返回值: Failure(List(Invalid first-name <D e a n>, Invalid last-name <>))