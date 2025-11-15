package com.mounish.Thread

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
 * Comprehensive Threading and Concurrency examples in Scala
 *
 * Improvements over Java version:
 * - Demonstrates Scala-specific concurrency features
 * - Futures and Promises
 * - Parallel collections
 * - Functional approach to concurrency
 * - Multiple threading patterns
 */

object ScalaThreadingExamples {

  /**
   * 1. Traditional Thread extending Thread class (similar to Java)
   */
  class ThreadExtendingThread(name: String) extends Thread(name) {
    override def run(): Unit = {
      for (i <- 1 to 5) {
        println(s"${Thread.currentThread().getName} - Count: $i")
        Thread.sleep(500)
      }
      println(s"${Thread.currentThread().getName} completed")
    }
  }

  def extendingThreadDemo(): Unit = {
    println("=== Extending Thread Class ===")
    val thread1 = new ThreadExtendingThread("Thread-1")
    val thread2 = new ThreadExtendingThread("Thread-2")

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
    println("Both threads completed\n")
  }

  /**
   * 2. Implementing Runnable (similar to Java)
   */
  class RunnableTask(name: String) extends Runnable {
    override def run(): Unit = {
      for (i <- 1 to 5) {
        println(s"$name - Count: $i")
        Thread.sleep(500)
      }
      println(s"$name completed")
    }
  }

  def implementingRunnableDemo(): Unit = {
    println("=== Implementing Runnable ===")
    val task1 = new RunnableTask("Runnable-1")
    val task2 = new RunnableTask("Runnable-2")

    val thread1 = new Thread(task1)
    val thread2 = new Thread(task2)

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
    println("Both runnable tasks completed\n")
  }

  /**
   * 3. Lambda/Anonymous function approach (similar to Java 8)
   */
  def lambdaThreadDemo(): Unit = {
    println("=== Lambda/Anonymous Function Threads ===")

    val thread1 = new Thread(() => {
      for (i <- 1 to 5) {
        println(s"Lambda-Thread-1 - Count: $i")
        Thread.sleep(500)
      }
    })

    val thread2 = new Thread(() => {
      for (i <- 1 to 5) {
        println(s"Lambda-Thread-2 - Count: $i")
        Thread.sleep(500)
      }
    })

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
    println("Both lambda threads completed\n")
  }

  /**
   * 4. Stoppable Thread (controlled termination)
   */
  class StoppableThread(name: String) extends Thread(name) {
    @volatile private var stopRequested = false

    def requestStop(): Unit = {
      stopRequested = true
    }

    override def run(): Unit = {
      var count = 0
      while (!stopRequested) {
        count += 1
        println(s"$name - Count: $count")
        Thread.sleep(300)
      }
      println(s"$name stopped gracefully after $count iterations")
    }
  }

  def stoppableThreadDemo(): Unit = {
    println("=== Stoppable Thread ===")
    val thread = new StoppableThread("Stoppable-Thread")
    thread.start()

    Thread.sleep(2000) // Let it run for 2 seconds
    println("Requesting thread to stop...")
    thread.requestStop()
    thread.join()
    println("Stoppable thread demo completed\n")
  }

  /**
   * 5. Scala Futures - Modern async programming
   */
  def futuresDemo(): Unit = {
    println("=== Scala Futures ===")

    // Simple future
    val future1 = Future {
      Thread.sleep(1000)
      println("Future 1 computing...")
      42
    }

    val future2 = Future {
      Thread.sleep(800)
      println("Future 2 computing...")
      58
    }

    // Combining futures
    val combinedFuture = for {
      result1 <- future1
      result2 <- future2
    } yield {
      println(s"Combining results: $result1 + $result2")
      result1 + result2
    }

    // Handle completion with callbacks
    combinedFuture.onComplete {
      case Success(value) => println(s"Combined result: $value")
      case Failure(exception) => println(s"Failed with: $exception")
    }

    // Wait for completion
    val result = Await.result(combinedFuture, 5.seconds)
    println(s"Final result: $result\n")
  }

  /**
   * 6. Promises - Completing futures manually
   */
  def promisesDemo(): Unit = {
    println("=== Scala Promises ===")

    val promise = Promise[Int]()
    val future = promise.future

    future.onComplete {
      case Success(value) => println(s"Promise completed with: $value")
      case Failure(exception) => println(s"Promise failed with: $exception")
    }

    // Complete promise in another thread
    new Thread(() => {
      Thread.sleep(1000)
      println("Computing result...")
      promise.success(100)
    }).start()

    Await.result(future, 5.seconds)
    println("Promise demo completed\n")
  }

