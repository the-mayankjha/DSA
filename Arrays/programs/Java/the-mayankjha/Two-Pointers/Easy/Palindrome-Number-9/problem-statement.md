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

The solution checks if an integer is a palindrome without converting it to a string. Instead of reversing the entire number—which risks integer overflow—it reverses only the **last half** of the digits. By comparing the first half of the number with the reversed second half, we can determine if the number reads the same forwards and backwards.

## Key Idea / Invariant

> [!NOTE]
> Reversing the entire integer can cause arithmetic overflow if the reversed value exceeds `Integer.MAX_VALUE`. Reversing only half the number avoids this risk entirely.

- Negative numbers can never be palindromes because of the leading minus sign.
- Numbers that end with `0` (other than `0` itself) cannot be palindromes because a palindrome cannot start with `0`.
- The `while` loop terminates when the original number `x` becomes less than or equal to `reversedHalf`, meaning we have processed half (or slightly more than half) of the digits.

## Code Explanation

The execution proceeds through the following steps:

1. **Early Exit Validation**:
   - The `if (x < 0 || (x % 10 == 0 && x != 0))` check immediately eliminates negative numbers and multiples of 10 (except `0` itself).
2. **Variable Initialization**:
   - `reversedHalf` is initialized to `0` to accumulate the reversed digits from the back of `x`.
3. **Digit Reversal Loop**:
   - The `while (x > reversedHalf)` loop runs as long as `x` is strictly greater than `reversedHalf`.
   - Inside the loop, `int digit = x % 10` extracts the last digit of `x`.
   - `reversedHalf = reversedHalf * 10 + digit` shifts the existing reversed digits to the left and appends the new `digit`.
   - `x = x / 10` removes the last digit from `x`.
4. **Length and Value Comparison**:
   - For numbers with an **even** number of digits, `x` will exactly equal `reversedHalf` when the loop terminates.
   - For numbers with an **odd** number of digits, the middle digit resides in `reversedHalf`, so we drop it using integer division (`reversedHalf / 10`) before comparing it to `x`.

## Dry Run

Let's trace `x = 1221`:

1. `x < 0` (`1221 < 0`) is false. `1221 % 10 == 0` is false. We proceed past the guard clause.
2. `reversedHalf = 0`.
3. **Iteration 1**:
   - `x > reversedHalf` (`1221 > 0`) is true.
   - `digit = 1221 % 10` $\rightarrow$ `1`.
   - `reversedHalf = 0 * 10 + 1` $\rightarrow$ `1`.
   - `x = 1221 / 10` $\rightarrow$ `122`.
4. **Iteration 2**:
   - `x > reversedHalf` (`122 > 1`) is true.
   - `digit = 122 % 10` $\rightarrow$ `2`.
   - `reversedHalf = 1 * 10 + 2` $\rightarrow$ `12`.
   - `x = 12 / 10` $\rightarrow$ `12`.
5. Loop terminates because `x > reversedHalf` (`12 > 12`) is now false.
6. Return `x == reversedHalf || x == reversedHalf / 10` $\rightarrow$ `12 == 12` is true.

## Complexity Analysis

- **Time Complexity**: $\mathcal{O}(\log_{10}(n))$ where $n$ is the value of `x`. We divide `x` by $10$ in each iteration of the loop, meaning the runtime is proportional to the number of digits.
- **Space Complexity**: $\mathcal{O}(1)$ because we only use a constant amount of extra memory (`reversedHalf` and `digit`) regardless of the size of `x`.

## Important Takeaways

> [!TIP]
> Always consider arithmetic overflow when dealing with reversals in numeric problems. Reversing only half the digits is a robust pattern that prevents overflow issues without needing larger data types like `long`.

## Final Summary

This solution efficiently validates palindromes numerically by stripping and reversing only the trailing half of the digits. It runs in logarithmic time with constant extra space and cleanly handles edge cases such as negative numbers and trailing zeros without converting data types.

