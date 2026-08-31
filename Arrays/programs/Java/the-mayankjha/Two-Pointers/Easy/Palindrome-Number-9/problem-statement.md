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

        // Even
```

## Complexity

_See AI Notes below for the implementation-specific analysis._


---

## AI Notes

## Approach

The solution determines whether an integer `x` is a palindrome by reversing only the second half of the number mathematically, avoiding potential integer overflow that could occur when reversing the entire number. 

> [!CAUTION]
> The provided code snippet is incomplete and cuts off before finishing the palindrome comparison and closing the method and class.

## Key Idea / Invariant

By repeatedly extracting the last digit of `x` using modulo arithmetic (`% 10`) and appending it to `reversedHalf`, we construct the reverse of the trailing digits. 

> [!NOTE]
> Reversing only half the number cuts the required math operations in half. When `x` becomes less than or equal to `reversedHalf`, the loop terminates.

## Code Explanation

The execution order follows these logical steps:

1. **Edge Case Validation:**
   ```java
   if (x < 0 || (x % 10 == 0 && x != 0)) {
       return false;
   }
   ```
   Negative numbers cannot be palindromes because of the leading minus sign. Numbers that end with `0` (other than `0` itself) cannot be palindromes because a palindrome cannot start with `0`.

2. **Initialization:**
   ```java
   int reversedHalf = 0;
   ```
   `reversedHalf` accumulates the reversed digits of the right half of `x`.

3. **Digit Extraction and Reversal Loop:**
   ```java
   while (x > reversedHalf) {
       int digit = x % 10;
       reversedHalf = reversedHalf * 10 + digit;
       x = x / 10;
   }
   ```
   * `int digit = x % 10;`: Extracts the rightmost digit of `x`.
   * `reversedHalf = reversedHalf * 10 + digit;`: Shifts existing digits in `reversedHalf` to the left and adds the new `digit`.
   * `x = x / 10;`: Removes the rightmost digit from `x`.
   * The loop continues as long as `x` is strictly greater than `reversedHalf`.

## Dry Run

Let's trace the execution for `x = 1221`:

| Step | Condition `x > reversedHalf` | `digit = x % 10` | `reversedHalf` update | `x` update |
| :--- | :--- | :--- | :--- | :--- |
| Initial | `1221 > 0` (True) | - | `0` | `1221` |
| 1 | `1221 > 0` (True) | `1` | `0 * 10 + 1 = 1` | `122` |
| 2 | `122 > 1` (True) | `2` | `1 * 10 + 2 = 12` | `12` |

Loop terminates because `x` (`12`) is no longer greater than `reversedHalf` (`12`).

## Complexity Analysis

- **Time Complexity:** $\mathcal{O}(\log_{10}(n))$ where $n$ is the value of `x`. The algorithm divides `x` by $10$ in each iteration, meaning the loop runs roughly half the number of digits in `x`.
- **Space Complexity:** $\mathcal{O}(1)$ as only a few primitive integer variables (`reversedHalf`, `digit`) are used, requiring constant extra space.

## Alternative Approach

An alternative approach is to convert the integer to a `String` and use a Two Pointers technique with `left` and `right` indices moving inward. However, that approach requires $\mathcal{O}(\log_{10}(n))$ extra space for string allocation, making the mathematical reversal approach more optimal.

## Important Takeaways

- Checking boundary conditions early (`x < 0` or trailing zeros) eliminates unnecessary computations.
- Reversing half of a numeric value is a clean strategy to prevent arithmetic overflow compared to reversing the entire number.

## Final Summary

This solution efficiently validates palindromic integers in logarithmic time and constant space by leveraging modulo and division operators to reverse only the trailing half of the input integer.

