"""
LeetCode problem solutions in Python with multiple approaches.

All 6 problems implemented with improvements:
1. Two Sum
2. Reverse Integer
3. Palindrome Number
4. Roman to Integer
5. Longest Common Prefix
6. Valid Parentheses
"""

from typing import List, Optional, Dict, Tuple
from collections import defaultdict
import time


# ============================================================================
# Problem 1: Two Sum
# ============================================================================

class TwoSum:
    """LeetCode #1: Two Sum - Multiple solution approaches."""

    @staticmethod
    def two_sum_hash(nums: List[int], target: int) -> Optional[Tuple[int, int]]:
        """
        Hash map approach - O(n) time, O(n) space.
        Most efficient for finding indices.
        """
        num_to_index = {}
        for i, num in enumerate(nums):
            complement = target - num
            if complement in num_to_index:
                return (num_to_index[complement], i)
            num_to_index[num] = i
        return None

    @staticmethod
    def two_sum_sorting(nums: List[int], target: int) -> Optional[Tuple[int, int]]:
        """
        Sorting with two pointers - O(n log n) time, O(n) space.
        Need to track original indices.
        """
        indexed_nums = [(num, i) for i, num in enumerate(nums)]
        indexed_nums.sort()

        left, right = 0, len(indexed_nums) - 1

        while left < right:
            current_sum = indexed_nums[left][0] + indexed_nums[right][0]
            if current_sum == target:
                return tuple(sorted([indexed_nums[left][1], indexed_nums[right][1]]))
            elif current_sum < target:
                left += 1
            else:
                right -= 1

        return None

    @staticmethod
    def two_sum_brute_force(nums: List[int], target: int) -> Optional[Tuple[int, int]]:
        """Brute force - O(n²) time, O(1) space."""
        for i in range(len(nums)):
            for j in range(i + 1, len(nums)):
                if nums[i] + nums[j] == target:
                    return (i, j)
        return None

    @staticmethod
    def find_all_two_sum_pairs(nums: List[int], target: int) -> List[Tuple[int, int]]:
        """Find all pairs that sum to target."""
        result = []
        seen = {}

        for i, num in enumerate(nums):
            complement = target - num
            if complement in seen:
                for j in seen[complement]:
                    result.append((j, i))
            seen.setdefault(num, []).append(i)

        return result


# ============================================================================
# Problem 7: Reverse Integer
# ============================================================================

