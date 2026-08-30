# Longest Palindromic Substring

<p>
  <a href="https://leetcode.com/problems/longest-palindromic-substring/">
    <img src="https://img.shields.io/badge/LeetCode-%235-orange?style=for-the-badge&logo=leetcode&logoColor=white" height="40">
  </a>
  <img src="https://img.shields.io/badge/Difficulty-Medium-yellow?style=for-the-badge" height="40">
  <img src="https://img.shields.io/badge/Pattern-Expand%20Around%20Center-blue?style=for-the-badge" height="40">
</p>

**Tags**

![String](https://img.shields.io/badge/String-444?style=flat-square)
![Two Pointers](https://img.shields.io/badge/Two%20Pointers-444?style=flat-square)
![Dynamic Programming](https://img.shields.io/badge/Dynamic%20Programming-444?style=flat-square)
![Manacher](https://img.shields.io/badge/Manacher-444?style=flat-square) 

## Problem

Given a string `s`, return the **longest palindromic substring** in `s`.

### Example 1

```text
Input:  s = "babad"
Output: "bab"
```

> [!NOTE]
> `"aba"` is also a valid answer.

### Example 2

```text
Input:  s = "cbbd"
Output: "bb"
```

## Constraints

- `1 <= s.length <= 1000`
- `s` consists of only digits and English letters.

## Solution

```java
class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int maxLen = 1;

        for (int i = 0; i < s.length(); i++) {
            // Odd-length palindrome
            int len1 = expand(s, i, i);

            // Even-length palindrome
            int len2 = expand(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
```

## Complexity

- **Time:** `O(n²)`
- **Space:** `O(1)`
