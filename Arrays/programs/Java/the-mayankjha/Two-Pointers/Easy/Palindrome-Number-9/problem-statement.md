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

The solution determines whether an integer `x` is a palindrome by reversing only the second half of the number mathematically, avoiding potential integer overflow that could occur if the entire number were reversed. 

> [!TIP]
> Reversing half the number instead of the full integer cuts the required arithmetic operations in half and prevents overflow issues inherent to large 32-bit signed integers.

## Key Idea / Invariant

Negative numbers and numbers that end with `0` (except `0` itself) cannot be palindromes. For all other numbers, we peel off digits from the right using modulo arithmetic (`% 10`) and accumulate them into `reversedHalf`. Once `reversedHalf` becomes greater than or equal to the remaining `x`, we have processed half the digits.

## Code Explanation

The execution proceeds through the following steps:

1. **Edge Case Guard**: 
   ```java
   if (x < 0 || (x % 10 == 0 && x != 0)) {
       return false;
   }
   ```
   The condition immediately eliminates negative numbers and multiples of 10 (since no positive integer starts with `0`).

2. **Digit Reversal Loop**:
   ```java
   int reversedHalf = 0;
   while (x > reversedHalf) {
       int digit = x % 10;
       reversedHalf = reversedHalf * 10 + digit;
       x = x / 10;
   }
   ```
   - `digit = x % 10` extracts the rightmost digit of `x`.
   - `reversedHalf = reversedHalf * 10 + digit` shifts the current `reversedHalf` left by one decimal place and appends the new `digit`.
   - `x = x / 10` removes the rightmost digit from `x`.
   The loop terminates when the shrinking `x` becomes less than or equal to the growing `reversedHalf`.

3. **Comparison**:
   ```java
   return x == reversedHalf || x == reversedHalf / 10;
   ```
   - If `x` originally had an **even** number of digits, `x` and `reversedHalf` will be equal.
   - If `x` originally had an **odd** number of digits, `reversedHalf / 10` drops the middle digit, allowing a direct comparison with `x`.

## Dry Run

Let's trace `x = 1221`:

| Step | Condition `x > reversedHalf` | `digit = x % 10` | `reversedHalf` computation | `x` after division |
| :--- | :--- | :--- | :--- | :--- |
| Initial | `1221 > 0` (True) | - | `0` | `1221` |
| 1 | `1221 > 0` (True) | `1` | `0 * 10 + 1 = 1` | `122` |
| 2 | `122 > 1` (True) | `2` | `1 * 10 + 2 = 12` | `12` |
| 3 | `12 > 12` (False) | - | Loop terminates | `12` |

Final check: `x == reversedHalf` evaluates to `12 == 12`, returning `true`.

## Complexity Analysis

- **Time Complexity**: $\mathcal{O}(\log_{10}(n))$ where $n$ is the value of `x`. The while loop runs roughly $\log_{10}(n) / 2$ times because we only process half of the digits.
- **Space Complexity**: $\mathcal{O}(1)$ as only a few primitive integer variables (`reversedHalf`, `digit`) are used.

## Important Takeaways

- **Overflow Prevention**: Reversing half a number is a robust pattern to avoid integer overflow problems on LeetCode.
- **Math over Strings**: Solving this numerically avoids the $\mathcal{O}(n)$ extra space complexity required to convert the integer to a `String` or `char[]`.

## Final Summary

This solution uses mathematical digit extraction and half-reversal to efficiently check for palindromes in logarithmic time and constant space, successfully dodging both overflow pitfalls and extra memory allocations.