class ReverseInteger:
    """LeetCode #7: Reverse Integer - Multiple approaches."""

    INT_MAX = 2**31 - 1
    INT_MIN = -(2**31)

    @staticmethod
    def reverse(x: int) -> int:
        """
        Mathematical approach with overflow check.
        Similar to Java version but more Pythonic.
        """
        sign = -1 if x < 0 else 1
        x = abs(x)
        reversed_num = 0

        while x != 0:
            digit = x % 10
            x //= 10

            # Check for overflow
            if reversed_num > ReverseInteger.INT_MAX // 10:
                return 0

            reversed_num = reversed_num * 10 + digit

        result = sign * reversed_num

        if result < ReverseInteger.INT_MIN or result > ReverseInteger.INT_MAX:
            return 0

        return result

    @staticmethod
    def reverse_string(x: int) -> int:
        """String-based approach - more Pythonic but less efficient."""
        sign = -1 if x < 0 else 1
        reversed_str = str(abs(x))[::-1]
        result = sign * int(reversed_str)

        if result < ReverseInteger.INT_MIN or result > ReverseInteger.INT_MAX:
            return 0

        return result

    @staticmethod
    def reverse_recursive(x: int, reversed_num: int = 0) -> int:
        """Recursive approach."""
        if x == 0:
            return reversed_num

        sign = -1 if x < 0 else 1
        x = abs(x) if reversed_num == 0 else x

        digit = x % 10
        new_reversed = reversed_num * 10 + digit

        if new_reversed > ReverseInteger.INT_MAX:
            return 0

        result = ReverseInteger.reverse_recursive(x // 10, new_reversed)
        return sign * result if reversed_num == 0 else result


# ============================================================================
# Problem 9: Palindrome Number
# ============================================================================

class PalindromeNumber:
    """LeetCode #9: Palindrome Number - Multiple approaches."""

    @staticmethod
    def is_palindrome(x: int) -> bool:
        """
        Half-reversal approach - O(log n) time, O(1) space.
        Most efficient.
        """
        if x < 0 or (x % 10 == 0 and x != 0):
            return False

        reversed_half = 0
        while x > reversed_half:
            reversed_half = reversed_half * 10 + x % 10
            x //= 10

        return x == reversed_half or x == reversed_half // 10

    @staticmethod
    def is_palindrome_string(x: int) -> bool:
        """String-based approach - most Pythonic."""
        if x < 0:
            return False
        s = str(x)
        return s == s[::-1]

    @staticmethod
    def is_palindrome_full_reverse(x: int) -> bool:
        """Full reversal approach."""
        if x < 0:
            return False

        original = x
        reversed_num = 0

        while x > 0:
            reversed_num = reversed_num * 10 + x % 10
            x //= 10

        return original == reversed_num

    @staticmethod
    def is_palindrome_two_pointers(x: int) -> bool:
        """Two pointers on string representation."""
        if x < 0:
            return False

        s = str(x)
        left, right = 0, len(s) - 1

        while left < right:
            if s[left] != s[right]:
                return False
            left += 1
            right -= 1

        return True


# ============================================================================
# Problem 13: Roman to Integer
# ============================================================================

class RomanToInteger:
    """LeetCode #13: Roman to Integer - Multiple approaches."""

    ROMAN_MAP = {
        'I': 1, 'V': 5, 'X': 10, 'L': 50,
        'C': 100, 'D': 500, 'M': 1000
    }

    @staticmethod
    def roman_to_int(s: str) -> int:
        """Iterative approach checking next character."""
        total = 0
        i = 0

        while i < len(s):
            current = RomanToInteger.ROMAN_MAP[s[i]]

            if i + 1 < len(s):
                next_val = RomanToInteger.ROMAN_MAP[s[i + 1]]
                if current < next_val:
                    total += next_val - current
                    i += 2
                    continue

            total += current
            i += 1

        return total

    @staticmethod
    def roman_to_int_reverse(s: str) -> int:
        """Reverse iteration - simpler logic."""
        total = 0
        prev_value = 0

        for char in reversed(s):
            value = RomanToInteger.ROMAN_MAP[char]
            if value < prev_value:
                total -= value
            else:
                total += value
            prev_value = value

        return total

    @staticmethod
    def roman_to_int_replace(s: str) -> int:
        """Replace subtraction cases first."""
        replacements = {
            'IV': 'IIII', 'IX': 'VIIII',
            'XL': 'XXXX', 'XC': 'LXXXX',
            'CD': 'CCCC', 'CM': 'DCCCC'
        }

        for old, new in replacements.items():
            s = s.replace(old, new)

        return sum(RomanToInteger.ROMAN_MAP[char] for char in s)

    @staticmethod
    def int_to_roman(num: int) -> str:
        """Bonus: Convert integer to Roman numeral."""
        values = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
        symbols = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I']

        result = []
        for value, symbol in zip(values, symbols):
            count = num // value
            if count:
                result.append(symbol * count)
                num -= value * count

        return ''.join(result)


# ============================================================================
# Problem 14: Longest Common Prefix
# ============================================================================

class LongestCommonPrefix:
    """LeetCode #14: Longest Common Prefix - Multiple approaches."""

    @staticmethod
    def longest_common_prefix_horizontal(strs: List[str]) -> str:
        """Horizontal scanning - compare with first string."""
        if not strs:
            return ""

        prefix = strs[0]
        for s in strs[1:]:
            while not s.startswith(prefix):
                prefix = prefix[:-1]
                if not prefix:
                    return ""

        return prefix

    @staticmethod
    def longest_common_prefix_vertical(strs: List[str]) -> str:
        """Vertical scanning - compare character by character."""
        if not strs:
            return ""

        for i, char in enumerate(strs[0]):
            for s in strs[1:]:
                if i >= len(s) or s[i] != char:
                    return strs[0][:i]

        return strs[0]

    @staticmethod
    def longest_common_prefix_zip(strs: List[str]) -> str:
        """Using zip - most Pythonic approach."""
        if not strs:
            return ""

        result = []
        for chars in zip(*strs):
            if len(set(chars)) == 1:
                result.append(chars[0])
            else:
                break

        return ''.join(result)

    @staticmethod
    def longest_common_prefix_binary_search(strs: List[str]) -> str:
        """Binary search on prefix length."""
        if not strs:
            return ""

        def is_common_prefix(length: int) -> bool:
            prefix = strs[0][:length]
            return all(s.startswith(prefix) for s in strs)

        min_len = min(len(s) for s in strs)
        low, high = 0, min_len

        while low <= high:
            mid = (low + high) // 2
            if is_common_prefix(mid):
                low = mid + 1
            else:
                high = mid - 1

        return strs[0][:high]

    @staticmethod
    def longest_common_prefix_divide_conquer(strs: List[str]) -> str:
        """Divide and conquer approach."""
        if not strs:
            return ""

        def common_prefix(left: str, right: str) -> str:
            min_len = min(len(left), len(right))
            for i in range(min_len):
                if left[i] != right[i]:
                    return left[:i]
            return left[:min_len]

        def divide_conquer(start: int, end: int) -> str:
            if start == end:
                return strs[start]

            mid = (start + end) // 2
            left_prefix = divide_conquer(start, mid)
            right_prefix = divide_conquer(mid + 1, end)
            return common_prefix(left_prefix, right_prefix)

        return divide_conquer(0, len(strs) - 1)


# ============================================================================
# Problem 20: Valid Parentheses
# ============================================================================

class ValidParentheses:
    """LeetCode #20: Valid Parentheses - Multiple approaches."""

    @staticmethod
    def is_valid(s: str) -> bool:
        """Stack-based approach - most common solution."""
        stack = []
        pairs = {')': '(', '}': '{', ']': '['}

        for char in s:
            if char in pairs:
                if not stack or stack.pop() != pairs[char]:
                    return False
            else:
                stack.append(char)

        return len(stack) == 0

    @staticmethod
    def is_valid_replace(s: str) -> bool:
        """
        Replacement approach - repeatedly remove valid pairs.
        Less efficient but elegant.
        """
        while '()' in s or '[]' in s or '{}' in s:
            s = s.replace('()', '').replace('[]', '').replace('{}', '')
        return s == ''

    @staticmethod
    def is_valid_dict_stack(s: str) -> bool:
        """Stack with dictionary lookup - Pythonic."""
        if len(s) % 2 != 0:
            return False

        stack = []
        opening = {'(', '{', '['}
        closing = {')': '(', '}': '{', ']': '['}

        for char in s:
            if char in opening:
                stack.append(char)
            elif char in closing:
                if not stack or stack.pop() != closing[char]:
                    return False

        return not stack

    @staticmethod
    def generate_parentheses(n: int) -> List[str]:
        """
        Bonus: Generate all valid parentheses combinations.
        Related to LeetCode #22.
        """
        def backtrack(current: str, open_count: int, close_count: int):
            if len(current) == 2 * n:
                result.append(current)
                return

            if open_count < n:
                backtrack(current + '(', open_count + 1, close_count)

            if close_count < open_count:
                backtrack(current + ')', open_count, close_count + 1)

        result = []
        backtrack('', 0, 0)
        return result


# ============================================================================
# Demo Functions
# ============================================================================

def demo_two_sum():
    """Demonstrate Two Sum solutions."""
    print("\n" + "=" * 80)
    print("PROBLEM 1: TWO SUM")
    print("=" * 80)

    test_cases = [
        ([2, 7, 11, 15], 9, "Example 1"),
        ([3, 2, 4], 6, "Example 2"),
        ([3, 3], 6, "Example 3"),
        ([1, 5, 3, 7, 9, 2], 10, "Multiple pairs"),
    ]

    for nums, target, description in test_cases:
        print(f"\n{description}: nums = {nums}, target = {target}")
        result1 = TwoSum.two_sum_hash(nums, target)
        result2 = TwoSum.two_sum_sorting(nums, target)
        result3 = TwoSum.two_sum_brute_force(nums, target)

        print(f"  Hash map:    {result1}")
        print(f"  Sorting:     {result2}")
        print(f"  Brute force: {result3}")

        all_pairs = TwoSum.find_all_two_sum_pairs(nums, target)
        if len(all_pairs) > 1:
            print(f"  All pairs: {all_pairs}")


def demo_reverse_integer():
    """Demonstrate Reverse Integer solutions."""
    print("\n" + "=" * 80)
    print("PROBLEM 7: REVERSE INTEGER")
    print("=" * 80)

    test_cases = [123, -123, 120, 0, 1534236469]

    for x in test_cases:
        print(f"\nInput: {x}")
        result1 = ReverseInteger.reverse(x)
        result2 = ReverseInteger.reverse_string(x)

        print(f"  Mathematical: {result1}")
        print(f"  String-based: {result2}")
        print(f"  Match: {result1 == result2} ✓" if result1 == result2 else "  Mismatch ✗")


def demo_palindrome_number():
    """Demonstrate Palindrome Number solutions."""
    print("\n" + "=" * 80)
    print("PROBLEM 9: PALINDROME NUMBER")
    print("=" * 80)

    test_cases = [(121, True), (-121, False), (10, False), (12321, True)]

    for x, expected in test_cases:
        print(f"\nInput: {x}, Expected: {expected}")
        result1 = PalindromeNumber.is_palindrome(x)
        result2 = PalindromeNumber.is_palindrome_string(x)
        result3 = PalindromeNumber.is_palindrome_full_reverse(x)

        print(f"  Half-reverse:  {result1} {'✓' if result1 == expected else '✗'}")
        print(f"  String:        {result2} {'✓' if result2 == expected else '✗'}")
        print(f"  Full-reverse:  {result3} {'✓' if result3 == expected else '✗'}")


def demo_roman_to_integer():
    """Demonstrate Roman to Integer solutions."""
    print("\n" + "=" * 80)
    print("PROBLEM 13: ROMAN TO INTEGER")
    print("=" * 80)

    test_cases = [
        ("III", 3), ("LVIII", 58), ("MCMXCIV", 1994),
        ("IV", 4), ("IX", 9), ("MMMCMXCIX", 3999)
    ]

    for roman, expected in test_cases:
        print(f"\nRoman: '{roman}', Expected: {expected}")
        result1 = RomanToInteger.roman_to_int(roman)
        result2 = RomanToInteger.roman_to_int_reverse(roman)
        result3 = RomanToInteger.roman_to_int_replace(roman)

        print(f"  Iterative:  {result1} {'✓' if result1 == expected else '✗'}")
        print(f"  Reverse:    {result2} {'✓' if result2 == expected else '✗'}")
        print(f"  Replace:    {result3} {'✓' if result3 == expected else '✗'}")

    print("\n=== Integer to Roman ===")
    for num in [3, 58, 1994, 3999]:
        roman = RomanToInteger.int_to_roman(num)
        back_to_int = RomanToInteger.roman_to_int(roman)
        print(f"{num:4d} -> {roman:15s} -> {back_to_int:4d} {'✓' if num == back_to_int else '✗'}")


def demo_longest_common_prefix():
    """Demonstrate Longest Common Prefix solutions."""
    print("\n" + "=" * 80)
    print("PROBLEM 14: LONGEST COMMON PREFIX")
    print("=" * 80)

    test_cases = [
        (["flower", "flow", "flight"], "fl"),
        (["dog", "racecar", "car"], ""),
        (["interspecies", "interstellar", "interstate"], "inters"),
    ]

    for strs, expected in test_cases:
        print(f"\nInput: {strs}")
        print(f"Expected: '{expected}'")

        result1 = LongestCommonPrefix.longest_common_prefix_horizontal(strs)
        result2 = LongestCommonPrefix.longest_common_prefix_vertical(strs)
        result3 = LongestCommonPrefix.longest_common_prefix_zip(strs)

        print(f"  Horizontal: '{result1}' {'✓' if result1 == expected else '✗'}")
        print(f"  Vertical:   '{result2}' {'✓' if result2 == expected else '✗'}")
        print(f"  Zip:        '{result3}' {'✓' if result3 == expected else '✗'}")


def demo_valid_parentheses():
    """Demonstrate Valid Parentheses solutions."""
    print("\n" + "=" * 80)
    print("PROBLEM 20: VALID PARENTHESES")
    print("=" * 80)

    test_cases = [
        ("()", True), ("()[]{}", True), ("(]", False),
        ("([)]", False), ("{[]}", True), ("((()))", True)
    ]

    for s, expected in test_cases:
        print(f"\nInput: '{s}', Expected: {expected}")
        result1 = ValidParentheses.is_valid(s)
        result2 = ValidParentheses.is_valid_replace(s)
        result3 = ValidParentheses.is_valid_dict_stack(s)

        print(f"  Stack:       {result1} {'✓' if result1 == expected else '✗'}")
        print(f"  Replace:     {result2} {'✓' if result2 == expected else '✗'}")
        print(f"  Dict Stack:  {result3} {'✓' if result3 == expected else '✗'}")

    print("\n=== Generate Valid Parentheses (n=3) ===")
    generated = ValidParentheses.generate_parentheses(3)
    print(f"Generated {len(generated)} combinations:")
    print(", ".join(generated))


def demo() -> None:
    """Run all LeetCode problem demonstrations."""
    print("\n" + "=" * 80)
    print("LEETCODE PROBLEMS - PYTHON IMPLEMENTATIONS")
    print("=" * 80)

    demo_two_sum()
    demo_reverse_integer()
    demo_palindrome_number()
    demo_roman_to_integer()
    demo_longest_common_prefix()
    demo_valid_parentheses()

    print("\n" + "=" * 80)
    print("ALL LEETCODE PROBLEMS COMPLETED")
    print("=" * 80 + "\n")


if __name__ == "__main__":
    demo()
