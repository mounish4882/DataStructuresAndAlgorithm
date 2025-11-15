package com.mounish.LeetCode

/**
 * LeetCode Problem 14: Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Improvements over Java version:
 * - Multiple implementation strategies
 * - Functional approaches
 * - Pattern matching
 * - More idiomatic Scala code
 */

object LongestCommonPrefix {

  /**
   * Horizontal scanning (similar to Java version)
   * Compare prefix with each string and reduce it
   */
  def longestCommonPrefix(strs: Array[String]): String = {
    if (strs == null || strs.isEmpty) return ""

    var prefix = strs(0)

    for (i <- 1 until strs.length) {
      while (!strs(i).startsWith(prefix)) {
        prefix = prefix.substring(0, prefix.length - 1)
        if (prefix.isEmpty) return ""
      }
    }

    prefix
  }

  /**
   * Vertical scanning - compare characters column by column
   */
  def longestCommonPrefixVertical(strs: Array[String]): String = {
    if (strs == null || strs.isEmpty) return ""
    if (strs.length == 1) return strs(0)

    for (i <- strs(0).indices) {
      val char = strs(0)(i)

      for (j <- 1 until strs.length) {
        if (i >= strs(j).length || strs(j)(i) != char) {
          return strs(0).substring(0, i)
        }
      }
    }

    strs(0)
  }

  /**
   * Functional approach using foldLeft
   */
  def longestCommonPrefixFunctional(strs: Array[String]): String = {
    if (strs == null || strs.isEmpty) return ""

    strs.foldLeft(strs.head) { (prefix, str) =>
      prefix.zip(str).takeWhile { case (a, b) => a == b }.map(_._1).mkString
    }
  }

  /**
   * Using reduce for a more concise functional approach
   */
  def longestCommonPrefixReduce(strs: Array[String]): String = {
    if (strs == null || strs.isEmpty) return ""

    strs.reduce { (prefix, str) =>
      prefix.zip(str).takeWhile { case (a, b) => a == b }.map(_._1).mkString
    }
  }

  /**
   * Binary search approach - O(S * log m) where S is sum of all characters, m is length of shortest string
   */
  def longestCommonPrefixBinarySearch(strs: Array[String]): String = {
    if (strs == null || strs.isEmpty) return ""

    val minLen = strs.map(_.length).min

    def isCommonPrefix(len: Int): Boolean = {
      val prefix = strs(0).substring(0, len)
      strs.forall(_.startsWith(prefix))
    }

    var low = 0
    var high = minLen

    while (low <= high) {
      val mid = (low + high) / 2
      if (isCommonPrefix(mid)) {
        low = mid + 1
      } else {
        high = mid - 1
      }
    }

    strs(0).substring(0, (low + high) / 2)
  }

  /**
   * Divide and conquer approach
   */
  def longestCommonPrefixDivideConquer(strs: Array[String]): String = {
    if (strs == null || strs.isEmpty) return ""

    def findCommonPrefix(left: String, right: String): String = {
      val minLen = left.length min right.length
      var i = 0
      while (i < minLen && left(i) == right(i)) {
        i += 1
      }
      left.substring(0, i)
    }

    def divideConquer(left: Int, right: Int): String = {
      if (left == right) {
        strs(left)
      } else {
        val mid = (left + right) / 2
        val leftPrefix = divideConquer(left, mid)
        val rightPrefix = divideConquer(mid + 1, right)
        findCommonPrefix(leftPrefix, rightPrefix)
      }
    }

    divideConquer(0, strs.length - 1)
  }

  /**
   * Trie-based approach (most efficient for multiple queries)
   */
  class TrieNode {
    val children = scala.collection.mutable.Map[Char, TrieNode]()
    var isEndOfWord = false
  }

