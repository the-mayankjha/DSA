# 42. Trapping Rain Water

<p>
  <a href="https://leetcode.com/problems/trapping-rain-water/">
    <img src="https://img.shields.io/badge/LeetCode-%2342-orange?style=for-the-badge&logo=leetcode&logoColor=white" height="40">
  </a>
  <img src="https://img.shields.io/badge/Difficulty-Hard-yellow?style=for-the-badge" height="40">
  <img src="https://img.shields.io/badge/Pattern-Not%20specified-blue?style=for-the-badge" height="40">
</p>

**Tags**

![Array](https://img.shields.io/badge/Array-444?style=flat-square)
![Two Pointers](https://img.shields.io/badge/Two%20Pointers-444?style=flat-square)
![Dynamic Programming](https://img.shields.io/badge/Dynamic%20Programming-444?style=flat-square)
![Stack](https://img.shields.io/badge/Stack-444?style=flat-square)
![Monotonic Stack](https://img.shields.io/badge/Monotonic%20Stack-444?style=flat-square)

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

                left++
```

## Complexity

_See AI Notes below for the implementation-specific analysis._


---

## AI Notes

## Approach

The solution implements the **Two Pointers** pattern to efficiently compute trapped rainwater without requiring extra space for prefix/suffix maximum arrays. By maintaining two pointers (`left` and `right`) at opposite ends of the `height` array alongside running maximums (`leftMax` and `rightMax`), we can determine the trapped water at any boundary immediately based on the height constraint of the opposing side.

## Key Idea / Invariant

> [!NOTE]
> The core invariant of this approach is that if `height[left] <= height[right]`, the water trapped at the `left` pointer is bounded solely by `leftMax`, because a taller or equal bar guaranteed at `right` ensures the water cannot spill out on the right side.

## Code Explanation

The execution proceeds through the following steps:

1. **Initialization**: We declare two pointers, `left` starting at index `0` and `right` starting at the end of the array (`height.length - 1`). We also initialize `leftMax`, `rightMax`, and `water` to `0`.
2. **Main Loop (`while (left < right)`)**: The loop iterates inward until the two pointers meet.
3. **Comparison (`if (height[left] <= height[right])`)**: 
   - We evaluate the side with the smaller height. Since `height[left]` is less than or equal to `height[right]`, `leftMax` dictates the water level.
   - We update `leftMax` using `Math.max(leftMax, height[left])`.
   - We calculate trapped water at the current `left` position as `leftMax - height[left]` and add it to `water`.
   - We advance `left` to the right. *(Note: The provided code snippet is incomplete and cuts off right after `left++`)*.

> [!CAUTION]
> The provided solution code is incomplete and cuts off mid-execution (`left++`). It misses the `else` branch for processing the `right` pointer, the loop closure, and the return statement.

## Dry Run

Let's dry run the intended logic using `height = [0,1,0,2]`:
- Initial state: `left = 0`, `right = 3`, `leftMax = 0`, `rightMax = 0`, `water = 0`.
- Iteration 1: `height[0]` (`0`) `<= height[3]` (`2`). `leftMax = Math.max(0, 0) = 0`. `water += 0 - 0 = 0`. `left` becomes `1`.
- Iteration 2: `height[1]` (`1`) `> height[3]` (`2`) is false? Wait, `height[1]` is `1` and `height[3]` is `2`, so `1 <= 2` is true: `leftMax = Math.max(0, 1) = 1`. `water += 1 - 1 = 0`. `left` becomes `2`.
- Iteration 3: `height[2]` (`0`) `<= height[3]` (`2`). `leftMax = Math.max(1, 0) = 1`. `water += 1 - 0 = 1`. `left` becomes `3`.
- Loop terminates when `left` equals `right`.

## Complexity Analysis

- **Time Complexity**: $\mathcal{O}(N)$ where $N$ is the length of the `height` array, since each element is visited at most once by the two pointers.
- **Space Complexity**: $\mathcal{O}(1)$ because only a constant amount of extra memory (`left`, `right`, `leftMax`, `rightMax`, `water`) is used.

## Alternative Approach

An alternative approach uses **Dynamic Processing** with two arrays (`leftMax` and `rightMax`) to precompute the maximum heights to the left and right of every index. While easier to conceptualize, it requires $\mathcal{O}(N)$ extra space. Alternatively, a **Monotonic Stack** can keep track of bars to compute trapped water horizontally layer by layer.

## Important Takeaways

- Two pointers work exceptionally well for trapping water problems because processing the smaller boundary eliminates uncertainty regarding the opposing side.
- Always ensure all control flow blocks (like the `else` branch for the `right` pointer) and return statements are fully implemented in production code.

## Final Summary

This two-pointer solution optimizes space down to $\mathcal{O}(1)$ by converging from both ends of the array, dynamically resolving water capacity based on the limiting height of the currently processed side.

