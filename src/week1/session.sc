object session {
  1 + 2
  def abs(x: Double) = if (x < 0) -x else x

  // Version 2 of sqrt() : This version
  // redundantly defines x everywhere.
//  def sqrt(x: Double) = {
//    def sqrtIter(guess: Double, x: Double): Double =
//      if (isGoodEnough(guess, x)) guess
//      else sqrtIter(improve(guess, x), x)
//
//    def isGoodEnough(guess: Double, x: Double) =
//    // naive variant
//    //    abs(guess * guess - x) < .001
//    // working variant
//      abs(guess * guess - x) / x < 0.001
//
//    def improve(guess: Double, x: Double) =
//      (guess + x / guess) / 2
//
//    sqrtIter(1.0, x)
//  }

  /* Version 3 of sqrt(): This version
      only defines x in the outer scope.
      Since the nested blocks don't redefine, or shadow x,
      we don't have to pass x in as a second parameter
      for sqrtIter(), isGoodEnough(), and improve().
   */
  def sqrt(x: Double) = {
    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double) =
    // naive variant
    //    abs(guess * guess - x) < .001
    // working variant
      abs(guess * guess - x) / x < 0.001

    def improve(guess: Double) =
      (guess + x / guess) / 2

    sqrtIter(1.0)
  }

//  l = [0.001, 0.1e-20, 1.0e20, 1.0e50]
  sqrt(0.001)
  sqrt(0.1e-20)
  sqrt(1.0e20)
  sqrt(1.0e50)

}