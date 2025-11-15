## Python Implementation - Data Structures and Algorithms

Comprehensive Python implementations of data structures, algorithms, and LeetCode problems with modern Python features and best practices.

## 📁 Directory Structure

```
python/
└── com/mounish/
    ├── __init__.py
    ├── main.py                           # Main entry point with interactive menu
    ├── data_structures/
    │   ├── __init__.py
    │   └── singly_linked_list.py        # Mutable & immutable implementations
    ├── algorithms/
    │   ├── __init__.py
    │   └── fibonacci_dp.py              # 7 different implementations
    ├── leetcode/
    │   ├── __init__.py
    │   └── problems.py                  # All 6 LeetCode problems
    └── concurrency/
        ├── __init__.py
        └── examples.py                  # Threading & asyncio examples
```

## 🚀 Quick Start

### Installation

```bash
# Clone the repository
cd /path/to/DataStructuresAndAlgorithm

# Install dependencies (optional, for testing/linting)
pip install -r requirements.txt

# Or install in development mode
pip install -e .
```

### Running

```bash
# Interactive menu (default)
python -m com.mounish.main

# Run all demos
python -m com.mounish.main --all

# Run specific demonstrations
python -m com.mounish.main --ds          # Data structures
python -m com.mounish.main --algo        # Algorithms
python -m com.mounish.main --leetcode    # LeetCode problems
python -m com.mounish.main --concurrency # Threading examples

# Get help
python -m com.mounish.main --help
```

### Running Individual Modules

```bash
# Data Structures
python -m com.mounish.data_structures.singly_linked_list

# Algorithms
python -m com.mounish.algorithms.fibonacci_dp

# LeetCode Problems
python -m com.mounish.leetcode.problems

# Concurrency
python -m com.mounish.concurrency.examples
```

## 🎯 Features

### 1. Data Structures

**Singly Linked List** - Two implementations:

#### Mutable Version
```python
from com.mounish.data_structures.singly_linked_list import SinglyLinkedList

# Create and populate
linked_list = SinglyLinkedList[int]()
linked_list.insert(10)
linked_list.insert(20)
linked_list.insert_at_beginning(5)

# Use Python idioms
print(len(linked_list))        # 3
print(20 in linked_list)       # True
print(linked_list[0])          # 5 (supports indexing)
for value in linked_list:      # Iterator support
    print(value)
```

#### Immutable Version
```python
from com.mounish.data_structures.singly_linked_list import create_linked_list

# Functional approach
list1 = create_linked_list(1, 2, 3, 4, 5)
list2 = list1.prepend(0)       # Returns new list
list3 = list1.append(6)        # Original unchanged
```

**Key Features:**
- ✅ Type hints with generics
- ✅ Magic methods (`__len__`, `__iter__`, `__str__`, `__contains__`, `__getitem__`)
- ✅ Property decorators
- ✅ Both mutable and immutable implementations
- ✅ Full Python idioms support

### 2. Algorithms

**Fibonacci Dynamic Programming** - 7 implementations:

```python
from com.mounish.algorithms.fibonacci_dp import *

# Different approaches
result = fibonacci_iterative(50)           # O(n) time, O(1) space
result = fibonacci_lru_cache(50)           # Automatic memoization
result = fibonacci_matrix(50)              # O(log n) using matrix
result = FibonacciMemoized().calculate(50) # Manual memoization

# Generator for infinite sequence
gen = fibonacci_generator()
first_20 = [next(gen) for _ in range(20)]

# Benchmark different approaches
results = benchmark_fibonacci(30, iterations=10)
```

**Implementations:**
1. **Memoized** - Manual dictionary caching
2. **LRU Cache** - Python's `@lru_cache` decorator
3. **Iterative** - O(n) time, O(1) space
4. **Generator** - Memory-efficient infinite sequence
5. **Binet's Formula** - O(1) using golden ratio
6. **Matrix Exponentiation** - O(log n) for large numbers
7. **Tail Recursive** - Functional approach

### 3. LeetCode Problems

All 6 problems with multiple solution approaches:

