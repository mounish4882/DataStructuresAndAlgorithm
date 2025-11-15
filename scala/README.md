# Scala Implementation of Data Structures and Algorithms

This directory contains Scala implementations of all the data structures, algorithms, and LeetCode problems originally implemented in Java, with significant improvements and enhancements.

## Directory Structure

```
scala/
└── com/mounish/
    ├── ScalaMain.scala                           # Main entry point
    ├── Algorithms/
    │   └── FibonacciDynamicProgramming.scala    # Multiple Fibonacci implementations
    ├── DataStructures/
    │   └── SinglyLinkedList.scala               # Immutable & mutable linked lists
    ├── LeetCode/
    │   ├── TwoSum.scala                         # Problem 1
    │   ├── ReverseInteger.scala                 # Problem 7
    │   ├── PalindromeInteger.scala              # Problem 9
    │   ├── RomanToInteger.scala                 # Problem 13
    │   ├── LongestCommonPrefix.scala            # Problem 14
    │   └── ValidParentheses.scala               # Problem 20
    └── Thread/
        └── ScalaThreadingExamples.scala         # Threading & concurrency
```

## Building and Running

### Using SBT (Scala Build Tool)

```bash
# From the project root directory
cd /path/to/DataStructuresAndAlgorithm

# Compile
sbt compile

# Run interactive menu
sbt "runMain com.mounish.ScalaMain"

# Run all demos
sbt "runMain com.mounish.ScalaMain --all"

# Run with Scala REPL
sbt console
```

### Direct Compilation with scalac

```bash
cd scala

# Compile all files
scalac -d ../out com/mounish/**/*.scala

# Run
scala -cp ../out com.mounish.ScalaMain
```

## Key Improvements Over Java Implementation

### 1. Data Structures

**Immutable LinkedList:**
- Functional, immutable implementation using sealed traits
- Pattern matching for operations
- Tail recursion for better performance
- Type parameterized (generic)

**Mutable LinkedList:**
- Similar to Java version but with Scala idioms
- Uses Option types instead of null
- Better encapsulation

### 2. Algorithms

**Fibonacci Dynamic Programming:**
- **5 different implementations:**
  1. Memoization with mutable HashMap
  2. Pure functional with immutable Map
  3. Tail-recursive with accumulator (most efficient)
  4. Iterative approach
  5. Lazy Stream (infinite Fibonacci sequence)
  6. Matrix exponentiation (O(log n))
- Uses BigInt for handling large numbers
- Performance benchmarks included

### 3. LeetCode Problems

Each problem includes multiple solution approaches:

**Two Sum:**
- Hash map approach
- Functional approach with collectFirst
- Sorting with two pointers
- Brute force
- Find all pairs variant

**Reverse Integer:**
- Iterative with overflow check
- Tail-recursive functional
- String-based
- Using Long for overflow detection
- Safe version with Option return type

**Palindrome Integer:**
- Half-reversal optimization
- String-based
- Full reversal
- Functional recursive
- Digit comparison
- List-based

**Roman to Integer:**
- Iterative approach
- Functional with foldLeft
- Pattern matching
- Reverse iteration
- Sliding window
- Bonus: Integer to Roman conversion

**Longest Common Prefix:**
- Horizontal scanning
- Vertical scanning
- Functional with foldLeft
- Reduce
- Binary search
- Divide and conquer
- Trie-based
- Transpose (elegant functional)

**Valid Parentheses:**
- Stack-based (mutable)
- Pattern matching with immutable List
- Functional with foldLeft
- Error reporting with Either
- Advanced: Can be made valid with k removals
- Bonus: Generate all valid parentheses

### 4. Threading & Concurrency

**Modern Scala Concurrency:**
- Traditional Thread and Runnable
- Lambda/anonymous functions
- Stoppable threads with volatile flags
- **Futures** - Async computation
- **Promises** - Manual future completion
- **Parallel Collections** - Easy parallelization
- Producer-Consumer pattern
- Thread pools with ExecutionContext
- Synchronized access to shared resources

## Scala Features Demonstrated

1. **Immutability** - Immutable data structures by default
2. **Pattern Matching** - For cleaner, more expressive code
3. **Higher-Order Functions** - map, filter, fold, etc.
4. **Type Inference** - Less verbose than Java
5. **Option Types** - Safe null handling
6. **Either Types** - Error handling with context
7. **For-Comprehensions** - Elegant composition
8. **Tail Call Optimization** - Efficient recursion
9. **Case Classes & Sealed Traits** - Algebraic data types
10. **Lazy Evaluation** - LazyList for infinite sequences
11. **Futures & Promises** - Modern async programming
12. **Type Parameterization** - Powerful generics

## Performance

All implementations include performance benchmarks comparing different approaches:
- Time complexity analysis
- Space complexity analysis
- Real-world execution time measurements
- Comparison between functional and imperative styles

## Running Individual Demos

You can run specific demos from the Scala REPL:

```scala
// From sbt console
import com.mounish.DataStructures.SinglyLinkedList
SinglyLinkedList.demo()

import com.mounish.Algorithms.FibonacciDynamicProgramming
FibonacciDynamicProgramming.demo()

import com.mounish.LeetCode.TwoSum
TwoSum.demo()

import com.mounish.Thread.ScalaThreadingExamples
ScalaThreadingExamples.demo()
```

## Testing

Tests can be added using ScalaTest (already included in build.sbt):

```bash
sbt test
```

## Comparison: Java vs Scala

| Aspect | Java | Scala |
|--------|------|-------|
| **Verbosity** | More verbose | Concise, type inference |
| **Null Safety** | Null pointer exceptions | Option types |
| **Immutability** | Manual (final) | Default for vals |
| **Pattern Matching** | Switch (limited) | Powerful pattern matching |
| **Functional Programming** | Limited (since Java 8) | First-class support |
| **Concurrency** | Threads, ExecutorService | Futures, Actors, Parallel Collections |
| **Type System** | Strong, static | Stronger with advanced features |
| **Collections** | Mutable by default | Immutable by default |

## Code Quality Improvements

1. **Type Safety** - Option and Either types prevent null pointer exceptions
2. **Immutability** - Reduces bugs from shared mutable state
3. **Functional Composition** - Easier to reason about and test
4. **Pattern Matching** - More expressive than if-else chains
5. **Tail Recursion** - Stack-safe recursive functions
6. **Higher-Order Functions** - More reusable, composable code

## Learning Resources

- Each file is heavily commented with explanations
- Multiple implementation approaches for comparison
- Performance benchmarks for understanding trade-offs
- Pattern matching examples
- Functional programming patterns

## Contributing

When adding new implementations:
1. Provide multiple solution approaches
2. Include performance benchmarks
3. Add comprehensive comments
4. Demonstrate Scala-specific features
5. Include demo() method with test cases

## License

Same as the parent Java project.

## Author

Mounish - Scala implementations with improvements
