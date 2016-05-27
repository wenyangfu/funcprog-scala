package recfun
import common._

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  // The following pattern of numbers is called Pascal’s triangle.
  // Pascal:
  //     1
  //    1 1
  //   1 2 1
  //  1 3 3 1
  // 1 4 6 4 1
  // The numbers at the edge of the triangle are all 1,
  // and each number inside the triangle is the sum of
  // the two numbers above it. Write a function that
  // computes the elements of Pascal’s triangle by
  // means of a recursive process.
  // Do this exercise by implementing the pascal function,
  // which takes a column c and a row r, counting from 0
  // and returns the number at that spot in the triangle.
  // For example, pascal(0,2)=1, pascal(1,2)=2 and pascal(1,3)=3.
  def pascal(c: Int, r: Int): Int = {
    def findPascal(c: Int, r: Int): Int = {
      if (c == 0 || c == r)
        1
      else
        findPascal(c - 1, r - 1) + findPascal(c, r - 1)
    }
    if (c > r || c < 0) // Invalid Pascal's Triangle number...
      0
    //      throw new IndexOutOfBoundsException
    else
      findPascal(c, r)

  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    // Original Solution:
    /*def balance(acc: Int, chars: List[Char]): Boolean = {
      if (chars.isEmpty)
        acc == 0
      else
        if (acc < 0) false
        else
          if (chars.head == '(')
            balance(acc + 1, chars.tail)
          else
            if (chars.head == ')')
              balance(acc - 1, chars.tail)
            else
              balance(acc, chars.tail)
    }
    if (chars.isEmpty) false
    else balance(0, chars)*/
    // Refactored:
    @tailrec
    def iter(acc:Int, chars: List[Char]): Int = {
      if (acc < 0 || chars.isEmpty) acc
      else if (chars.head == '(') iter(acc + 1, chars.tail)
      else if (chars.head == ')') iter(acc - 1, chars.tail)
      else iter(acc, chars.tail)
    }
    iter(0, chars) == 0
  }

  /**
    * Exercise 3
    */
  // We want to make change for N cents given a set of M coins, where each coin has
  // a different denomination, and where we have an infinite supply of each coin
  // Precondition checking: Money must be >= 0 and coin denominations must be non-empty.
  // If preconditions are not met, just return 0.
  // Base case 1 : no money left + change available - success (increment)
  // Base case 2 : negative amount of money (don't increment)
  // Base case 3 : no coins left - we have no change available (don't increment)
  // case 3 : Recurse with 2 branches:
  //          1. subtract the one coin's value from the money
  //          2. remove one coin from the set of
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) 0
    else if (money == 0) 1
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}