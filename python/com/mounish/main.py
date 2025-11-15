#!/usr/bin/env python3
"""
Main entry point for Python Data Structures and Algorithms demonstrations.

Run with:
    python -m com.mounish.main
    python -m com.mounish.main --all
    python -m com.mounish.main --help
"""

import sys
import argparse
from typing import Callable, Dict

# Import all demo modules
from com.mounish.data_structures import singly_linked_list
from com.mounish.algorithms import fibonacci_dp
from com.mounish.leetcode import problems
from com.mounish.concurrency import examples


def print_section_header(title: str, width: int = 80) -> None:
    """Print a formatted section header."""
    print("\n" + "=" * width)
    padding = (width - len(title) - 2) // 2
    print("=" * padding + f" {title} " + "=" * padding)
    print("=" * width + "\n")


def print_banner() -> None:
    """Print the main banner."""
    banner = """
    ╔═══════════════════════════════════════════════════════════════════════════╗
    ║                                                                           ║
    ║          DATA STRUCTURES AND ALGORITHMS - PYTHON IMPLEMENTATION           ║
    ║                                                                           ║
    ║                  Comprehensive demonstrations of:                         ║
    ║                  • Data Structures                                        ║
    ║                  • Algorithms                                             ║
    ║                  • LeetCode Problems                                      ║
    ║                  • Threading & Concurrency                                ║
    ║                                                                           ║
    ╚═══════════════════════════════════════════════════════════════════════════╝
    """
    print(banner)


def demo_data_structures() -> None:
    """Run data structures demonstrations."""
    print_section_header("DATA STRUCTURES")
    singly_linked_list.demo()


def demo_algorithms() -> None:
    """Run algorithms demonstrations."""
    print_section_header("ALGORITHMS")
    fibonacci_dp.demo()


def demo_leetcode() -> None:
    """Run LeetCode problems demonstrations."""
    print_section_header("LEETCODE PROBLEMS")
    problems.demo()


def demo_concurrency() -> None:
    """Run concurrency demonstrations."""
    print_section_header("THREADING & CONCURRENCY")
    examples.demo()


def run_all_demos() -> None:
    """Run all demonstrations in sequence."""
    print_banner()
    print_section_header("RUNNING ALL DEMONSTRATIONS")

    print("This will run all demonstrations:")
    print("  1. Data Structures (Linked List)")
    print("  2. Algorithms (Fibonacci DP)")
    print("  3. LeetCode Problems (All 6 problems)")
    print("  4. Threading & Concurrency")
    print()

    demo_data_structures()
    demo_algorithms()
    demo_leetcode()
    demo_concurrency()

    print_section_header("ALL DEMONSTRATIONS COMPLETED SUCCESSFULLY")

    print("""
Summary of Python Implementations:

1. DATA STRUCTURES:
   • Both mutable and immutable linked lists
   • Type hints for type safety
   • Pythonic idioms (iterators, generators, __magic__ methods)
   • Property decorators
   • Context manager support

2. ALGORITHMS:
   • 7 different Fibonacci implementations
   • @lru_cache decorator for automatic memoization
   • Generator for infinite sequences
   • Matrix exponentiation (O(log n))
   • Binet's formula
   • Performance benchmarking

3. LEETCODE PROBLEMS:
   • All 6 problems with multiple solution approaches
   • Pythonic code using list comprehensions, zip, etc.
   • Type hints throughout
   • Bonus features (e.g., generate valid parentheses)

4. THREADING & CONCURRENCY:
   • Traditional threading with Thread class
   • Thread pools with ThreadPoolExecutor
   • Synchronization with Lock and Event
   • Producer-Consumer with queue
   • Modern async/await with asyncio
   • Performance comparisons

Key Python Features Demonstrated:
   • Type hints (Python 3.5+)
   • Dataclasses
   • List comprehensions and generator expressions
   • Decorators (@lru_cache, @property)
   • Context managers (with statement)
   • Magic methods (__len__, __iter__, __str__, etc.)
   • f-strings for formatting
   • Pattern matching (structural matching)
   • Asyncio for concurrent I/O
   • ThreadPoolExecutor and ProcessPoolExecutor

Performance:
   All implementations include benchmarks comparing different approaches
   to help understand time/space trade-offs.
""")


def interactive_menu() -> None:
    """Run interactive menu for selecting demos."""
    demos: Dict[str, Callable] = {
        "1": ("Data Structures (Linked List)", demo_data_structures),
        "2": ("Algorithms (Fibonacci DP)", demo_algorithms),
        "3": ("LeetCode Problems (All 6 problems)", demo_leetcode),
        "4": ("Threading & Concurrency", demo_concurrency),
        "5": ("Run All Demos", run_all_demos),
    }

    while True:
        print_banner()
        print_section_header("MAIN MENU")

        print("Select a demonstration to run:\n")
        for key, (description, _) in demos.items():
            print(f"  {key}. {description}")
        print("  6. Exit")

        choice = input("\nEnter your choice (1-6): ").strip()

        if choice == "6":
            print("\nExiting... Goodbye!")
            break

        if choice in demos:
            _, demo_func = demos[choice]
            try:
                demo_func()
                input("\nPress Enter to continue...")
            except KeyboardInterrupt:
                print("\n\nDemo interrupted by user.")
                input("\nPress Enter to continue...")
            except Exception as e:
                print(f"\nError running demo: {e}")
                import traceback
                traceback.print_exc()
                input("\nPress Enter to continue...")
        else:
            print("\nInvalid choice. Please enter a number between 1 and 6.")
            input("Press Enter to continue...")


def main() -> int:
    """Main entry point."""
    parser = argparse.ArgumentParser(
        description="Data Structures and Algorithms - Python Implementation",
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
Examples:
  python -m com.mounish.main              # Interactive menu
  python -m com.mounish.main --all        # Run all demos
  python -m com.mounish.main --ds         # Run data structures only
  python -m com.mounish.main --algo       # Run algorithms only
  python -m com.mounish.main --leetcode   # Run LeetCode problems
  python -m com.mounish.main --concurrency # Run concurrency examples
        """
    )

    parser.add_argument(
        "--all",
        action="store_true",
        help="Run all demonstrations automatically"
    )
    parser.add_argument(
        "--ds",
        "--data-structures",
        action="store_true",
        dest="data_structures",
        help="Run data structures demonstrations"
    )
    parser.add_argument(
        "--algo",
        "--algorithms",
        action="store_true",
        dest="algorithms",
        help="Run algorithms demonstrations"
    )
    parser.add_argument(
        "--leetcode",
        action="store_true",
        help="Run LeetCode problems demonstrations"
    )
    parser.add_argument(
        "--concurrency",
        action="store_true",
        help="Run concurrency demonstrations"
    )
    parser.add_argument(
        "--interactive",
        action="store_true",
        help="Run interactive menu (default if no options specified)"
    )

    args = parser.parse_args()

    # If no arguments provided, run interactive menu
    if len(sys.argv) == 1 or args.interactive:
        interactive_menu()
        return 0

    try:
        # Run specific demos based on arguments
        if args.all:
            run_all_demos()
        else:
            if args.data_structures:
                demo_data_structures()
            if args.algorithms:
                demo_algorithms()
            if args.leetcode:
                demo_leetcode()
            if args.concurrency:
                demo_concurrency()

        return 0

    except KeyboardInterrupt:
        print("\n\nInterrupted by user. Exiting...")
        return 130
    except Exception as e:
        print(f"\nError: {e}", file=sys.stderr)
        import traceback
        traceback.print_exc()
        return 1


if __name__ == "__main__":
    sys.exit(main())
