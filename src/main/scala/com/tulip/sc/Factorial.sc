def fatorial(i: Int): Int = {

  def fact(i: Int, accumlator: Int): Int = {
    if (i <= 1) accumlator
    else fact(i - 1, i * accumlator)
  }

  fact(i, 1)
}

(0 to 5) foreach (i => println(fatorial(i)))