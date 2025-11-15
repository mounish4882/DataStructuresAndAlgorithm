package com.mounish.Algorithms

import scala.collection.mutable

/**
 * Fibonacci implementations with various optimization techniques
 * Improvements over Java version:
 * - Multiple implementation strategies
 * - Lazy evaluation
 * - Stream-based approach
 * - Tail recursion optimization
 * - BigInt for handling larger numbers
 */

object FibonacciDynamicProgramming {

  /**
   * Memoization approach using mutable HashMap (similar to Java version)
   */
  class MemoizedFibonacci {
    private val memo = mutable.HashMap[Long, BigInt](0L -> BigInt(0), 1L -> BigInt(1))

    def calculate(n: Long): BigInt = {
      memo.getOrElseUpdate(n, calculate(n - 1) + calculate(n - 2))
    }
  }

  /**
   * Functional memoization using immutable Map (pure functional approach)
   */
  def fibonacciFunctional(n: Long): BigInt = {
    def fib(n: Long, memo: Map[Long, BigInt]): (BigInt, Map[Long, BigInt]) = {
      if (memo.contains(n)) {
        (memo(n), memo)
      } else {
        val (fib1, memo1) = fib(n - 1, memo)
        val (fib2, memo2) = fib(n - 2, memo1)
        val result = fib1 + fib2
        (result, memo2 + (n -> result))
      }
    }

    val initialMemo = Map(0L -> BigInt(0), 1L -> BigInt(1))
    fib(n, initialMemo)._1
  }

  /**
   * Tail-recursive approach with accumulator (most efficient)
   */
  def fibonacciTailRec(n: Long): BigInt = {
    @scala.annotation.tailrec
    def fib(n: Long, a: BigInt, b: BigInt): BigInt = n match {
      case 0 => a
      case _ => fib(n - 1, b, a + b)
    }
    fib(n, BigInt(0), BigInt(1))
  }

  /**
   * Iterative approach (similar performance to tail recursion)
   */
  def fibonacciIterative(n: Long): BigInt = {
    if (n <= 1) return BigInt(n)

    var a = BigInt(0)
    var b = BigInt(1)
    var i = 2L

    while (i <= n) {
      val temp = a + b
      a = b
      b = temp
      i += 1
    }
    b
  }

  /**
   * Lazy Stream approach - generates infinite Fibonacci sequence
   * Most elegant Scala solution
   */
  lazy val fibonacciStream: LazyList[BigInt] = {
    def fib(a: BigInt, b: BigInt): LazyList[BigInt] = a #:: fib(b, a + b)
    fib(0, 1)
  }

  def fibonacciFromStream(n: Long): BigInt = {
    fibonacciStream(n.toInt)
  }

  /**
   * Matrix exponentiation approach - O(log n) complexity
   * Most efficient for very large n
   */
  def fibonacciMatrix(n: Long): BigInt = {
    def multiplyMatrix(a: Array[Array[BigInt]], b: Array[Array[BigInt]]): Array[Array[BigInt]] = {
      Array(
        Array(a(0)(0) * b(0)(0) + a(0)(1) * b(1)(0), a(0)(0) * b(0)(1) + a(0)(1) * b(1)(1)),
        Array(a(1)(0) * b(0)(0) + a(1)(1) * b(1)(0), a(1)(0) * b(0)(1) + a(1)(1) * b(1)(1))
      )
    }

    def powerMatrix(matrix: Array[Array[BigInt]], n: Long): Array[Array[BigInt]] = {
      if (n == 1) return matrix

      val half = powerMatrix(matrix, n / 2)
      val result = multiplyMatrix(half, half)

      if (n % 2 == 0) result
      else multiplyMatrix(result, matrix)
    }

    if (n == 0) return BigInt(0)
    if (n == 1) return BigInt(1)

    val baseMatrix = Array(Array(BigInt(1), BigInt(1)), Array(BigInt(1), BigInt(0)))
    val resultMatrix = powerMatrix(baseMatrix, n)
    resultMatrix(0)(1)
  }

  def demo(): Unit = {
    println("=== Fibonacci Dynamic Programming Demo ===")
    val testValues = List(10L, 20L, 30L, 40L, 50L)

    println("\n1. Memoized Fibonacci:")
    val memoFib = new MemoizedFibonacci()
    testValues.foreach { n =>
      val start = System.nanoTime()
      val result = memoFib.calculate(n)
      val duration = (System.nanoTime() - start) / 1000000.0
      println(f"fib($n%2d) = $result%-20s (${duration}%.3f ms)")
    }

    println("\n2. Tail Recursive Fibonacci:")
    testValues.foreach { n =>
      val start = System.nanoTime()
      val result = fibonacciTailRec(n)
      val duration = (System.nanoTime() - start) / 1000000.0
      println(f"fib($n%2d) = $result%-20s (${duration}%.3f ms)")
    }

    println("\n3. Stream-based Fibonacci:")
    testValues.foreach { n =>
      val start = System.nanoTime()
      val result = fibonacciFromStream(n)
      val duration = (System.nanoTime() - start) / 1000000.0
      println(f"fib($n%2d) = $result%-20s (${duration}%.3f ms)")
    }

    println("\n4. Matrix Exponentiation Fibonacci (fastest for large n):")
    testValues.foreach { n =>
      val start = System.nanoTime()
      val result = fibonacciMatrix(n)
      val duration = (System.nanoTime() - start) / 1000000.0
      println(f"fib($n%2d) = $result%-20s (${duration}%.3f ms)")
    }

    println("\n5. First 20 Fibonacci numbers using Stream:")
    println(fibonacciStream.take(20).mkString(", "))

    println("\n6. Large Fibonacci number demonstration:")
    val largeN = 100L
    println(s"fib($largeN) = ${fibonacciTailRec(largeN)}")
  }
}
