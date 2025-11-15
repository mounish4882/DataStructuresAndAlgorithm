package com.mounish.LeetCode

import scala.collection.mutable

/**
 * LeetCode Problem 20: Valid Parentheses
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 *
 * Improvements over Java version:
 * - Pattern matching for cleaner code
 * - Functional approaches
 * - Multiple implementation strategies
 * - Immutable data structures option
 */

object ValidParentheses {

  /**
   * Stack-based approach using mutable Stack (similar to Java version)
   */
  def isValid(s: String): Boolean = {
    val stack = mutable.Stack[Char]()
    val pairs = Map(')' -> '(', '}' -> '{', ']' -> '[')

    for (char <- s) {
      if (char == '(' || char == '{' || char == '[') {
        stack.push(char)
      } else {
        if (stack.isEmpty || stack.pop() != pairs(char)) {
          return false
        }
      }
    }

    stack.isEmpty
  }

  /**
   * Pattern matching approach (more idiomatic Scala)
   */
  def isValidPatternMatch(s: String): Boolean = {
    @scala.annotation.tailrec
    def validate(chars: List[Char], stack: List[Char]): Boolean = (chars, stack) match {
      case (Nil, Nil) => true
      case (Nil, _) => false
      case ('(' :: tail, _) => validate(tail, '(' :: stack)
      case ('{' :: tail, _) => validate(tail, '{' :: stack)
      case ('[' :: tail, _) => validate(tail, '[' :: stack)
      case (')' :: tail, '(' :: stackTail) => validate(tail, stackTail)
      case ('}' :: tail, '{' :: stackTail) => validate(tail, stackTail)
      case (']' :: tail, '[' :: stackTail) => validate(tail, stackTail)
      case _ => false
    }

    validate(s.toList, Nil)
  }

  /**
   * Functional approach using foldLeft with immutable List as stack
   */
  def isValidFunctional(s: String): Boolean = {
    val result = s.foldLeft(Option(List.empty[Char])) {
      case (None, _) => None
      case (Some(stack), char) => char match {
        case '(' | '{' | '[' => Some(char :: stack)
        case ')' => if (stack.headOption.contains('(')) Some(stack.tail) else None
        case '}' => if (stack.headOption.contains('{')) Some(stack.tail) else None
        case ']' => if (stack.headOption.contains('[')) Some(stack.tail) else None
        case _ => None
      }
    }

    result.contains(Nil)
  }

  /**
   * Using Either for detailed error reporting
   */
  def isValidWithError(s: String): Either[String, Boolean] = {
    @scala.annotation.tailrec
    def validate(chars: List[Char], stack: List[Char], position: Int): Either[String, Boolean] = {
      (chars, stack) match {
        case (Nil, Nil) => Right(true)
        case (Nil, remaining) => Left(s"Unclosed brackets: ${remaining.mkString}")
        case ('(' :: tail, _) => validate(tail, '(' :: stack, position + 1)
        case ('{' :: tail, _) => validate(tail, '{' :: stack, position + 1)
        case ('[' :: tail, _) => validate(tail, '[' :: stack, position + 1)
        case (')' :: tail, '(' :: stackTail) => validate(tail, stackTail, position + 1)
        case ('}' :: tail, '{' :: stackTail) => validate(tail, stackTail, position + 1)
        case (']' :: tail, '[' :: stackTail) => validate(tail, stackTail, position + 1)
        case (closing :: _, _) =>
          Left(s"Mismatched bracket '$closing' at position $position")
      }
    }

    validate(s.toList, Nil, 0)
  }

  /**
   * Counter-based approach (only works for single bracket type)
   * Included for educational purposes
   */
  def isValidSimple(s: String): Boolean = {
    var count = 0
    for (char <- s) {
      char match {
        case '(' => count += 1
        case ')' => count -= 1
        case _ => // ignore
      }
      if (count < 0) return false
    }
    count == 0
  }

