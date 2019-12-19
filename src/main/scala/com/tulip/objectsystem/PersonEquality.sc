case class PersonEq(firstname: String, lastName: String, age: Int)

val p1a = PersonEq("Dean", "Wampler", 29)
val p1b = PersonEq("Dean", "Wampler", 29)
val p2 = PersonEq("Buck", "Trends", 30)

p1a equals p1a // = true
p1a equals p1b // = true
p1a equals p2 // = false
p1a equals null // = false
null equals p1a // 抛出 java.lang.NullPointerException异常
null equals null // 抛出 java.lang.NullPointerException异常

