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

The solution determines if an integer `x` is a palindrome without converting it to a string. Instead of reversing the entire integer—which risks integer overflow—the algorithm reverses only the trailing half of the number. By comparing the first half of the original number with the reversed second half, we can verify symmetry in logarithmic time.

## Key Idea / Invariant

> [!TIP]
> Reversing only half of the number avoids potential integer overflow issues that would occur if we reversed a large 32-bit signed integer completely.

- **Negative numbers** can never be palindromes because of the leading minus sign (e.g., `-121` reads as `121-` backwards).
- **Numbers ending in `0`** (except `0` itself) cannot be palindromes because a palindrome cannot start with `0` (e.g., `10` reversed is `01`, which evaluates to `1`).
- The `while` loop terminates precisely when `x` (the remaining first half) becomes less than or equal to `reversedHalf` (the constructed second half).

## Code Explanation

The execution proceeds through the following steps:

1. **Edge Case Guard (`if`)**:
   ```java
   if (x < 0 || (x % 10 == 0 && x != 0)) {
       return false;
   }
   ```
   The condition `x < 0` immediately filters out negatives. The condition `x % 10 == 0 && x != 0` handles numbers like `10`, `100`, etc., ensuring multiples of 10 (excluding `0` itself) are rejected.

2. **Half-Reversal Loop (`while`)**:
   ```java
   int reversedHalf = 0;

   while (x > reversedHalf) {
       int digit = x % 10;
       reversedHalf = reversedHalf * 10 + digit;
       x = x / 10;
   }
   ```
   - `int digit = x % 10;` extracts the last digit of the current integer `x`.
   - `reversedHalf = reversedHalf * 10 + digit;` shifts existing digits in `reversedHalf` to the left and appends the new `digit`.
   - `x = x / 10;` drops the extracted last digit from `x`.
   - The loop continues as long as `x > reversedHalf`, stopping right at or past the midpoint of the original number.

3. **Palindrome Validation (`return`)**:
   ```java
   return x == reversedHalf || x == reversedHalf / 10;
   ```
   - For an **even-length** number (e.g., `1221`), `x` and `reversedHalf` will be equal when the loop terminates.
   - For an **odd-length** number (e.g., `12321`), `reversedHalf` will contain one extra middle digit (e.g., `x = 12`, `reversedHalf = 123`). Dividing `reversedHalf` by 10 (`reversedHalf / 10`) strips the middle digit so it matches `x`.

## Dry Run

Let's trace `x = 1221`:

| Step | `x` (before) | `digit` (`x % 10`) | `reversedHalf` (after) | `x` (after) | Loop Condition (`x > reversedHalf`) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Initial | `1221` | - | `0` | `1221` | `1221 > 0` (true) |
| 1 | `1221` | `1` | `1` | `122` | `122 > 1` (true) |
| 2 | `122` | `2` | `12` | `12` | `12 > 12` (false) |

- **Termination**: Loop exits because `x` (`12`) is no longer strictly greater than `reversedHalf` (`12`).
- **Return Check**: `x == reversedHalf` evaluates to `12 == 12`, which is `true`.

---

Let's trace `x = 12321`:

| Step | `x` (before) | `digit` (`x % 10`) | `reversedHalf` (after) | `x` (after) | Loop Condition (`x > reversedHalf`) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Initial | `12321` | - | `0` | `12321` | `12321 > 0` (true) |
| 1 | `12321` | `1` | `1` | `1232` | `1232 > 1` (true) |
| 2 | `1232` | `2` | `12` | `123` | `123 > 12` (true) |
| 3 | `123` | `3` | `123` | `12` | `12 > 12` (false) |

- **Termination**: Loop exits because `x` (`12`) is not greater than `reversedHalf` (`123`).
- **Return Check**: `x == reversedHalf / 10` evaluates to `12 == 123 / 10` (`12 == 12`), which is `true`.

## Complexity Analysis

- **Time Complexity**: $\mathcal{O}(\log_{10}(n))$ where $n$ is the value of `x`. The algorithm divides `x` by `10` in each iteration of the while loop, meaning it processes roughly half the total number of digits.
- **Space Complexity**: $\mathcal{O}(1)$. Only a few primitive integer variables (`reversedHalf`, `digit`) are used, requiring constant extra memory regardless of the input size.

## Alternative Approach

An alternative approach is converting the integer to a `String` (or `StringBuilder`) and using a two-pointer technique or `.reverse()` method to check equality. However, string conversion requires $\mathcal{O}(\log_{10}(n))$ extra space for character arrays and incurs the performance overhead of object allocation.

## Important Takeaways

- **Overflow Prevention**: Reversing only half of the integer prevents arithmetic overflow that could happen when multiplying a full 32-bit integer by 10.
- **Odd vs. Even Lengths**: Handling odd-length palindromes requires stripping the middle digit from `reversedHalf` via integer division (`/ 10`) before comparison.
- **Early Exits**: Checking for negative values and non-zero multiples of 10 up front eliminates unnecessary calculations.

## Final Summary

This solution efficiently validates palindrome numbers in-place using arithmetic operations. By peeling off digits from the tail of `x` and building `reversedHalf`, it runs in logarithmic time $\mathcal{O}(\log n)$ and constant space $\mathcal{O}(1)$ without relying on string conversions.