  /**
   * Advanced: Check if string can be made valid by removing at most k brackets
   */
  def canBeMadeValid(s: String, k: Int): Boolean = {
    def minRemovals(str: String): Int = {
      val stack = mutable.Stack[Char]()
      var removals = 0

      for (char <- str) {
        char match {
          case '(' | '{' | '[' => stack.push(char)
          case ')' =>
            if (stack.headOption.contains('(')) stack.pop()
            else removals += 1
          case '}' =>
            if (stack.headOption.contains('{')) stack.pop()
            else removals += 1
          case ']' =>
            if (stack.headOption.contains('[')) stack.pop()
            else removals += 1
        }
      }

      removals + stack.size
    }

    minRemovals(s) <= k
  }

  /**
   * Generate all valid parentheses combinations of length n
   * (Bonus: related problem - LeetCode 22)
   */
  def generateParenthesis(n: Int): List[String] = {
    def generate(current: String, open: Int, close: Int, max: Int): List[String] = {
      if (current.length == max * 2) {
        List(current)
      } else {
        val withOpen = if (open < max) generate(current + "(", open + 1, close, max) else Nil
        val withClose = if (close < open) generate(current + ")", open, close + 1, max) else Nil
        withOpen ++ withClose
      }
    }

    generate("", 0, 0, n)
  }

  def demo(): Unit = {
    println("=== Valid Parentheses Demo ===")

    val testCases = List(
      ("()", true, "Simple valid"),
      ("()[]{}", true, "Multiple types"),
      ("(]", false, "Mismatched"),
      ("([)]", false, "Wrong order"),
      ("{[]}", true, "Nested"),
      ("", true, "Empty string"),
      ("((()))", true, "Multiple nested"),
      ("(((", false, "Unclosed"),
      (")))", false, "Extra closing"),
      ("({[]})", true, "Complex nested"),
      ("({[}])", false, "Interleaved mismatch"),
      ("{[()()]}", true, "Complex valid")
    )

    testCases.foreach { case (s, expected, description) =>
      println(s"\n$description: \"$s\"")
      println(s"Expected: $expected")

      val result1 = isValid(s)
      val result2 = isValidPatternMatch(s)
      val result3 = isValidFunctional(s)
      val result4 = isValidWithError(s)

      println(s"  Stack-based:      $result1 ${if (result1 == expected) "✓" else "✗"}")
      println(s"  Pattern Match:    $result2 ${if (result2 == expected) "✓" else "✗"}")
      println(s"  Functional:       $result3 ${if (result3 == expected) "✓" else "✗"}")
      println(s"  With Error:       $result4")

      // Verify all methods agree
      val allResults = List(result1, result2, result3)
      if (allResults.forall(_ == expected)) {
        println(s"  ✓ All methods correct")
      } else {
        println(s"  ✗ Some methods incorrect!")
      }
    }

    // Test canBeMadeValid
    println("\n=== Can Be Made Valid Demo ===")
    val validityTests = List(
      ("())", 1, true),
      ("(()", 1, true),
      ("())()", 1, true),
      ("(((", 3, true),
      ("))))", 4, true),
      ("((((", 2, false)
    )

    validityTests.foreach { case (s, k, expected) =>
      val result = canBeMadeValid(s, k)
      println(f"\"$s%-8s\" with k=$k: $result ${if (result == expected) "✓" else "✗"}")
    }

    // Generate valid parentheses
    println("\n=== Generate Valid Parentheses ===")
    for (n <- 1 to 4) {
      val combinations = generateParenthesis(n)
      println(s"n=$n (${combinations.length} combinations): ${combinations.mkString(", ")}")
    }

    // Performance comparison
    println("\n=== Performance Comparison ===")
    val iterations = 100000
    val testValue = "({[()()]})"

    val methods = List(
      ("Stack-based", () => isValid(testValue)),
      ("Pattern Match", () => isValidPatternMatch(testValue)),
      ("Functional", () => isValidFunctional(testValue))
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
