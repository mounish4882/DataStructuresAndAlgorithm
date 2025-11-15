# Data Structures and Algorithms

A comprehensive collection of data structures, algorithms, and LeetCode problem solutions implemented in **Java** and **Scala**.

## Overview

This repository contains implementations of fundamental data structures, algorithms, and solutions to common coding problems. Each implementation is available in two languages:

- **Java** - Original implementations using OOP principles
- **Scala** - Enhanced implementations with functional programming features

## Repository Structure

```
DataStructuresAndAlgorithm/
├── src/                          # Java implementations
│   ├── Main.java
│   └── com/mounish/
│       ├── Algorithms/
│       │   └── FibonacciDynamicProgramming.java
│       ├── DataStructures/
│       │   └── SinglyLinkedList.java
│       ├── LeetCode/
│       │   ├── TwoSum.java
│       │   ├── ReverseInteger.java
│       │   ├── PalindromeInteger.java
│       │   ├── RomanToInteger.java
│       │   ├── LongestCommonPrefix.java
│       │   └── ValidParanthesis.java
│       ├── Thread/
│       │   ├── ThreadExtendingThreadClass.java
│       │   ├── ThreadImplementingRunnable.java
│       │   ├── ThreadCreatingRunnable.java
│       │   ├── ThreadUsingLambdaFunction.java
│       │   └── StopThreadImplementingRunnable.java
│       └── Utilities/
│           └── RomanValues.java
│
├── scala/                        # Scala implementations
│   ├── README.md
│   └── com/mounish/
│       ├── ScalaMain.scala
│       ├── Algorithms/
│       │   └── FibonacciDynamicProgramming.scala
│       ├── DataStructures/
│       │   └── SinglyLinkedList.scala
│       ├── LeetCode/
│       │   ├── TwoSum.scala
│       │   ├── ReverseInteger.scala
│       │   ├── PalindromeInteger.scala
│       │   ├── RomanToInteger.scala
│       │   ├── LongestCommonPrefix.scala
│       │   └── ValidParentheses.scala
│       └── Thread/
│           └── ScalaThreadingExamples.scala
│
├── build.sbt                     # Scala build configuration
└── README.md                     # This file
```

## Implementations

### Data Structures

| Data Structure | Java | Scala | Features |
|---------------|------|-------|----------|
| Singly Linked List | ✅ | ✅ | Insert, Delete, Count, Print, Access by index |

**Scala Improvements:**
- Both immutable (functional) and mutable versions
- Pattern matching for operations
- Type parameterization (generics)
- Tail recursion optimization

### Algorithms

| Algorithm | Java | Scala | Approach |
|-----------|------|-------|----------|
| Fibonacci (Dynamic Programming) | ✅ | ✅ | Memoization, recursion |

**Scala Improvements:**
- 6 different implementations (memoization, tail-recursive, iterative, lazy stream, matrix exponentiation)
- Uses BigInt for large numbers
- Performance benchmarks included

### LeetCode Problems

| # | Problem | Java | Scala | Difficulty |
|---|---------|------|-------|------------|
| 1 | Two Sum | ✅ | ✅ | Easy |
| 7 | Reverse Integer | ✅ | ✅ | Medium |
| 9 | Palindrome Number | ✅ | ✅ | Easy |
| 13 | Roman to Integer | ✅ | ✅ | Easy |
| 14 | Longest Common Prefix | ✅ | ✅ | Easy |
| 20 | Valid Parentheses | ✅ | ✅ | Easy |

**Scala Improvements:**
- Multiple solution approaches for each problem
- Functional programming patterns
- Pattern matching
- Option/Either types for safe error handling
- Performance comparisons

### Threading Examples

**Java Implementations:**
1. Extending Thread class
2. Implementing Runnable interface
3. Creating Runnable with anonymous class
4. Using Lambda functions (Java 8+)
5. Stoppable thread with graceful termination