#### Problem 1: Two Sum
```python
from com.mounish.leetcode.problems import TwoSum

nums = [2, 7, 11, 15]
target = 9

# Different approaches
result = TwoSum.two_sum_hash(nums, target)         # O(n) hash map
result = TwoSum.two_sum_sorting(nums, target)      # O(n log n) two pointers
result = TwoSum.two_sum_brute_force(nums, target)  # O(n²) brute force
pairs = TwoSum.find_all_two_sum_pairs(nums, target) # Find all pairs
```

#### Problem 7: Reverse Integer
```python
from com.mounish.leetcode.problems import ReverseInteger

result = ReverseInteger.reverse(123)            # 321
result = ReverseInteger.reverse_string(-123)    # -321
result = ReverseInteger.reverse(1534236469)     # 0 (overflow)
```

#### Problem 9: Palindrome Number
```python
from com.mounish.leetcode.problems import PalindromeNumber

result = PalindromeNumber.is_palindrome(121)         # True
result = PalindromeNumber.is_palindrome_string(121)  # True (Pythonic)
result = PalindromeNumber.is_palindrome(-121)        # False
```

#### Problem 13: Roman to Integer
```python
from com.mounish.leetcode.problems import RomanToInteger

result = RomanToInteger.roman_to_int("MCMXCIV")     # 1994
result = RomanToInteger.roman_to_int_reverse("IX")  # 9

# Bonus: reverse conversion
roman = RomanToInteger.int_to_roman(1994)           # "MCMXCIV"
```

#### Problem 14: Longest Common Prefix
```python
from com.mounish.leetcode.problems import LongestCommonPrefix

strs = ["flower", "flow", "flight"]
result = LongestCommonPrefix.longest_common_prefix_horizontal(strs)  # "fl"
result = LongestCommonPrefix.longest_common_prefix_zip(strs)         # "fl" (Pythonic)
```

#### Problem 20: Valid Parentheses
```python
from com.mounish.leetcode.problems import ValidParentheses

result = ValidParentheses.is_valid("()[]{}")        # True
result = ValidParentheses.is_valid("([)]")          # False

# Bonus: generate valid combinations
combos = ValidParentheses.generate_parentheses(3)   # ["((()))", "(()())", ...]
```

### 4. Concurrency

**Threading Examples:**
```python
from com.mounish.concurrency.examples import *

# Basic threading
thread = ThreadExtendingThread("Worker", 5)
thread.start()
thread.join()

# Stoppable thread
thread = StoppableThread("Stoppable")
thread.start()
time.sleep(2)
thread.stop()  # Graceful shutdown
thread.join()

# Thread-safe counter
counter = Counter()
threads = [Thread(target=counter.increment) for _ in range(10)]
# ... all threads complete safely

# Thread pool
with ThreadPoolExecutor(max_workers=4) as executor:
    futures = [executor.submit(task_function, i) for i in range(10)]
    results = [f.result() for f in futures]
```

**Async/Await Examples:**
```python
import asyncio
from com.mounish.concurrency.examples import *

# Run async tasks
async def main():
    tasks = [async_task(i, 0.5) for i in range(5)]
    results = await asyncio.gather(*tasks)
    return results

results = asyncio.run(main())

# Async producer-consumer
asyncio.run(demo_asyncio_producer_consumer())
```

## 🐍 Python Features Demonstrated

### Modern Python (3.8+)
- ✅ **Type Hints** - Full type annotations with generics
- ✅ **Dataclasses** - Simplified class definitions
- ✅ **F-strings** - Modern string formatting
- ✅ **Walrus Operator** - Assignment expressions (`:=`)
- ✅ **Positional-only parameters** - `/` syntax

### Pythonic Idioms
- ✅ **List Comprehensions** - `[x*2 for x in nums]`
- ✅ **Generator Expressions** - `(x*2 for x in nums)`
- ✅ **Decorators** - `@lru_cache`, `@property`, `@dataclass`
- ✅ **Context Managers** - `with` statement
- ✅ **Magic Methods** - `__len__`, `__iter__`, `__str__`, etc.
- ✅ **Zip and Enumerate** - Pythonic iteration
- ✅ **Unpacking** - `a, b = b, a` for swaps

