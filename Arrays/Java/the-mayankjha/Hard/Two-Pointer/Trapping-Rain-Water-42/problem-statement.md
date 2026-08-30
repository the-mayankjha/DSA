# Trapping Rain Water

<p>
  <a href="https://leetcode.com/problems/trapping-rain-water/">
    <img src="https://img.shields.io/badge/LeetCode-%2342-orange?style=for-the-badge&logo=leetcode&logoColor=white" height="40">
  </a>
  <img src="https://img.shields.io/badge/Difficulty-Hard-yellow?style=for-the-badge" height="40">
  <img src="https://img.shields.io/badge/Pattern-Two%20Pointer-blue?style=for-the-badge" height="40">
</p>

**Tags**

![Array](https://img.shields.io/badge/Array-444?style=flat-square)
![Two Pointers](https://img.shields.io/badge/Two%20Pointers-444?style=flat-square)
![Dynamic Programming](https://img.shields.io/badge/Dynamic%20Programming-444?style=flat-square)
![Stack](https://img.shields.io/badge/Stack-444?style=flat-square)
![Monotonic Stack](https://img.shields.io/badge/Monotonic%20Stack-444?style=flat-square)

## AI Notes

## 🧠 Approach

The solution employs a **Two-Pointer** strategy to calculate trapped water in a single pass with $O(1)$ extra space. Instead of pre-calculating the maximum height to the left and right of every index, we maintain two pointers, `left` and `right`, and two variables, `leftMax` and `rightMax`, to track the boundaries dynamically.

1.  **Initialization**: Set `left` at the start and `right` at the end of the array.
2.  **Pointer Movement**: The algorithm compares `height[left]` and `height[right]`. 
3.  **Water Calculation**: 
    *   If `height[left] <= height[right]`, we know the water trapped at `left` is limited by `leftMax` because there is a taller (or equal) bar at `right`. We update `leftMax` and add `leftMax - height[left]` to `water`.
    *   If `height[left] > height[right]`, we know the water trapped at `right` is limited by `rightMax`. We update `rightMax` and add `rightMax - height[right]` to `water`.
4.  **Termination**: The loop runs until `left` meets `right`.

## 🔍 Key Idea: The Bottleneck Principle

The amount of water at any index `i` is determined by `min(maxLeft, maxRight) - height[i]`. By always moving the pointer pointing to the **smaller** height, we guarantee that we are processing the side that acts as the "bottleneck." We don't need to know the *exact* maximum on the other side; we only need to know that *at least one* bar exists on the other side that is taller than the current pointer's value.

## 🧪 Dry Run

Consider `height = [0, 1, 0, 2]`:
1.  `left=0 (0)`, `right=3 (2)`. `0 <= 2` is true. `leftMax` becomes `0`. `water += 0 - 0 = 0`. `left` moves to `1`.
2.  `left=1 (1)`, `right=3 (2)`. `1 <= 2` is true. `leftMax` becomes `1`. `water += 1 - 1 = 0`. `left` moves to `2`.
3.  `left=2 (0)`, `right=3 (2)`. `0 <= 2` is true. `leftMax` stays `1`. `water += 1 - 0 = 1`. `left` moves to `3`.
4.  `left == right`, loop terminates. Result: `1`.

## ⏱️ Complexity Analysis

*   **Time Complexity**: $O(n)$, where $n$ is the length of the `height` array. Each element is visited exactly once by either the `left` or `right` pointer.
*   **Space Complexity**: $O(1)$, as we only use a few integer variables regardless of the input size.

## 🎯 Why this pattern

The Two-Pointer approach is optimal here because it avoids the $O(n)$ space requirement of the Dynamic Programming approach (which requires two auxiliary arrays to store prefix and suffix maximums). It effectively "shrinks" the search space from both ends toward the center.

## 🧠 Pattern Recognition

*   **Look for**: Problems involving "trapping," "enclosing," or "finding boundaries" in an array.
*   **Constraint**: If the problem asks for $O(1)$ space, the Two-Pointer approach is almost always the intended solution for array-based boundary problems.

## 📝 Important Takeaways

> [!IMPORTANT]
> *   **The "Smaller" Rule**: Always move the pointer that points to the smaller height. This ensures that the `max` variable on that side is the true limiting factor for the water level.
> *   **Boundary Safety**: The condition `height[left] <= height[right]` handles the equality case correctly, ensuring the logic remains robust even with duplicate heights.
> *   **Initialization**: Ensure `leftMax` and `rightMax` are initialized to `0` to correctly handle the first elements encountered.

## 🧩 Quick Cheat Sheet

```text
[L] . . . . . . [R]
 ^               ^
 |               |
leftMax       rightMax
```
*   If `H[L] <= H[R]`: Water at `L` is `leftMax - H[L]`.
*   If `H[L] > H[R]`: Water at `R` is `rightMax - H[R]`.

> [!CAUTION]
> The provided code snippet is incomplete (it lacks the `else` block for the `right` pointer logic and the return statement). Ensure the `right` pointer logic mirrors the `left` logic to complete the algorithm.

## 🏁 Final Summary

> [!SUCCESS]
> You have implemented a highly efficient $O(n)$ time and $O(1)$ space solution. By leveraging the Two-Pointer technique, you avoid redundant calculations and auxiliary storage, making this the most optimal approach for the Trapping Rain Water problem.


## Problem

Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

![Problem diagram](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

### Examples

### Example 1

**Input:**

```text
height = [0,1,0,2,1,0,1,3,2,1,2,1]
```

**Output:**

```text
6
```

> [!NOTE]
> The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

### Example 2

**Input:**

```text
height = [4,2,0,3,2,5]
```

**Output:**

```text
9
```

## Constraints

- `n == height.length`
- `1 <= n <= 2 * 104`
- `0 <= height[i] <= 105`


---

## 📝 Notes & Insights

This is manual test for Notes and Insightss


## Solution

```java
class Solution {
    public int trap(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int water = 0;

        while (left < right) {

            if (height[left] <= height[right]) {

                // Update maximum height on the left
                leftMax = Math.max(leftMax, height[left]);

                // Water trapped at left
                water += leftMax - height[left];

                left++;
```

## Complexity

- **Time:** See AI Notes for the analysis of this implementation.
- **Space:** See AI Notes for the analysis of this implementation.

