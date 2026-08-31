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
To check if an integer is a palindrome without converting it to a string (which avoids extra space for string storage), the algorithm reverses only the *second half* of the number mathematically. By comparing the reversed half with the remaining first half, we can determine symmetry efficiently.

> [!NOTE]
> Reversing the entire number risks integer overflow. Reversing only half prevents this while still providing enough data to validate palindromic properties.

## Key Idea / Invariant
The core invariant relies on peeling off digits from the original number `x` from right to left using modulo (`% 10`) and division (`/ 10`), building `reversedHalf` piece by piece until `reversedHalf` becomes greater than or equal to the shrinking `x`.

## Code Explanation
The solution method `isPalindrome` executes sequentially as follows:

1. **Edge Case Filtering:**
   ```java
   if (x < 0 || (x % 10 == 0 && x != 0)) {
       return false;
   }
   ```
   * Any negative number (`x < 0`) cannot be a palindrome because of the leading minus sign.
   * Any number ending in `0` (except `0` itself) cannot be a palindrome because no non-zero number starts with `0`.

2. **Reversing Half the Number:**
   * An integer `reversedHalf` is initialized to `0`.
   * The `while (x > reversedHalf)` loop runs as long as the remaining part of `x` is strictly greater than the digits already popped and reversed.
   * Inside the loop, `int digit = x % 10;` extracts the last digit.
   * `reversedHalf = reversedHalf * 10 + digit;` shifts existing digits left and appends the new digit.
   * `x = x / 10;` strips the last digit from `x`.

> [!CAUTION]
> The provided code snippet cuts off before completing the loop check and handling odd-length number comparisons (e.g., checking `x == reversedHalf || x == reversedHalf / 10`).

## Dry Run
Let's trace `x = 1221`:
1. **Initial check:** `1221 < 0` is false, `1221 % 10 == 0` is false. Passes.
2. **Loop 1:** (`x = 1221`, `reversedHalf = 0`)
   * `digit = 1`
   * `reversedHalf = 1`
   * `x = 122`
3. **Loop 2:** (`x = 122`, `reversedHalf = 1`)
   * `digit = 2`
   * `reversedHalf = 12`
   * `x = 12`
4. **Loop Termination:** `x > reversedHalf` (`12 > 12`) becomes false. Loop exits with `x = 12` and `reversedHalf = 12`.

## Complexity Analysis
* **Time Complexity:** $\mathcal{O}(\log_{10} n)$ where $n$ is the value of `x`. The algorithm processes roughly half of the total number of digits.
* **Space Complexity:** $\mathcal{O}(1)$ auxiliary space, as only a few primitive integer variables (`reversedHalf`, `digit`) are maintained.

## Alternative Approach
* **String Conversion:** Convert `x` to a `String` (or `StringBuilder`) and check if it equals its reverse. 
* *Drawback:* Requires $\mathcal{O}(\log_{10} n)$ extra space for character storage and is generally slower due to string manipulation overhead.

## Important Takeaways
* Mathematical digit manipulation (`% 10` and `/ 10`) avoids the memory overhead of string conversions.
* Halving the reversal process is a brilliant strategy to bypass potential integer overflow bugs that happen when fully reversing large integers.

## Final Summary
This solution leverages a mathematical Two Pointers-like technique on numeric digits by building the reverse of the right half and comparing it against the left half, ensuring optimal $\mathcal{O}(\log n)$ time and $\mathcal{O}(1)$ space performance.

