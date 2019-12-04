val map = Map(
  "test" -> "test",
  "null2" -> null
)

println(map.get("test").get)
println(map.get("null2").get == null)
println(map.get("null") == None)