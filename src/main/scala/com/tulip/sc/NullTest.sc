val map = Map(
  "test" -> "test",
  "null2" -> null
)

println(map.get("test").get)
println(map.get("null2").get == null)
println(map.get("null") == None)

var a = 0;
val numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
// for 循环
for {a <- numList
     if a != 3; if a < 8} {
  println("Value of a: " + a);
}