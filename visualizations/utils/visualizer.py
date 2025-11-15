#!/usr/bin/env python3
"""
Interactive visualization tool for data structures and algorithms.

Usage:
    python visualizer.py --demo linked-list
    python visualizer.py --demo fibonacci --steps 10
    python visualizer.py --compare languages
"""

import argparse
import sys
from typing import List, Optional


class LinkedListVisualizer:
    """Visualize linked list operations in ASCII art."""

    def __init__(self):
        self.nodes = []

    def add(self, value):
        """Add a node to visualization."""
        self.nodes.append(value)
        return self

    def display(self, title="Linked List"):
        """Display linked list as ASCII art."""
        print(f"\n{'=' * 60}")
        print(f"{title:^60}")
        print('=' * 60)

        if not self.nodes:
            print("\n  HEAD: NULL\n")
            return

        # Draw nodes
        print("\n  HEAD")
        print("   ↓")

        # Top line
        for i, node in enumerate(self.nodes):
            print("  ┌─────┬──────┐", end="")
            if i < len(self.nodes) - 1:
                print("    ", end="")
        print()

        # Middle line with data
        for i, node in enumerate(self.nodes):
            print(f"  │ {str(node):^3} │  •───┼", end="")
            if i < len(self.nodes) - 1:
                print("───>", end="")
            else:
                print("───> NULL")
                break
        print()

        # Bottom line
        for i, node in enumerate(self.nodes):
            print("  └─────┴──────┘", end="")
            if i < len(self.nodes) - 1:
                print("    ", end="")
        print("\n")

    def display_operation(self, operation, value=None):
        """Display an operation being performed."""
        if operation == "insert":
            print(f"\n🔹 Operation: INSERT {value}")
            self.add(value)
            self.display("After Insertion")
        elif operation == "delete":
            print(f"\n🔹 Operation: DELETE {value}")
            if value in self.nodes:
                self.nodes.remove(value)
            self.display("After Deletion")
        elif operation == "search":
            print(f"\n🔹 Operation: SEARCH for {value}")
            if value in self.nodes:
                idx = self.nodes.index(value)
                print(f"   ✓ Found at position {idx}")
            else:
                print(f"   ✗ Not found")

    def demo(self):
        """Run interactive demo."""
        print("\n" + "=" * 60)
        print("LINKED LIST VISUALIZATION DEMO".center(60))
        print("=" * 60)

        print("\n📝 Starting with empty list...")
        self.display("Empty List")

        print("\n➕ Inserting: 10, 20, 30, 40")
        self.add(10).add(20).add(30).add(40)
        self.display("After Insertions")

        print("\n🔍 Searching for 20...")
        self.display_operation("search", 20)

        print("\n❌ Deleting 20...")
        self.display_operation("delete", 20)

        print("\n✓ Demo complete!")


class FibonacciVisualizer:
    """Visualize Fibonacci computation."""

    def __init__(self):
        self.call_tree = []
        self.memo = {0: 0, 1: 1}

    def visualize_recursive_calls(self, n, max_depth=4):
        """Show recursive call tree."""
        print(f"\n{'=' * 60}")
        print(f"FIBONACCI RECURSIVE CALLS: F({n})".center(60))
        print('=' * 60)

        def print_tree(n, depth=0, prefix=""):
            if depth > max_depth:
                return

            indent = "  " * depth
            if depth == 0:
                print(f"\n{prefix}F({n})")
            else:
                print(f"{indent}{prefix}├── F({n})")

            if n <= 1:
                print(f"{indent}    └── Base case: {n}")
                return

            print_tree(n - 1, depth + 1, "    ")
            print_tree(n - 2, depth + 1, "    ")

        print_tree(n)
        print()

    def visualize_memoization(self, n):
        """Show memoization table."""
        print(f"\n{'=' * 60}")
        print(f"FIBONACCI MEMOIZATION TABLE".center(60))
        print('=' * 60)

        # Compute with memoization
        def fib(n):
            if n in self.memo:
                return self.memo[n]
            self.memo[n] = fib(n - 1) + fib(n - 2)
            return self.memo[n]

        # Compute up to n
        result = fib(n)

        # Display table
        print("\n  ┌" + "─────┬" * min(n + 1, 11) + "")
        print("  │", end="")
        for i in range(min(n + 1, 11)):
            print(f"  {i:2} │", end="")
        print("\n  ├" + "─────┼" * min(n + 1, 11) + "")
        print("  │", end="")
        for i in range(min(n + 1, 11)):
            print(f" {self.memo[i]:3} │", end="")
        print("\n  └" + "─────┴" * min(n + 1, 11) + "")

        if n > 10:
            print(f"\n  ... (showing first 11 values)")
            print(f"\n  F({n}) = {result}")

        print()

    def visualize_steps(self, n):
        """Show step-by-step computation."""
        print(f"\n{'=' * 60}")
        print(f"FIBONACCI STEP-BY-STEP: F({n})".center(60))
        print('=' * 60)

        memo = {0: 0, 1: 1}

        print("\n  Initial memo: {0: 0, 1: 1}\n")

        for i in range(2, n + 1):
            val = memo[i - 1] + memo[i - 2]
            memo[i] = val
            print(f"  Step {i - 1}: F({i}) = F({i-1}) + F({i-2}) = {memo[i-1]} + {memo[i-2]} = {val}")

        print(f"\n  ✓ Final result: F({n}) = {memo[n]}\n")

    def demo(self, n=7):
        """Run Fibonacci demo."""
        self.visualize_recursive_calls(n, max_depth=3)
        self.visualize_memoization(n)
        self.visualize_steps(n)


