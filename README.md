# Data Structures and Algorithms

A comprehensive collection of data structures, algorithms, and LeetCode problem solutions implemented in **Java**, **Scala**, and **Python**.

## Overview

This repository contains implementations of fundamental data structures, algorithms, and solutions to common coding problems. Each implementation is available in three languages:

- **Java** - Original implementations using OOP principles
- **Scala** - Enhanced implementations with functional programming features
- **Python** - Modern Pythonic implementations with type hints and async/await

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
├── python/                       # Python implementations
│   ├── README.md
│   └── com/mounish/
│       ├── main.py               # Main entry point
│       ├── data_structures/
│       │   └── singly_linked_list.py
│       ├── algorithms/
│       │   └── fibonacci_dp.py
│       ├── leetcode/
│       │   └── problems.py       # All 6 LeetCode problems
│       └── concurrency/
│           └── examples.py       # Threading & asyncio
│
├── build.sbt                     # Scala build configuration
├── setup.py                      # Python package configuration
├── requirements.txt              # Python dependencies
└── README.md                     # This file
```

## Implementations

### Data Structures

| Data Structure | Java | Scala | Python | Features |
|---------------|------|-------|--------|----------|
| Singly Linked List | ✅ | ✅ | ✅ | Insert, Delete, Count, Print, Access by index |

**Scala Improvements:**
- Both immutable (functional) and mutable versions
- Pattern matching for operations
- Type parameterization (generics)
- Tail recursion optimization

**Python Improvements:**
- Type hints with generics
- Magic methods (`__len__`, `__iter__`, `__str__`, `__getitem__`)
- Property decorators
- Both mutable and immutable implementations
- Full Python idioms support

### Algorithms

| Algorithm | Java | Scala | Python | Approach |
|-----------|------|-------|--------|----------|
| Fibonacci (Dynamic Programming) | ✅ | ✅ | ✅ | Memoization, recursion |

**Scala Improvements:**
- 6 different implementations (memoization, tail-recursive, iterative, lazy stream, matrix exponentiation)
- Uses BigInt for large numbers
- Performance benchmarks included

**Python Improvements:**
- 7 different implementations including Binet's formula
- `@lru_cache` decorator for automatic memoization
- Generator for infinite sequences
- Performance benchmarking framework
- Type hints throughout

### LeetCode Problems

| # | Problem | Java | Scala | Python | Difficulty |
|---|---------|------|-------|--------|------------|
| 1 | Two Sum | ✅ | ✅ | ✅ | Easy |
| 7 | Reverse Integer | ✅ | ✅ | ✅ | Medium |
| 9 | Palindrome Number | ✅ | ✅ | ✅ | Easy |
| 13 | Roman to Integer | ✅ | ✅ | ✅ | Easy |
| 14 | Longest Common Prefix | ✅ | ✅ | ✅ | Easy |
| 20 | Valid Parentheses | ✅ | ✅ | ✅ | Easy |

**Scala Improvements:**
- Multiple solution approaches for each problem
- Functional programming patterns
- Pattern matching
- Option/Either types for safe error handling
- Performance comparisons

**Python Improvements:**
- Pythonic solutions using list comprehensions, zip, etc.
- Type hints throughout
- Multiple approaches per problem (3-5 each)
- Bonus features (e.g., generate valid parentheses, int to roman)
- Clean, readable code following PEP 8

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

**Python Implementations:**
1. Thread class and target functions
2. Stoppable threads with Event
3. Thread synchronization with Lock
4. Producer-Consumer with queue.Queue
5. **ThreadPoolExecutor** - Thread pools
6. **async/await** - Modern asyncio
7. **Async generators** - Async iteration
8. Async Producer-Consumer
9. Performance comparisons (Threading vs Asyncio)

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

### Python

```bash
# Interactive menu (recommended)
python -m com.mounish.main

# Run all demos
python -m com.mounish.main --all

# Run specific demonstrations
python -m com.mounish.main --ds          # Data structures
python -m com.mounish.main --algo        # Algorithms
python -m com.mounish.main --leetcode    # LeetCode problems
python -m com.mounish.main --concurrency # Threading & asyncio

# Install dependencies (optional, for testing/linting)
pip install -r requirements.txt
```

See [python/README.md](python/README.md) for detailed Python instructions.

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

### Python Version
- ✅ All Java features plus:
- ✅ Type hints (Python 3.5+)
- ✅ List comprehensions and generators
- ✅ Decorators (@lru_cache, @property, @dataclass)
- ✅ Magic methods (__len__, __iter__, etc.)
- ✅ Context managers (with statement)
- ✅ Modern async/await with asyncio
- ✅ Multiple approaches per problem
- ✅ Pythonic idioms throughout
- ✅ Performance benchmarking
- ✅ Clean, readable PEP 8 style

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

## Python Enhancements

The Python implementations showcase modern Python features:

1. **Type Hints** - Full type annotations with generics for better IDE support
2. **Pythonic Code** - List comprehensions, generators, decorators
3. **Magic Methods** - `__len__`, `__iter__`, `__str__`, `__getitem__` for natural Python usage
4. **Decorators** - `@lru_cache` for automatic memoization, `@property` for getters
5. **Async/Await** - Modern asyncio for efficient I/O-bound concurrency
6. **Multiple Approaches** - 3-7 different solutions per problem
7. **Performance Benchmarks** - Compare different approaches
8. **Clean Code** - PEP 8 compliant, readable, well-documented

## Comparison: Java vs Scala vs Python

| Feature | Java | Scala | Python |
|---------|------|-------|--------|
| Paradigm | OOP | OOP + Functional | OOP + Functional |
| Immutability | Manual | Default | Manual |
| Null Safety | No | Yes (Option) | No (but Optional) |
| Pattern Matching | Limited | Powerful | Good (3.10+) |
| Type Inference | Limited | Excellent | Excellent |
| Type System | Static | Static | Dynamic + Hints |
| Concurrency | Threads, Executors | Futures, Actors | async/await, Threads |
| Collections | Mutable default | Immutable default | Mutable default |
| Verbosity | Higher | Lower | Lowest |
| Learning Curve | Medium | Steep | Gentle |
| Performance | Fast | Fast | Moderate |
| Ecosystem | Huge | Large | Huge |

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

### Python
- Python 3.8 or higher
- pip (Python package installer)
- Optional: pytest, mypy, black (for testing and development)

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
- Python implementations: Modern Pythonic versions with type hints and async/await

## License

This project is open source and available for educational purposes.

## Acknowledgments

- LeetCode for problem inspirations
- Scala community for functional programming patterns
- Java community for OOP best practices
