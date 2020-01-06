package com.tulip.dynamic



object App extends App{

  def makeMap(name: String, capital: String, statehood: Int) = Map("name" -> name, "capital" -> capital, "statehood" -> statehood)

  // "Records" for Five of the states in the U.S.A.
  val states = CLINQ(
    List(
      makeMap("Alaska", "Juneau", 1959),
      makeMap("California", "Sacramento", 1850),
      makeMap("Illinois", "Springfield", 1818),
      makeMap("Virginia", "Richmond", 1788),
      makeMap("Washington", "Olympia", 1889)))

  // Field projections ("SELECT ..."):
  println(states.name)
  println(states.capital)
  println(states.statehood)
  println(states.name_and_capital)
  println(states.name_and_statehood)
  println(states.capital_and_statehood)
  println(states.name_capital_and_statehood)
  println(states.all)

  // Add "WHERE" clauses.
  println(states.all.where("name").NE("Alaska"))
  println(states.all.where("statehood").EQ(1889))
  println(states.name_and_statehood.where("statehood").NE(1850))
}