### Functional Programming
- ✅ **Lambda Functions** - Anonymous functions
- ✅ **Map, Filter, Reduce** - Functional operations
- ✅ **Generators** - Lazy evaluation
- ✅ **Itertools** - Advanced iteration tools

### Async Programming
- ✅ **async/await** - Modern async syntax
- ✅ **asyncio** - Async I/O framework
- ✅ **Async generators** - `async for` loops
- ✅ **Concurrent futures** - Thread and process pools

## 📊 Comparison: Java vs Scala vs Python

| Feature | Java | Scala | Python |
|---------|------|-------|--------|
| **Type System** | Static | Static | Dynamic + Type Hints |
| **Verbosity** | High | Medium | Low |
| **Null Safety** | No | Yes (Option) | No (but has Optional) |
| **Immutability** | Manual | Default | Manual |
| **Functional** | Limited | Full | Good |
| **Async** | CompletableFuture | Futures | async/await |
| **Generics** | Yes | Yes (better) | Yes (3.5+ type hints) |
| **Performance** | Fast | Fast | Slower (but fast enough) |
| **Learning Curve** | Medium | Steep | Gentle |
| **Ecosystem** | Huge | Large | Huge |

## 🎓 Key Improvements Over Java/Scala

### Simplicity
```python
# Python - concise and readable
def fibonacci(n):
    a, b = 0, 1
    for _ in range(n):
        a, b = b, a + b
    return a

# vs Java - more verbose
public static long fibonacci(int n) {
    long a = 0, b = 1;
    for (int i = 0; i < n; i++) {
        long temp = a;
        a = b;
        b = temp + b;
    }
    return a;
}
```

### Pythonic Features
```python
# List comprehension
squares = [x**2 for x in range(10)]

# Generator expression
fibonacci_gen = (fib(i) for i in range(100))

# Multiple return values
def get_stats(nums):
    return min(nums), max(nums), sum(nums) / len(nums)

min_val, max_val, avg = get_stats([1, 2, 3, 4, 5])

# Dictionary comprehension
char_count = {char: text.count(char) for char in set(text)}

# Zip for parallel iteration
for num, square in zip(range(5), [0, 1, 4, 9, 16]):
    print(f"{num}² = {square}")
```

### Decorators
```python
from functools import lru_cache
import time

# Automatic memoization
@lru_cache(maxsize=None)
def fibonacci(n):
    if n <= 1:
        return n
    return fibonacci(n-1) + fibonacci(n-2)

# Timing decorator
def timer(func):
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        print(f"{func.__name__} took {time.time() - start:.3f}s")
        return result
    return wrapper

@timer
def slow_function():
    time.sleep(1)
```

## 🧪 Testing

```bash
# Run tests (if pytest is installed)
pytest python/tests/

# With coverage
pytest --cov=com.mounish python/tests/

# Type checking
mypy python/com/mounish/

# Linting
pylint python/com/mounish/
flake8 python/com/mounish/

# Formatting
black python/com/mounish/
```

## 📈 Performance Tips

1. **Use built-in functions** - They're implemented in C
2. **List comprehensions** - Faster than explicit loops
3. **Generators** - Memory-efficient for large sequences
4. **@lru_cache** - Automatic memoization
5. **numpy** - For numerical computations (not included here)
6. **asyncio** - For I/O-bound concurrency
7. **multiprocessing** - For CPU-bound parallelism

## 🔧 Requirements

- **Python 3.8+** - Modern features require recent Python
- **No external dependencies** - All implementations use standard library
- **Optional**: pytest, mypy, black (for development)

## 📚 Learning Resources

Each implementation includes:
- ✅ Comprehensive docstrings
- ✅ Type hints throughout
- ✅ Multiple solution approaches
- ✅ Performance benchmarks
- ✅ Example usage in demos
- ✅ Comments explaining algorithms

## 🤝 Contributing

When adding new implementations:
1. Use type hints
2. Follow PEP 8 style guide
3. Include docstrings
4. Provide multiple approaches
5. Add performance benchmarks
6. Include demo examples

## 📄 License

Same as parent project - open source for educational purposes.

## 👤 Author

**Mounish** - Python implementations with modern features and best practices

---

**Happy Coding! 🐍**