  def longestCommonPrefixTrie(strs: Array[String]): String = {
    if (strs == null || strs.isEmpty) return ""

    // Build trie with first string
    val root = new TrieNode()
    var node = root

    for (char <- strs(0)) {
      node.children(char) = new TrieNode()
      node = node.children(char)
    }
    node.isEndOfWord = true

    // Find common prefix by traversing trie
    node = root
    val prefix = new StringBuilder()

    while (node.children.size == 1 && !node.isEndOfWord) {
      val (char, nextNode) = node.children.head
      // Check if this character exists in all strings at this position
      if (strs.forall(s => prefix.length < s.length && s(prefix.length) == char)) {
        prefix.append(char)
        node = nextNode
      } else {
        return prefix.toString()
      }
    }

    prefix.toString()
  }

  /**
   * Using transpose for elegant functional solution
   */
  def longestCommonPrefixTranspose(strs: Array[String]): String = {
    if (strs == null || strs.isEmpty) return ""

    strs.map(_.toList)
      .transpose
      .takeWhile(_.distinct.length == 1)
      .map(_.head)
      .mkString
  }

  def demo(): Unit = {
    println("=== Longest Common Prefix Demo ===")

    val testCases = List(
      (Array("flower", "flow", "flight"), "fl", "Common case"),
      (Array("dog", "racecar", "car"), "", "No common prefix"),
      (Array("interspecies", "interstellar", "interstate"), "inters", "Long prefix"),
      (Array("throne", "throne"), "throne", "Identical strings"),
      (Array("abc"), "abc", "Single string"),
      (Array("", "b"), "", "Empty string in array"),
      (Array("a"), "a", "Single character"),
      (Array("ab", "a"), "a", "Different lengths"),
      (Array("cir", "car"), "c", "Single character prefix"),
      (Array("reflower", "flow", "flight"), "", "No match")
    )

    testCases.foreach { case (strs, expected, description) =>
      println(s"\n$description: [${strs.mkString("\", \"")}]")
      println(s"Expected: \"$expected\"")

      val result1 = longestCommonPrefix(strs.clone())
      val result2 = longestCommonPrefixVertical(strs.clone())
      val result3 = longestCommonPrefixFunctional(strs.clone())
      val result4 = longestCommonPrefixReduce(strs.clone())
      val result5 = longestCommonPrefixBinarySearch(strs.clone())
      val result6 = longestCommonPrefixDivideConquer(strs.clone())

      println(s"  Horizontal:       \"$result1\" ${if (result1 == expected) "✓" else "✗"}")
      println(s"  Vertical:         \"$result2\" ${if (result2 == expected) "✓" else "✗"}")
      println(s"  Functional:       \"$result3\" ${if (result3 == expected) "✓" else "✗"}")
      println(s"  Reduce:           \"$result4\" ${if (result4 == expected) "✓" else "✗"}")
      println(s"  Binary Search:    \"$result5\" ${if (result5 == expected) "✓" else "✗"}")
      println(s"  Divide & Conquer: \"$result6\" ${if (result6 == expected) "✓" else "✗"}")

      if (strs.nonEmpty && strs.forall(_.nonEmpty)) {
        val result7 = longestCommonPrefixTranspose(strs.clone())
        println(s"  Transpose:        \"$result7\" ${if (result7 == expected) "✓" else "✗"}")
      }

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
    val iterations = 100000
    val testValue = Array("interspecies", "interstellar", "interstate", "international")

    val methods = List(
      ("Horizontal", () => longestCommonPrefix(testValue.clone())),
      ("Vertical", () => longestCommonPrefixVertical(testValue.clone())),
      ("Functional", () => longestCommonPrefixFunctional(testValue.clone())),
      ("Reduce", () => longestCommonPrefixReduce(testValue.clone())),
      ("Binary Search", () => longestCommonPrefixBinarySearch(testValue.clone())),
      ("Divide & Conquer", () => longestCommonPrefixDivideConquer(testValue.clone())),
      ("Transpose", () => longestCommonPrefixTranspose(testValue.clone()))
    )

    methods.foreach { case (name, method) =>
      val start = System.nanoTime()
      var i = 0
      while (i < iterations) {
        method()
        i += 1
      }
      val duration = (System.nanoTime() - start) / 1000000.0
      println(f"$name%-17s: ${duration}%.3f ms for $iterations iterations")
    }
  }
}
