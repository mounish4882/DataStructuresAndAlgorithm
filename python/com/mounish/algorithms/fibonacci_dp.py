"""
Fibonacci implementations with Dynamic Programming and various optimizations.

Features:
- Multiple implementation strategies
- Memoization with decorators
- Generator-based approach
- Matrix exponentiation
- Performance benchmarking
"""

from typing import Dict, Iterator, Tuple
from functools import lru_cache
import time


class FibonacciMemoized:
    """
    Fibonacci with memoization using dictionary (similar to Java version).

    Improvements over Java:
    - More Pythonic with dictionary
    - Type hints
    - Property decorator for cleaner API
    """

    def __init__(self):
        self._memo: Dict[int, int] = {0: 0, 1: 1}

    def calculate(self, n: int) -> int:
        """Calculate nth Fibonacci number with memoization."""
        if n in self._memo:
            return self._memo[n]

        self._memo[n] = self.calculate(n - 1) + self.calculate(n - 2)
        return self._memo[n]

    def clear_cache(self) -> None:
        """Clear the memoization cache."""
        self._memo = {0: 0, 1: 1}


@lru_cache(maxsize=None)
def fibonacci_lru_cache(n: int) -> int:
    """
    Fibonacci using Python's built-in LRU cache decorator.

    Most Pythonic approach - automatic memoization.
    """
    if n <= 1:
        return n
    return fibonacci_lru_cache(n - 1) + fibonacci_lru_cache(n - 2)


def fibonacci_iterative(n: int) -> int:
    """
    Iterative approach - O(n) time, O(1) space.

    Most efficient for single calculations.
    """
    if n <= 1:
        return n

    a, b = 0, 1
    for _ in range(2, n + 1):
        a, b = b, a + b

    return b


def fibonacci_generator() -> Iterator[int]:
    """
    Generator that yields infinite Fibonacci sequence.

    Most Pythonic and memory-efficient for sequences.
    """
    a, b = 0, 1
    while True:
        yield a
        a, b = b, a + b


def fibonacci_from_generator(n: int) -> int:
    """Get nth Fibonacci number from generator."""
    gen = fibonacci_generator()
    for _ in range(n):
        next(gen)
    return next(gen)


def fibonacci_binet(n: int) -> int:
    """
    Binet's formula - O(1) time using golden ratio.

    Note: Limited by floating point precision for large n.
    """
    phi = (1 + 5 ** 0.5) / 2
    psi = (1 - 5 ** 0.5) / 2
    return int((phi ** n - psi ** n) / 5 ** 0.5)


