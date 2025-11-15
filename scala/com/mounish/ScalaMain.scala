package com.mounish

import com.mounish.Algorithms.FibonacciDynamicProgramming
import com.mounish.DataStructures.SinglyLinkedList
import com.mounish.LeetCode._
import com.mounish.Thread.ScalaThreadingExamples

/**
 * Main entry point for Scala implementations
 *
 * This demonstrates all data structures, algorithms, and LeetCode solutions
 * implemented in Scala with improvements over the original Java versions.
 */

object ScalaMain extends App {

  def printSectionHeader(title: String): Unit = {
    val width = 80
    val padding = (width - title.length - 2) / 2
    println("\n" + "=" * width)
    println("=" * padding + s" $title " + "=" * padding)
    println("=" * width + "\n")
  }

  def promptUser(message: String): Boolean = {
    print(s"$message (y/n): ")
    val response = scala.io.StdIn.readLine().toLowerCase
    response == "y" || response == "yes"
  }

  def runDataStructuresDemo(): Unit = {
    printSectionHeader("DATA STRUCTURES")
    SinglyLinkedList.demo()
  }

  def runAlgorithmsDemo(): Unit = {
    printSectionHeader("ALGORITHMS")
    FibonacciDynamicProgramming.demo()
  }

  def runLeetCodeProblems(): Unit = {
    printSectionHeader("LEETCODE PROBLEMS")

    println("\n--- Problem 1: Two Sum ---")
    TwoSum.demo()

    println("\n" + "-" * 80)
    println("\n--- Problem 7: Reverse Integer ---")
    ReverseInteger.demo()

    println("\n" + "-" * 80)
    println("\n--- Problem 9: Palindrome Integer ---")
    PalindromeInteger.demo()

    println("\n" + "-" * 80)
    println("\n--- Problem 13: Roman to Integer ---")
    RomanToInteger.demo()

    println("\n" + "-" * 80)
    println("\n--- Problem 14: Longest Common Prefix ---")
    LongestCommonPrefix.demo()

    println("\n" + "-" * 80)
    println("\n--- Problem 20: Valid Parentheses ---")
    ValidParentheses.demo()
  }

  def runThreadingDemo(): Unit = {
    printSectionHeader("THREADING & CONCURRENCY")
    ScalaThreadingExamples.demo()
  }

  def runInteractiveMenu(): Unit = {
    var running = true

    while (running) {
      printSectionHeader("SCALA DATA STRUCTURES & ALGORITHMS")
      println("Select a demo to run:")
      println("1. Data Structures (Linked List)")
      println("2. Algorithms (Fibonacci DP)")
      println("3. LeetCode Problems (All 6 problems)")
      println("4. Threading & Concurrency")
      println("5. Run All Demos")
      println("6. Exit")
      print("\nEnter your choice (1-6): ")

      try {
        val choice = scala.io.StdIn.readLine()

        choice match {
          case "1" => runDataStructuresDemo()
          case "2" => runAlgorithmsDemo()
          case "3" => runLeetCodeProblems()
          case "4" => runThreadingDemo()
          case "5" =>
            runDataStructuresDemo()
            runAlgorithmsDemo()
            runLeetCodeProblems()
            runThreadingDemo()
            printSectionHeader("ALL DEMOS COMPLETED")
          case "6" =>
            println("\nExiting... Goodbye!")
            running = false
          case _ =>
            println("\nInvalid choice. Please enter a number between 1 and 6.")
        }

        if (running && choice != "6") {
          print("\nPress Enter to continue...")
          scala.io.StdIn.readLine()
        }
      } catch {
        case _: Exception =>
          println("\nError reading input. Please try again.")
      }
    }
  }

  def runAllDemos(): Unit = {
    printSectionHeader("SCALA IMPLEMENTATIONS - COMPLETE DEMO")

    println("This will run all demonstrations:")
    println("- Data Structures")
    println("- Algorithms")
    println("- LeetCode Problems")
    println("- Threading & Concurrency")
    println()

    runDataStructuresDemo()
    runAlgorithmsDemo()
    runLeetCodeProblems()
    runThreadingDemo()

    printSectionHeader("ALL DEMONSTRATIONS COMPLETED SUCCESSFULLY")

    println("""
      |Summary of Improvements in Scala Version:
      |
      |1. DATA STRUCTURES:
      |   - Both immutable and mutable implementations
      |   - Pattern matching for cleaner code
      |   - Type parameterization (generics)
      |   - Tail recursion optimization
      |
      |2. ALGORITHMS:
      |   - Multiple implementation strategies
      |   - Lazy evaluation with Streams
      |   - Matrix exponentiation for O(log n) complexity
      |   - Pure functional approaches
      |
      |3. LEETCODE PROBLEMS:
      |   - Functional programming approaches
      |   - Pattern matching
      |   - Option types for safer error handling
      |   - Multiple solution strategies for each problem
      |
      |4. THREADING:
      |   - Modern Scala concurrency with Futures
      |   - Promises for manual completion
      |   - Parallel collections
      |   - Functional approach to concurrency
      |   - Producer-Consumer patterns
      |
      |Key Scala Features Demonstrated:
      |   - Immutability by default
      |   - Higher-order functions
      |   - Pattern matching
      |   - For-comprehensions
      |   - Type inference
      |   - Tail call optimization
      |   - Case classes and sealed traits
      |   - Implicit parameters
      |   - Lazy evaluation
      |
      |Performance Comparisons:
      |   All demos include performance benchmarks comparing different
      |   implementation approaches to help understand trade-offs.
      |""".stripMargin)
  }

  // Main execution
  if (args.contains("--all")) {
    // Run all demos without interaction
    runAllDemos()
  } else if (args.contains("--help") || args.contains("-h")) {
    println("""
      |Scala Data Structures and Algorithms Demo
      |
      |Usage:
      |  scala ScalaMain [options]
      |
      |Options:
      |  --all          Run all demos automatically
      |  --interactive  Run interactive menu (default)
      |  --help, -h     Show this help message
      |
      |Examples:
      |  scala ScalaMain                 # Interactive menu
      |  scala ScalaMain --all           # Run all demos
      |  scala ScalaMain --help          # Show help
      |""".stripMargin)
  } else {
    // Default: Run interactive menu
    runInteractiveMenu()
  }
}
