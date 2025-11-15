package com.mounish.LeetCode

import scala.collection.mutable

/**
 * LeetCode Problem 1: Two Sum
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 *
 * Improvements over Java version:
 * - Functional approach with pattern matching
 * - Multiple implementation strategies
 * - Type-safe Option return types
 * - More idiomatic Scala code
 */

object TwoSum {

  /**
   * Hash Map approach - O(n) time, O(n) space
   * Best approach for finding indices
   */
  def twoSumHashing(nums: Array[Int], target: Int): Option[(Int, Int)] = {
    val numToIndex = mutable.HashMap[Int, Int]()

    for (i <- nums.indices) {
      val complement = target - nums(i)
      numToIndex.get(complement) match {
        case Some(j) => return Some((j, i))
        case None => numToIndex(nums(i)) = i
      }
    }
    None
  }

  /**
   * Functional approach using zipWithIndex and find
   */
  def twoSumFunctional(nums: Array[Int], target: Int): Option[(Int, Int)] = {
    val indexed = nums.zipWithIndex
    val seen = mutable.HashMap[Int, Int]()

    indexed.collectFirst {
      case (num, idx) if seen.contains(target - num) =>
        (seen(target - num), idx)
      case (num, idx) =>
        seen(num) = idx
        (-1, -1) // Placeholder that won't be returned
    }.filter(_._1 != -1)
  }

  /**
   * Sorting approach with two pointers - O(n log n) time, O(n) space
   * Best for checking if a pair exists (but need to track original indices)
   */
  def twoSumSorting(nums: Array[Int], target: Int): Option[(Int, Int)] = {
    // Create array of (value, originalIndex) pairs
    val indexed = nums.zipWithIndex.sortBy(_._1)

    var left = 0
    var right = indexed.length - 1

    while (left < right) {
      val sum = indexed(left)._1 + indexed(right)._1

      if (sum == target) {
        return Some((indexed(left)._2 min indexed(right)._2, indexed(left)._2 max indexed(right)._2))
      } else if (sum < target) {
        left += 1
      } else {
        right -= 1
      }
    }
    None
  }

  /**
   * Brute force approach - O(n²) time, O(1) space
   * For educational purposes
   */
  def twoSumBruteForce(nums: Array[Int], target: Int): Option[(Int, Int)] = {
    for {
      i <- nums.indices
      j <- (i + 1) until nums.length
      if nums(i) + nums(j) == target
    } yield (i, j)
  }.headOption

  /**
   * Returns all pairs that sum to target (not just first one)
   */
  def findAllTwoSumPairs(nums: Array[Int], target: Int): List[(Int, Int)] = {
    val result = mutable.ListBuffer[(Int, Int)]()
    val numToIndices = mutable.HashMap[Int, mutable.ListBuffer[Int]]()

    for (i <- nums.indices) {
      numToIndices.getOrElseUpdate(nums(i), mutable.ListBuffer[Int]()) += i
    }

    for (i <- nums.indices) {
      val complement = target - nums(i)
      numToIndices.get(complement).foreach { indices =>
        indices.filter(_ > i).foreach { j =>
          result += ((i, j))
        }
      }
    }
    result.toList
  }

  def demo(): Unit = {
    println("=== Two Sum Demo ===")

    val testCases = List(
      (Array(2, 7, 11, 15), 9, "Example 1"),
      (Array(3, 2, 4), 6, "Example 2"),
      (Array(3, 3), 6, "Example 3"),
      (Array(1, 5, 3, 7, 9, 2), 10, "Multiple pairs"),
      (Array(-1, -2, -3, -4, -5), -8, "Negative numbers")
    )

    testCases.foreach { case (nums, target, description) =>
      println(s"\n$description: nums = [${nums.mkString(", ")}], target = $target")

      val hashResult = twoSumHashing(nums, target)
      println(s"  Hashing approach: $hashResult")
      hashResult.foreach { case (i, j) =>
        println(s"    Indices: [$i, $j], Values: [${nums(i)}, ${nums(j)}], Sum: ${nums(i) + nums(j)}")
      }

      val sortResult = twoSumSorting(nums, target)
      println(s"  Sorting approach: $sortResult")

      val functionalResult = twoSumFunctional(nums, target)
      println(s"  Functional approach: $functionalResult")

      val allPairs = findAllTwoSumPairs(nums, target)
      if (allPairs.length > 1) {
        println(s"  All pairs: $allPairs")
      }
    }

    // Performance comparison
    println("\n=== Performance Comparison ===")
    val largeArray = Array.tabulate(10000)(i => i)
    val largeTarget = 19997

    val methods = List(
      ("Hashing", () => twoSumHashing(largeArray, largeTarget)),
      ("Sorting", () => twoSumSorting(largeArray, largeTarget)),
      ("Functional", () => twoSumFunctional(largeArray, largeTarget))
    )

    methods.foreach { case (name, method) =>
      val start = System.nanoTime()
      val result = method()
      val duration = (System.nanoTime() - start) / 1000000.0
      println(f"$name%-12s: ${duration}%.3f ms, result = $result")
    }
  }
}
