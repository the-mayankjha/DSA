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
> The provided code snippet is incomplete and cuts off before finishing the final comparison and method return statement.

## Key Idea / Invariant

Instead of converting the integer to a string or reversing the entire number, we pop digits from the right side of `x` one by one and append them to `reversedHalf`. Because a palindrome reads the same forwards and backwards, the first half of the number must equal the reversed second half once the `while` loop reaches the middle.

## Code Explanation

The method `isPalindrome(int x)` processes the input number step by step:

1. **Edge Case Validation:**
   ```java
   if (x < 0 || (x % 10 == 0 && x != 0)) {
       return false;
   }
   ```
   Negative numbers can never be palindromes due to the leading minus sign. Numbers that are multiples of 10 (other than `0` itself) cannot be palindromes because no integer starts with the digit `0`.

2. **Partial Reversal Loop:**
   ```java
   int reversedHalf = 0;

   while (x > reversedHalf) {
       int digit = x % 10;
       reversedHalf = reversedHalf * 10 + digit;
       x = x / 10;
   }
   ```
   - `digit = x % 10` extracts the rightmost digit of `x`.
   - `reversedHalf = reversedHalf * 10 + digit` shifts existing digits in `reversedHalf` to the left and appends the new `digit`.
   - `x = x / 10` drops the rightmost digit from `x`.
   - The loop continues as long as `x` is strictly greater than `reversedHalf`, meaning we have processed half or slightly more than half of the digits.

## Dry Run

Let's trace `x = 1221`:

1. **Initial Check:** `1221 < 0` is false, `1221 % 10 == 0` is false. Proceed to loop.
2. **First Iteration:**
   - `x > reversedHalf` (`1221 > 0`) is true.
   - `digit = 1221 % 10` $\rightarrow$ `1`
   - `reversedHalf = 0 * 10 + 1` $\rightarrow$ `1`
   - `x = 1221 / 10` $\rightarrow$ `122`
3. **Second Iteration:**
   - `x > reversedHalf` (`122 > 1`) is true.
   - `digit = 122 % 10` $\rightarrow$ `2`
   - `reversedHalf = 1 * 10 + 2` $\rightarrow$ `12`
   - `x = 12 / 10` $\rightarrow$ `1`
4. **Termination:** Loop checks `x > reversedHalf` (`1 > 12`), which is false. Loop terminates with `x = 1` and `reversedHalf = 12`.

*(Note: In the completed code, the final check would compare `x == reversedHalf` for even-length numbers or `x == reversedHalf / 10` for odd-length numbers).*

## Complexity Analysis

- **Time Complexity:** $O(\log_{10}(n))$ where $n$ is the value of `x`. The `while` loop divides `x` by 10 at each iteration, meaning it executes roughly half as many times as there are digits in `x`.
- **Space Complexity:** $O(1)$ because we only use a fixed number of integer variables (`reversedHalf`, `digit`) regardless of the size of `x`.

## Alternative Approach

An alternative approach is converting the integer to a `String` and using Two Pointers from both ends to compare characters. However, that approach requires $O(n)$ extra space for string allocation, making the math-based reversal more optimal.

## Important Takeaways

> [!TIP]
> Reversing only half of a numeric palindrome avoids integer overflow issues that frequently happen when reversing a 32-bit signed integer completely.

## Final Summary

This solution uses a math-based Two Pointers-like technique adapted for integers. By peeling off digits from the tail of `x` and building `reversedHalf`, we efficiently evaluate symmetry in $O(\log n)$ time and $O(1)$ space without string conversions.

