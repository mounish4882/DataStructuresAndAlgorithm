package com.mounish.LeetCode

/**
 * LeetCode Problem 13: Roman to Integer
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * Improvements over Java version:
 * - Pattern matching for cleaner code
 * - Multiple implementation strategies
 * - Functional approaches
 * - Better error handling with Option types
 */

object RomanToInteger {

  private val romanMap: Map[Char, Int] = Map(
    'I' -> 1,
    'V' -> 5,
    'X' -> 10,
    'L' -> 50,
    'C' -> 100,
    'D' -> 500,
    'M' -> 1000
  )

  /**
   * Iterative approach (similar to Java version)
   * Checks if current value is less than next value for subtraction cases
   */
  def romanToInt(s: String): Int = {
    if (s.isEmpty) return 0

    var result = 0
    var i = 0

    while (i < s.length) {
      val current = romanMap(s(i))

      if (i + 1 < s.length) {
        val next = romanMap(s(i + 1))
        if (current < next) {
          result += next - current
          i += 2
        } else {
          result += current
          i += 1
        }
      } else {
        result += current
        i += 1
      }
    }

    result
  }

  /**
   * Functional approach using foldLeft
   */
  def romanToIntFunctional(s: String): Int = {
    s.foldLeft((0, 0)) { case ((sum, prev), char) =>
      val current = romanMap(char)
      val newSum = if (prev < current) sum + current - 2 * prev else sum + current
      (newSum, current)
    }._1
  }

  /**
   * Pattern matching approach
   */
  def romanToIntPatternMatch(s: String): Int = {
    @scala.annotation.tailrec
    def convert(chars: List[Char], acc: Int): Int = chars match {
      case Nil => acc
      case 'I' :: 'V' :: tail => convert(tail, acc + 4)
      case 'I' :: 'X' :: tail => convert(tail, acc + 9)
      case 'X' :: 'L' :: tail => convert(tail, acc + 40)
      case 'X' :: 'C' :: tail => convert(tail, acc + 90)
      case 'C' :: 'D' :: tail => convert(tail, acc + 400)
      case 'C' :: 'M' :: tail => convert(tail, acc + 900)
      case head :: tail => convert(tail, acc + romanMap(head))
    }

    convert(s.toList, 0)
  }

  /**
   * Reverse iteration approach (simpler logic)
   */
  def romanToIntReverse(s: String): Int = {
    var result = 0
    var prevValue = 0

    for (i <- s.length - 1 to 0 by -1) {
      val currentValue = romanMap(s(i))
      if (currentValue < prevValue) {
        result -= currentValue
      } else {
        result += currentValue
      }
      prevValue = currentValue
    }

    result
  }

  /**
   * Using sliding window
   */
  def romanToIntSlidingWindow(s: String): Int = {
    val values = s.map(romanMap)

    values.zipWithIndex.foldLeft(0) { case (sum, (value, idx)) =>
      if (idx < values.length - 1 && value < values(idx + 1)) {
        sum - value
      } else {
        sum + value
      }
    }
  }

  /**
   * Safe version with Option return type
   */
  def romanToIntSafe(s: String): Option[Int] = {
    if (s.isEmpty || !s.forall(romanMap.contains)) {
      None
    } else {
      Some(romanToInt(s))
    }
  }

  /**
   * Convert integer to Roman (bonus: reverse operation)
   */
  def intToRoman(num: Int): String = {
    val values = List(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val symbols = List("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

    @scala.annotation.tailrec
    def convert(n: Int, pairs: List[(Int, String)], acc: String): String = pairs match {
      case Nil => acc
      case (value, symbol) :: tail =>
        if (n >= value) convert(n - value, pairs, acc + symbol)
        else convert(n, tail, acc)
    }

    convert(num, values.zip(symbols), "")
  }

  def demo(): Unit = {
    println("=== Roman to Integer Demo ===")

    val testCases = List(
      ("III", 3, "Simple addition"),
      ("LVIII", 58, "L = 50, V= 5, III = 3"),
      ("MCMXCIV", 1994, "M = 1000, CM = 900, XC = 90, IV = 4"),
      ("IV", 4, "Subtraction case"),
      ("IX", 9, "Subtraction case"),
      ("XL", 40, "Subtraction case"),
      ("XC", 90, "Subtraction case"),
      ("CD", 400, "Subtraction case"),
      ("CM", 900, "Subtraction case"),
      ("MMMCMXCIX", 3999, "Maximum value"),
      ("I", 1, "Minimum value")
    )

    testCases.foreach { case (roman, expected, description) =>
      println(s"\n$description: \"$roman\" = $expected")

      val result1 = romanToInt(roman)
      val result2 = romanToIntFunctional(roman)
      val result3 = romanToIntPatternMatch(roman)
      val result4 = romanToIntReverse(roman)
      val result5 = romanToIntSlidingWindow(roman)

      println(s"  Iterative:        $result1 ${if (result1 == expected) "✓" else "✗"}")
      println(s"  Functional:       $result2 ${if (result2 == expected) "✓" else "✗"}")
      println(s"  Pattern Match:    $result3 ${if (result3 == expected) "✓" else "✗"}")
      println(s"  Reverse:          $result4 ${if (result4 == expected) "✓" else "✗"}")
      println(s"  Sliding Window:   $result5 ${if (result5 == expected) "✓" else "✗"}")

      // Verify all methods agree
      val allResults = List(result1, result2, result3, result4, result5)
      if (allResults.forall(_ == expected)) {
        println(s"  ✓ All methods correct")
      } else {
        println(s"  ✗ Some methods incorrect!")
      }
    }

    // Test integer to roman conversion
    println("\n=== Integer to Roman Demo ===")
    val intTests = List(3, 58, 1994, 4, 9, 40, 90, 400, 900, 3999, 1)
    intTests.foreach { num =>
      val roman = intToRoman(num)
      val backToInt = romanToInt(roman)
      println(f"$num%4d -> $roman%-15s -> $backToInt%4d ${if (num == backToInt) "✓" else "✗"}")
    }

    // Performance comparison
    println("\n=== Performance Comparison ===")
    val iterations = 100000
    val testValue = "MCMXCIV"

    val methods = List(
      ("Iterative", () => romanToInt(testValue)),
      ("Functional", () => romanToIntFunctional(testValue)),
      ("Pattern Match", () => romanToIntPatternMatch(testValue)),
      ("Reverse", () => romanToIntReverse(testValue)),
      ("Sliding Window", () => romanToIntSlidingWindow(testValue))
    )

    methods.foreach { case (name, method) =>
      val start = System.nanoTime()
      var i = 0
      while (i < iterations) {
        method()
        i += 1
      }
      val duration = (System.nanoTime() - start) / 1000000.0
      println(f"$name%-15s: ${duration}%.3f ms for $iterations iterations")
    }
  }
}
