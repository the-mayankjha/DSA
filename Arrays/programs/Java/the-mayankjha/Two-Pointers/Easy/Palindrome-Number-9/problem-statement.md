# 9. Palindrome Number

<p>
  <a href="https://leetcode.com/problems/palindrome-number/">
    <img src="https://img.shields.io/badge/LeetCode-%239-orange?style=for-the-badge&logo=leetcode&logoColor=white" height="40">
  </a>
  <img src="https://img.shields.io/badge/Difficulty-Easy-yellow?style=for-the-badge" height="40">
  <img src="https://img.shields.io/badge/Pattern-Two%20Pointers-blue?style=for-the-badge" height="40">
</p>

**Tags**

![Math](https://img.shields.io/badge/Math-444?style=flat-square)

## Problem

Given an integer `x`, return `true` if `x` is a **palindrome**, and `false` otherwise.

**Follow-up:**

### Examples

### Example 1

**Input:**

```text
x = 121
```

**Output:**

```text
true
```

> [!NOTE]
> 121 reads as 121 from left to right and from right to left.

### Example 2

**Input:**

```text
x = -121
```

**Output:**

```text
false
```

> [!NOTE]
> From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

### Example 3

**Input:**

```text
x = 10
```

**Output:**

```text
false
```

> [!NOTE]
> Reads 01 from right to left. Therefore it is not a palindrome.

## Constraints

- `-231 <= x <= 231 - 1`


## Solution

```java
class Solution {
    public boolean isPalindrome(int x) {

        // Negative numbers are not palindromes
        // Numbers ending in 0 are not palindromes (except 0)
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;

        // Reverse only half of the number
        while (x > reversedHalf) {
            int digit = x % 10;
            reversedHalf = reversedHalf * 10 + digit;
            x = x / 10;
        }

        // Even digits: x == reversedHalf
        // Odd digits: x == reversedHalf / 10
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
```

## Complexity

_See AI Notes below for the implementation-specific analysis._


---

## AI Notes

## Approach

The solution determines if an integer is a palindrome by reversing only the second half of the number mathematically, avoiding the potential integer overflow issues that come with reversing the entire number. By processing digits from the right, the algorithm builds up `reversedHalf` until it meets or exceeds the remaining portion of `x`.

## Key Idea / Invariant

> [!TIP]
> Instead of converting the integer to a string or reversing all digits, we reverse the number in place digit by digit. When `reversedHalf` becomes greater than or equal to the remaining `x`, we have processed exactly half of the digits.

## Code Explanation

The execution order and core components of `isPalindrome` proceed as follows:

1. **Edge Case Filtering:**
   - The initial `if` statement checks if `x < 0` or if `x % 10 == 0 && x != 0`. 
   - Negative numbers cannot be palindromes due to the leading minus sign.
   - Numbers ending in `0` (other than `0` itself) cannot be palindromes because no integer starts with `0`.

2. **Digit Reversal Loop:**
   - We initialize `reversedHalf` to `0`.
   - The `while (x > reversedHalf)` loop runs as long as the remaining part of `x` is strictly greater than the digits we have popped and reversed so far.
   - Inside the loop, `int digit = x % 10` extracts the last digit of `x`.
   - `reversedHalf = reversedHalf * 10 + digit` shifts the existing reversed digits left and appends the new `digit`.
   - `x = x / 10` removes the last digit from `x`.

3. **Palindrome Comparison:**
   - After the loop terminates, we compare the halves. 
   - For numbers with an even number of digits, `x` and `reversedHalf` will be equal (`x == reversedHalf`).
   - For numbers with an odd number of digits, the middle digit resides in `reversedHalf`, so we drop it using integer division (`x == reversedHalf / 10`).

## Dry Run

Let's trace `x = 1221`:

| Step | `x` | `reversedHalf` | `digit` = `x % 10` | Condition `x > reversedHalf` |
| :--- | :--- | :--- | :--- | :--- |
| Initial | `1221` | `0` | - | `1221 > 0` (True) |
| 1 | `122` | `1` | `1` | `122 > 1` (True) |
| 2 | `12` | `12` | `2` | `12 > 12` (False, loop ends) |

- **Final Check:** `x == reversedHalf` evaluates to `12 == 12`, which returns `true`.

## Complexity Analysis

- **Time Complexity:** $O(\log_{10} n)$, where $n$ is the value of `x`. The while loop runs roughly $\frac{n}{2}$ times, dividing the number of digits by 2.
- **Space Complexity:** $O(1)$. We only use a few primitive integer variables (`reversedHalf`, `digit`), requiring constant extra space regardless of the input size.

## Important Takeaways

> [!IMPORTANT]
> Reversing only half of the number is a powerful optimization that prevents arithmetic overflow that could occur if you attempted to reverse a 32-bit signed integer completely.

## Final Summary

This math-based Two Pointers-style digit manipulation approach efficiently validates palindromes without allocating extra memory for strings or arrays. By leveraging symmetry and stopping halfway through the number, it achieves optimal $O(\log n)$ time and $O(1)$ space complexity.

