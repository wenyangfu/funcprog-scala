object tail_recursion_exercise {
  def factorial(n: Int): Int = {
    def loop(n: Int, accumulator: Int): Int =
      if (n == 0) accumulator else loop(n - 1, n * accumulator)
    loop(n, 1)
  }
    factorial(10)
}