  /**
   * 7. Parallel Collections
   */
  def parallelCollectionsDemo(): Unit = {
    println("=== Parallel Collections ===")

    val numbers = (1 to 10).toList

    println("Sequential processing:")
    val seqStart = System.nanoTime()
    val seqResults = numbers.map { n =>
      Thread.sleep(100) // Simulate work
      n * 2
    }
    val seqTime = (System.nanoTime() - seqStart) / 1000000
    println(s"Results: ${seqResults.mkString(", ")}")
    println(f"Time: $seqTime ms")

    println("\nParallel processing:")
    val parStart = System.nanoTime()
    val parResults = numbers.par.map { n =>
      Thread.sleep(100) // Simulate work
      n * 2
    }
    val parTime = (System.nanoTime() - parStart) / 1000000
    println(s"Results: ${parResults.mkString(", ")}")
    println(f"Time: $parTime ms")
    println(f"Speedup: ${seqTime.toDouble / parTime}x\n")
  }

  /**
   * 8. Producer-Consumer pattern using Futures
   */
  def producerConsumerDemo(): Unit = {
    println("=== Producer-Consumer Pattern ===")

    val queue = scala.collection.mutable.Queue[Int]()
    val lock = new Object()

    // Producer
    val producer = Future {
      for (i <- 1 to 10) {
        lock.synchronized {
          queue.enqueue(i)
          println(s"Produced: $i (Queue size: ${queue.size})")
          lock.notify()
        }
        Thread.sleep(200)
      }
      lock.synchronized {
        queue.enqueue(-1) // Sentinel value
        lock.notify()
      }
    }

    // Consumer
    val consumer = Future {
      var done = false
      while (!done) {
        lock.synchronized {
          while (queue.isEmpty) {
            lock.wait()
          }
          val item = queue.dequeue()
          if (item == -1) {
            done = true
            println("Consumer received sentinel, stopping")
          } else {
            println(s"Consumed: $item (Queue size: ${queue.size})")
          }
        }
        Thread.sleep(300)
      }
    }

    Await.result(Future.sequence(List(producer, consumer)), 10.seconds)
    println("Producer-Consumer demo completed\n")
  }

  /**
   * 9. Thread Pool using Execution Context
   */
  def threadPoolDemo(): Unit = {
    println("=== Thread Pool Demo ===")

    val tasks = (1 to 10).map { i =>
      Future {
        val threadName = Thread.currentThread().getName
        println(s"Task $i running on $threadName")
        Thread.sleep(500)
        i * i
      }
    }

    val results = Await.result(Future.sequence(tasks), 10.seconds)
    println(s"All tasks completed: ${results.mkString(", ")}\n")
  }

  /**
   * 10. Synchronized access to shared resource
   */
  class Counter {
    private var count = 0

    def increment(): Unit = synchronized {
      count += 1
    }

    def get: Int = synchronized {
      count
    }
  }

  def synchronizationDemo(): Unit = {
    println("=== Synchronization Demo ===")

    val counter = new Counter()

    val threads = (1 to 10).map { i =>
      Future {
        for (_ <- 1 to 1000) {
          counter.increment()
        }
      }
    }

    Await.result(Future.sequence(threads), 10.seconds)
    println(s"Final counter value: ${counter.get} (expected: 10000)")
    println("Synchronization demo completed\n")
  }

  def demo(): Unit = {
    println("\n" + "=" * 60)
    println("SCALA THREADING AND CONCURRENCY EXAMPLES")
    println("=" * 60 + "\n")

    try {
      extendingThreadDemo()
      Thread.sleep(500)

      implementingRunnableDemo()
      Thread.sleep(500)

      lambdaThreadDemo()
      Thread.sleep(500)

      stoppableThreadDemo()
      Thread.sleep(500)

      futuresDemo()
      Thread.sleep(500)

      promisesDemo()
      Thread.sleep(500)

      parallelCollectionsDemo()
      Thread.sleep(500)

      producerConsumerDemo()
      Thread.sleep(500)

      threadPoolDemo()
      Thread.sleep(500)

      synchronizationDemo()

    } catch {
      case e: Exception =>
        println(s"Error in threading demo: ${e.getMessage}")
        e.printStackTrace()
    }

    println("\n" + "=" * 60)
    println("ALL THREADING EXAMPLES COMPLETED")
    println("=" * 60 + "\n")
  }
}