**Scala Implementations:**
1. Traditional Thread and Runnable
2. Lambda/Anonymous functions
3. Stoppable threads
4. **Futures** - Modern async programming
5. **Promises** - Manual future completion
6. **Parallel Collections** - Easy parallelization
7. Producer-Consumer pattern
8. Thread pools with ExecutionContext
9. Synchronized shared resources
10. Complete concurrency examples

## Running the Code

### Java

```bash
# Compile
javac -d out src/**/*.java

# Run
java -cp out Main
```

### Scala

```bash
# Using SBT (recommended)
sbt compile
sbt "runMain com.mounish.ScalaMain"

# Run all demos
sbt "runMain com.mounish.ScalaMain --all"

# Run interactive menu
sbt "runMain com.mounish.ScalaMain --interactive"
```

See [scala/README.md](scala/README.md) for detailed Scala instructions.

## Key Features

### Java Version
- ✅ Clean, well-documented code
- ✅ Object-oriented design
- ✅ Multiple threading patterns
- ✅ LeetCode problem solutions
- ✅ Dynamic programming examples

### Scala Version
- ✅ All Java features plus:
- ✅ Functional programming approaches
- ✅ Immutable data structures
- ✅ Pattern matching
- ✅ Type safety with Option/Either
- ✅ Multiple implementation strategies
- ✅ Performance benchmarks
- ✅ Modern concurrency (Futures, Promises)
- ✅ Lazy evaluation
- ✅ Tail call optimization
- ✅ Higher-order functions

## Scala Enhancements

The Scala implementations include significant improvements:

1. **Multiple Solution Approaches** - Each problem solved in 3-7 different ways
2. **Functional Programming** - Immutable data, pure functions, higher-order functions
3. **Type Safety** - Option types prevent null pointer exceptions
4. **Pattern Matching** - More expressive than if-else chains
5. **Performance Benchmarks** - Compare different approaches
6. **Modern Concurrency** - Futures, Promises, parallel collections
7. **Better Generics** - Type parameterization with variance
8. **Lazy Evaluation** - Infinite sequences with LazyList

## Comparison: Java vs Scala

| Feature | Java | Scala |
|---------|------|-------|
| Paradigm | OOP | OOP + Functional |
| Immutability | Manual | Default |
| Null Safety | No | Yes (Option) |
| Pattern Matching | Limited | Powerful |
| Type Inference | Limited | Excellent |
| Concurrency | Threads, Executors | Futures, Actors |
| Collections | Mutable default | Immutable default |
| Verbosity | Higher | Lower |

## Learning Objectives

This repository helps you learn:

1. **Data Structures** - Implementation and usage
2. **Algorithms** - Dynamic programming, optimization
3. **Problem Solving** - LeetCode-style problems
4. **Concurrency** - Multi-threading patterns
5. **OOP Principles** - Java implementations
6. **Functional Programming** - Scala implementations
7. **Performance Analysis** - Benchmarking different approaches
8. **Design Patterns** - Multiple solution strategies

## Requirements

### Java
- JDK 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

### Scala
- Scala 2.13.x
- SBT (Scala Build Tool)
- JDK 8 or higher

## Contributing

Contributions are welcome! Please:
1. Follow the existing code style
2. Add comments and documentation
3. Include test cases
4. Update README if needed

## Future Additions

Planned implementations:
- [ ] More data structures (Stack, Queue, Trees, Graphs)
- [ ] More algorithms (Sorting, Searching, Graph algorithms)
- [ ] More LeetCode problems
- [ ] Unit tests for all implementations
- [ ] Benchmarking framework

## Author

**Mounish**
- Java implementations: Original work
- Scala implementations: Enhanced versions with functional programming

## License

This project is open source and available for educational purposes.

## Acknowledgments

- LeetCode for problem inspirations
- Scala community for functional programming patterns
- Java community for OOP best practices
