package com.mounish.LeetCode

/**
 * LeetCode Problem 7: Reverse Integer
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-2³¹, 2³¹ - 1],
 * then return 0.
 *
 * Improvements over Java version:
 * - Pattern matching for cleaner code
 * - Tail recursion for functional approach
 * - Better overflow handling
 * - Multiple implementation strategies
 */

object ReverseInteger {

  private val IntMax = Int.MaxValue
  private val IntMin = Int.MinValue

  /**
   * Iterative approach with overflow check (similar to Java version)
   */
  def reverse(x: Int): Int = {
    var num = x
    var reversed = 0

    while (num != 0) {
      val digit = num % 10
      num /= 10

      // Check for overflow before multiplying
      if (reversed > IntMax / 10 || (reversed == IntMax / 10 && digit > 7)) return 0
      if (reversed < IntMin / 10 || (reversed == IntMin / 10 && digit < -8)) return 0

      reversed = reversed * 10 + digit
    }

    reversed
  }

  /**
   * Functional approach using recursion
   */
  def reverseFunctional(x: Int): Int = {
    @scala.annotation.tailrec
    def reverseHelper(num: Int, reversed: Int): Int = {
      if (num == 0) return reversed

      val digit = num % 10
      val newNum = num / 10

      // Check for overflow
      if (reversed > IntMax / 10 || (reversed == IntMax / 10 && digit > 7)) return 0
      if (reversed < IntMin / 10 || (reversed == IntMin / 10 && digit < -8)) return 0

      reverseHelper(newNum, reversed * 10 + digit)
    }

    reverseHelper(x, 0)
  }

  /**
   * String-based approach (less efficient but more readable)
   */
  def reverseString(x: Int): Int = {
    try {
      val isNegative = x < 0
      val absStr = Math.abs(x.toLong).toString.reverse
      val result = absStr.toLong * (if (isNegative) -1 else 1)

      if (result > IntMax || result < IntMin) 0
      else result.toInt
    } catch {
      case _: NumberFormatException => 0
    }
  }

  /**
   * Using Option for safer overflow handling
   */
  def reverseSafe(x: Int): Option[Int] = {
    @scala.annotation.tailrec
    def reverseHelper(num: Int, reversed: Int): Option[Int] = {
      if (num == 0) return Some(reversed)

      val digit = num % 10
      val newNum = num / 10

      // Check for overflow
      if (reversed > IntMax / 10 || (reversed == IntMax / 10 && digit > 7)) return None
      if (reversed < IntMin / 10 || (reversed == IntMin / 10 && digit < -8)) return None

      reverseHelper(newNum, reversed * 10 + digit)
    }

    reverseHelper(x, 0)
  }

  /**
   * Mathematical approach using Long to detect overflow
   */
  def reverseWithLong(x: Int): Int = {
    var num = x
    var reversed = 0L

    while (num != 0) {
      reversed = reversed * 10 + num % 10
      num /= 10
    }

    if (reversed > IntMax || reversed < IntMin) 0
    else reversed.toInt
  }

  def demo(): Unit = {
    println("=== Reverse Integer Demo ===")

    val testCases = List(
      (123, "Positive number"),
      (-123, "Negative number"),
      (120, "Trailing zero"),
      (0, "Zero"),
      (1534236469, "Overflow case (should return 0)"),
      (-2147483648, "Min Int (overflow)"),
      (2147483647, "Max Int"),
      (1463847412, "Another overflow case"),
      (7, "Single digit"),
      (-7, "Single negative digit")
    )

    testCases.foreach { case (x, description) =>
      println(s"\n$description: x = $x")

      val result1 = reverse(x)
      val result2 = reverseFunctional(x)
      val result3 = reverseString(x)
      val result4 = reverseWithLong(x)
      val result5 = reverseSafe(x)

      println(s"  Iterative:    $result1")
      println(s"  Functional:   $result2")
      println(s"  String-based: $result3")
      println(s"  Long-based:   $result4")
      println(s"  Safe (Option): $result5")

      // Verify all methods agree
      if (result1 == result2 && result2 == result3 && result3 == result4 && result4 == result5.getOrElse(0)) {
        println(s"  ✓ All methods agree")
      } else {
        println(s"  ✗ Methods disagree!")
      }
    }

    // Performance comparison
    println("\n=== Performance Comparison ===")
    val iterations = 1000000
    val testValue = 1234567

    val methods = List(
      ("Iterative", () => reverse(testValue)),
      ("Functional", () => reverseFunctional(testValue)),
      ("String-based", () => reverseString(testValue)),
      ("Long-based", () => reverseWithLong(testValue))
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