def fibonacci_matrix(n: int) -> int:
    """
    Matrix exponentiation approach - O(log n) time.

    Most efficient for very large n.
    """
    def matrix_multiply(a: list, b: list) -> list:
        """Multiply two 2x2 matrices."""
        return [
            [a[0][0] * b[0][0] + a[0][1] * b[1][0],
             a[0][0] * b[0][1] + a[0][1] * b[1][1]],
            [a[1][0] * b[0][0] + a[1][1] * b[1][0],
             a[1][0] * b[0][1] + a[1][1] * b[1][1]]
        ]

    def matrix_power(matrix: list, n: int) -> list:
        """Raise matrix to power n using binary exponentiation."""
        if n == 1:
            return matrix

        if n % 2 == 0:
            half = matrix_power(matrix, n // 2)
            return matrix_multiply(half, half)
        else:
            return matrix_multiply(matrix, matrix_power(matrix, n - 1))

    if n == 0:
        return 0
    if n == 1:
        return 1

    base_matrix = [[1, 1], [1, 0]]
    result_matrix = matrix_power(base_matrix, n)
    return result_matrix[0][1]


def fibonacci_tail_recursive(n: int, a: int = 0, b: int = 1) -> int:
    """
    Tail-recursive implementation.

    Note: Python doesn't optimize tail recursion, but included for completeness.
    """
    if n == 0:
        return a
    if n == 1:
        return b
    return fibonacci_tail_recursive(n - 1, b, a + b)


def get_fibonacci_sequence(n: int) -> list:
    """Get first n Fibonacci numbers as a list."""
    if n <= 0:
        return []
    if n == 1:
        return [0]

    sequence = [0, 1]
    for i in range(2, n):
        sequence.append(sequence[i-1] + sequence[i-2])

    return sequence


def get_fibonacci_sequence_generator(n: int) -> list:
    """Get first n Fibonacci numbers using generator (memory efficient)."""
    gen = fibonacci_generator()
    return [next(gen) for _ in range(n)]


def benchmark_fibonacci(n: int, iterations: int = 1) -> Dict[str, Tuple[int, float]]:
    """
    Benchmark different Fibonacci implementations.

    Returns: Dictionary mapping method name to (result, time_ms) tuple.
    """
    results = {}

    # Memoized approach
    fib_memo = FibonacciMemoized()
    start = time.perf_counter()
    for _ in range(iterations):
        result = fib_memo.calculate(n)
    duration = (time.perf_counter() - start) * 1000
    results["Memoized"] = (result, duration)
    fib_memo.clear_cache()

    # LRU Cache
    fibonacci_lru_cache.cache_clear()
    start = time.perf_counter()
    for _ in range(iterations):
        result = fibonacci_lru_cache(n)
    duration = (time.perf_counter() - start) * 1000
    results["LRU Cache"] = (result, duration)

    # Iterative
    start = time.perf_counter()
    for _ in range(iterations):
        result = fibonacci_iterative(n)
    duration = (time.perf_counter() - start) * 1000
    results["Iterative"] = (result, duration)

    # Generator
    start = time.perf_counter()
    for _ in range(iterations):
        result = fibonacci_from_generator(n)
    duration = (time.perf_counter() - start) * 1000
    results["Generator"] = (result, duration)

    # Matrix (for reasonable n values)
    if n < 1000:
        start = time.perf_counter()
        for _ in range(iterations):
            result = fibonacci_matrix(n)
        duration = (time.perf_counter() - start) * 1000
        results["Matrix"] = (result, duration)

    # Binet (accurate for small n)
    if n < 70:
        start = time.perf_counter()
        for _ in range(iterations):
            result = fibonacci_binet(n)
        duration = (time.perf_counter() - start) * 1000
        results["Binet"] = (result, duration)

    # Tail Recursive (only for small n due to Python recursion limit)
    if n < 100:
        start = time.perf_counter()
        for _ in range(iterations):
            result = fibonacci_tail_recursive(n)
        duration = (time.perf_counter() - start) * 1000
        results["Tail Recursive"] = (result, duration)

    return results


def demo() -> None:
    """Demonstrate Fibonacci implementations."""
    print("=" * 80)
    print("FIBONACCI DYNAMIC PROGRAMMING DEMO")
    print("=" * 80)

    # Test values
    test_values = [10, 20, 30, 40, 50]

    print("\n=== Fibonacci Values ===")
    for n in test_values:
        result = fibonacci_iterative(n)
        print(f"fib({n:2d}) = {result:>15,}")

    # Show first 20 Fibonacci numbers
    print("\n=== First 20 Fibonacci Numbers ===")
    sequence = get_fibonacci_sequence(20)
    print(", ".join(str(n) for n in sequence))

    # Performance comparison
    print("\n=== Performance Comparison (n=30) ===")
    results = benchmark_fibonacci(30, iterations=10)

    print(f"{'Method':<20} {'Result':<20} {'Time (ms)':<15}")
    print("-" * 55)
    for method, (result, time_ms) in results.items():
        print(f"{method:<20} {result:<20,} {time_ms:>10.3f}")

    # Large Fibonacci number
    print("\n=== Large Fibonacci Numbers ===")
    large_n = 100
    result = fibonacci_iterative(large_n)
    print(f"fib({large_n}) = {result:,}")
    print(f"Number of digits: {len(str(result))}")

    # Very large using matrix exponentiation
    print("\n=== Very Large Fibonacci (Matrix Method) ===")
    very_large_n = 1000
    result = fibonacci_matrix(very_large_n)
    result_str = str(result)
    print(f"fib({very_large_n}) has {len(result_str)} digits")
    print(f"First 50 digits: {result_str[:50]}...")
    print(f"Last 50 digits: ...{result_str[-50:]}")

    # Generator demonstration
    print("\n=== Using Generator for First 15 Numbers ===")
    gen = fibonacci_generator()
    fib_list = [next(gen) for _ in range(15)]
    print(", ".join(str(n) for n in fib_list))

    # Memoization demonstration
    print("\n=== Memoization Cache Demo ===")
    fib_memo = FibonacciMemoized()
    print("Calculating fib(40) for the first time...")
    start = time.perf_counter()
    result1 = fib_memo.calculate(40)
    time1 = (time.perf_counter() - start) * 1000
    print(f"Result: {result1:,}, Time: {time1:.3f} ms")

    print("Calculating fib(40) second time (cached)...")
    start = time.perf_counter()
    result2 = fib_memo.calculate(40)
    time2 = (time.perf_counter() - start) * 1000
    print(f"Result: {result2:,}, Time: {time2:.3f} ms")
    print(f"Speedup: {time1/time2:.1f}x faster")

    # Verification - all methods should agree
    print("\n=== Verification (n=20) ===")
    n = 20
    methods = {
        "Memoized": FibonacciMemoized().calculate(n),
        "LRU Cache": fibonacci_lru_cache(n),
        "Iterative": fibonacci_iterative(n),
        "Generator": fibonacci_from_generator(n),
        "Matrix": fibonacci_matrix(n),
        "Binet": fibonacci_binet(n),
        "Tail Recursive": fibonacci_tail_recursive(n),
    }

    all_same = len(set(methods.values())) == 1
    print(f"All methods agree: {all_same} ✓" if all_same else f"Methods disagree! ✗")
    for method, result in methods.items():
        print(f"  {method:<20}: {result}")

    print("\n" + "=" * 80)


if __name__ == "__main__":
    demo()
