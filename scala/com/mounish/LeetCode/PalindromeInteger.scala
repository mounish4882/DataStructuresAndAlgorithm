package com.mounish.LeetCode

/**
 * LeetCode Problem 9: Palindrome Number
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 * An integer is a palindrome when it reads the same backward as forward.
 *
 * Improvements over Java version:
 * - Multiple implementation strategies
 * - Doesn't rely on ReverseInteger (more self-contained)
 * - Optimized half-reversal approach
 * - Functional implementations
 */

object PalindromeInteger {

  /**
   * Optimized approach - reverse only half of the number
   * Most efficient: O(log n) time, O(1) space
   */
  def isPalindrome(x: Int): Boolean = {
    // Negative numbers are not palindromes
    // Numbers ending in 0 (except 0 itself) are not palindromes
    if (x < 0 || (x % 10 == 0 && x != 0)) return false
    if (x < 10) return true

    var num = x
    var reversed = 0

    // Reverse only half of the number
    while (num > reversed) {
      reversed = reversed * 10 + num % 10
      num /= 10
    }

    // For even length: num == reversed
    // For odd length: num == reversed / 10 (middle digit doesn't matter)
    num == reversed || num == reversed / 10
  }

  /**
   * String-based approach (simple but less efficient)
   */
  def isPalindromeString(x: Int): Boolean = {
    if (x < 0) return false
    val str = x.toString
    str == str.reverse
  }

  /**
   * Full reversal approach (uses ReverseInteger logic)
   */
  def isPalindromeFullReverse(x: Int): Boolean = {
    if (x < 0) return false

    var num = x
    var reversed = 0L

    while (num != 0) {
      reversed = reversed * 10 + num % 10
      num /= 10
    }

    x == reversed
  }

  /**
   * Functional approach using recursion
   */
  def isPalindromeFunctional(x: Int): Boolean = {
    if (x < 0) return false

    @scala.annotation.tailrec
    def reverseHalf(num: Int, reversed: Int): Boolean = {
      if (num <= reversed) {
        num == reversed || num == reversed / 10
      } else {
        reverseHalf(num / 10, reversed * 10 + num % 10)
      }
    }

    if (x % 10 == 0 && x != 0) false
    else if (x < 10) true
    else reverseHalf(x, 0)
  }

  /**
   * Digit comparison approach (compare digits from both ends)
   */
  def isPalindromeDigitCompare(x: Int): Boolean = {
    if (x < 0) return false
    if (x < 10) return true

    val digits = getDigits(x)
    var left = 0
    var right = digits.length - 1

    while (left < right) {
      if (digits(left) != digits(right)) return false
      left += 1
      right -= 1
    }
    true
  }

  private def getDigits(x: Int): Array[Int] = {
    x.toString.map(_.asDigit).toArray
  }

  /**
   * Using List to collect digits and check palindrome
   */
  def isPalindromeList(x: Int): Boolean = {
    if (x < 0) return false

    @scala.annotation.tailrec
    def collectDigits(num: Int, acc: List[Int]): List[Int] = {
      if (num == 0) acc
      else collectDigits(num / 10, (num % 10) :: acc)
    }

    val digits = if (x == 0) List(0) else collectDigits(x, Nil)
    digits == digits.reverse
  }

  def demo(): Unit = {
    println("=== Palindrome Integer Demo ===")

    val testCases = List(
      (121, true, "Palindrome"),
      (-121, false, "Negative number"),
      (10, false, "Ends with 0"),
      (0, true, "Zero"),
      (12321, true, "Odd-length palindrome"),
      (1221, true, "Even-length palindrome"),
      (123, false, "Not a palindrome"),
      (9, true, "Single digit"),
      (1000021, false, "Contains zeros"),
      (9999999, true, "All same digits"),
      (1234567890, false, "Large non-palindrome")
    )

    testCases.foreach { case (x, expected, description) =>
      println(s"\n$description: x = $x, expected = $expected")

      val result1 = isPalindrome(x)
      val result2 = isPalindromeString(x)
      val result3 = isPalindromeFullReverse(x)
      val result4 = isPalindromeFunctional(x)
      val result5 = isPalindromeDigitCompare(x)
      val result6 = isPalindromeList(x)

      println(s"  Half-reverse:     $result1 ${if (result1 == expected) "✓" else "✗"}")
      println(s"  String-based:     $result2 ${if (result2 == expected) "✓" else "✗"}")
      println(s"  Full-reverse:     $result3 ${if (result3 == expected) "✓" else "✗"}")
      println(s"  Functional:       $result4 ${if (result4 == expected) "✓" else "✗"}")
      println(s"  Digit-compare:    $result5 ${if (result5 == expected) "✓" else "✗"}")
      println(s"  List-based:       $result6 ${if (result6 == expected) "✓" else "✗"}")

      // Verify all methods agree
      val allResults = List(result1, result2, result3, result4, result5, result6)
      if (allResults.forall(_ == expected)) {
        println(s"  ✓ All methods correct")
      } else {
        println(s"  ✗ Some methods incorrect!")
      }
    }

    // Performance comparison
    println("\n=== Performance Comparison ===")
    val iterations = 1000000
    val testValue = 123454321

    val methods = List(
      ("Half-reverse", () => isPalindrome(testValue)),
      ("String-based", () => isPalindromeString(testValue)),
      ("Full-reverse", () => isPalindromeFullReverse(testValue)),
      ("Functional", () => isPalindromeFunctional(testValue)),
      ("Digit-compare", () => isPalindromeDigitCompare(testValue)),
      ("List-based", () => isPalindromeList(testValue))
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