class LanguageComparison:
    """Compare implementations across languages."""

    def display(self):
        """Show language comparison."""
        print("\n" + "=" * 80)
        print("LANGUAGE COMPARISON: Java vs Scala vs Python".center(80))
        print("=" * 80)

        # Comparison table
        features = [
            ("Feature", "Java", "Scala", "Python"),
            ("─" * 20, "─" * 15, "─" * 15, "─" * 15),
            ("Type System", "Static", "Static", "Dynamic+Hints"),
            ("Null Safety", "No", "Yes (Option)", "No (Optional)"),
            ("Pattern Matching", "Limited", "Powerful", "Good (3.10+)"),
            ("Immutability", "Manual", "Default", "Manual"),
            ("Concurrency", "Threads", "Futures/Actors", "async/await"),
            ("Learning Curve", "Medium", "Steep", "Gentle"),
            ("Performance", "Fast ⚡⚡", "Fast ⚡⚡", "Moderate ⚡"),
            ("Ecosystem", "Huge", "Large", "Huge"),
            ("Best For", "Enterprise", "Spark/Scala", "Data Science"),
        ]

        # Print table
        for row in features:
            print(f"  {row[0]:<20} │ {row[1]:<15} │ {row[2]:<15} │ {row[3]:<15}")

        print("\n" + "=" * 80)

        # Use cases
        print("\n📊 WHEN TO USE EACH LANGUAGE\n")

        print("  ☕ Java:")
        print("     ✓ Production data platforms")
        print("     ✓ Apache Flink/Beam")
        print("     ✓ Enterprise ETL")
        print("     ✓ Microservices\n")

        print("  🔷 Scala:")
        print("     ✓ Apache Spark pipelines")
        print("     ✓ Functional ETL")
        print("     ✓ Type-safe data processing")
        print("     ✓ Real-time streaming\n")

        print("  🐍 Python:")
        print("     ✓ Data analysis & ML")
        print("     ✓ Rapid prototyping")
        print("     ✓ Jupyter notebooks")
        print("     ✓ Pandas/NumPy workflows\n")

        print("=" * 80 + "\n")


class PerformanceVisualizer:
    """Visualize performance comparisons."""

    def display(self, algorithm="fibonacci"):
        """Show performance comparison."""
        print("\n" + "=" * 80)
        print(f"PERFORMANCE COMPARISON: {algorithm.title()}".center(80))
        print("=" * 80)

        if algorithm == "fibonacci":
            print("\n  Computing F(35):\n")

            approaches = [
                ("Naive Recursion", 5.2, 15),
                ("Memoization", 0.0001, 35),
                ("Iterative", 0.00005, 35),
                ("Matrix", 0.00001, 35),
            ]

            max_time = max(t for _, t, _ in approaches)

            for name, time, ops in approaches:
                bar_length = int((time / max_time) * 50)
                bar = "█" * bar_length
                print(f"  {name:<20} │ {time:>10.5f}s │ {bar}")

            print("\n  ⚡ Memoization is 52,000x FASTER!\n")

        print("=" * 80 + "\n")


def main():
    """Main entry point."""
    parser = argparse.ArgumentParser(
        description="Visualize data structures and algorithms",
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
Examples:
  python visualizer.py --demo linked-list
  python visualizer.py --demo fibonacci --steps 10
  python visualizer.py --compare languages
  python visualizer.py --compare performance
        """
    )

    parser.add_argument(
        "--demo",
        choices=["linked-list", "fibonacci"],
        help="Run interactive demo"
    )

    parser.add_argument(
        "--steps",
        type=int,
        default=7,
        help="Number of steps for Fibonacci demo"
    )

    parser.add_argument(
        "--compare",
        choices=["languages", "performance"],
        help="Show comparison charts"
    )

    args = parser.parse_args()

    if args.demo == "linked-list":
        viz = LinkedListVisualizer()
        viz.demo()
    elif args.demo == "fibonacci":
        viz = FibonacciVisualizer()
        viz.demo(args.steps)
    elif args.compare == "languages":
        viz = LanguageComparison()
        viz.display()
    elif args.compare == "performance":
        viz = PerformanceVisualizer()
        viz.display("fibonacci")
    else:
        parser.print_help()


if __name__ == "__main__":
    main()
