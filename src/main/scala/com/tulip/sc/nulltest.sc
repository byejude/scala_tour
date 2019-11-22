val map = Map(
  "test" -> "test"
)

println(map.get("test").get)
println(map.get("null") == null)
println(map.get("null") == None)