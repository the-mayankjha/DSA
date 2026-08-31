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

The solution checks whether an integer `x` is a palindrome by reversing only the second half of the number mathematically, avoiding potential integer overflow that could occur if the entire number were reversed. 

> [!TIP]
> Reversing only half of the number is a powerful optimization that cuts the required mathematical operations in half and eliminates the need to handle full-reversal overflows.

## Key Idea / Invariant

Negative numbers and numbers that end in `0` (except `0` itself) cannot be palindromes because a palindrome cannot start with `0`. For valid numbers, the algorithm extracts digits from the right side of `x` one by one using modulo arithmetic and accumulates them into `reversedHalf`. Once `reversedHalf` becomes greater than or equal to the remaining `x`, the halfway point is reached.

## Code Explanation

The execution proceeds through the following steps:

1. **Guard Clauses:**
   ```java
   if (x < 0 || (x % 10 == 0 && x != 0)) {
       return false;
   }
   ```
   The `if` statement immediately filters out negative numbers and multiples of 10 (like `10`, `20`, `100`), which can never read the same forwards and backwards.

2. **Reversing the Right Half:**
   ```java
   int reversedHalf = 0;
   while (x > reversedHalf) {
       int digit = x % 10;
       reversedHalf = reversedHalf * 10 + digit;
       x = x / 10;
   }
   ```
   Inside the `while` loop, `digit = x % 10` extracts the rightmost digit of `x`. This digit is appended to `reversedHalf` by multiplying the current `reversedHalf` by `10` and adding `digit`. Finally, `x = x / 10` strips the extracted digit from `x`. The loop terminates when `x` has shrunk to be less than or equal to `reversedHalf`.

3. **Comparison:**
   ```java
   return x == reversedHalf || x == reversedHalf / 10;
   ```
   - For numbers with an **even** number of digits (e.g., `1221`), `x` and `reversedHalf` will be equal at the halfway point.
   - For numbers with an **odd** number of digits (e.g., `12321`), the middle digit resides in `reversedHalf` but can be safely ignored by checking `x == reversedHalf / 10`.

## Dry Run

Let's trace `x = 1221`:

| Iteration | Condition `x > reversedHalf` | `x` (before) | `digit = x % 10` | `reversedHalf` (after) | `x` (after) |
| :---: | :---: | :---: | :---: | :---: | :---: |
| **Start** | — | `1221` | — | `0` | `1221` |
| **1** | `1221 > 0` (True) | `1221` | `1` | `1` | `122` |
| **2** | `122 > 1` (True) | `122` | `2` | `12` | `12` |
| **End** | `12 > 12` (False) | `12` | — | `12` | `12` |

- **Final Check:** `x == reversedHalf` evaluates to `12 == 12`, which is `true`.

Let's trace `x = 12321`:

| Iteration | Condition `x > reversedHalf` | `x` (before) | `digit = x % 10` | `reversedHalf` (after) | `x` (after) |
| :---: | :---: | :---: | :---: | :---: | :---: |
| **Start** | — | `12321` | — | `0` | `12321` |
| **1** | `12321 > 0` (True) | `12321` | `1` | `1` | `1232` |
| **2** | `1232 > 1` (True) | `1232` | `2` | `12` | `123` |
| **End** | `123 > 12` (False) | `123` | — | `12` | `123` |

- **Final Check:** `x == reversedHalf / 10` evaluates to `123 == 12`, which simplifies to `12 == 12` (`true`).

## Complexity Analysis

- **Time Complexity:** $\mathcal{O}(\log_{10}(n))$ — The `while` loop divides `x` by `10` at each iteration, meaning the number of steps is proportional to the number of digits in `x`.
- **Space Complexity:** $\mathcal{O}(1)$ — Only a few primitive integer variables (`reversedHalf`, `digit`) are used, consuming constant extra memory.

## Important Takeaways

- Mathematical manipulation (`% 10` and `/ 10`) avoids converting integers to strings, saving both time and heap memory allocations.
- Halving the reversal process prevents integer overflow issues that frequently plague full-number reversals in languages with fixed-width integers like Java.

## Final Summary

This solution efficiently checks for palindromes by reversing only the trailing half of the integer using a `while` loop and modulo arithmetic. By handling even and odd digit lengths with a final logical check, it achieves optimal $\mathcal{O}(\log_{10} n)$ time and $\mathcal{O}(1)$ space complexity without requiring string conversions.

