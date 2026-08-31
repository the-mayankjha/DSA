# 42. Trapping Rain Water

<p>
  <a href="https://leetcode.com/problems/trapping-rain-water/">
    <img src="https://img.shields.io/badge/LeetCode-%2342-orange?style=for-the-badge&logo=leetcode&logoColor=white" height="40">
  </a>
  <img src="https://img.shields.io/badge/Difficulty-Hard-yellow?style=for-the-badge" height="40">
  <img src="https://img.shields.io/badge/Pattern-Two%20Pointers-blue?style=for-the-badge" height="40">
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

                left++;

            } else {

                // Update maximum height on the right
                rightMax = Math.max(rightMax, height[right]);

                // Water trapped at right
                water += rightMax - height[right];

                right--;
            }
        }

        return water;
    }
}
```

## Complexity

_See AI Notes below for the implementation-specific analysis._


---

## AI Notes

## Approach

This solution uses the **Two Pointers** pattern to compute the trapped rain water in a single pass with constant extra space. Instead of pre-computing the maximum heights for every index using auxiliary arrays, we maintain two pointers (`left` and `right`) starting at opposite ends of the elevation map. By tracking the running maximum heights from both directions (`leftMax` and `rightMax`), we can determine the trapped water at any boundary dynamically.

## Key Idea / Invariant

> [!NOTE]
> The water trapped at any given bar is limited by the shorter of the maximum heights to its left and right. 

Because of this, whenever `height[left] <= height[right]`, we are guaranteed that `leftMax` is strictly less than or equal to the true maximum on the right side. Therefore, the water trapped at the current `left` position depends entirely on `leftMax`, allowing us to safely process and advance the `left` pointer without needing to know the exact right-side maximums yet.

## Code Explanation

We begin by initializing five variables: `left` at index `0`, `right` at the end of the array, `leftMax` and `rightMax` set to `0`, and `water` accumulator set to `0`.

The `while (left < right)` loop drives the pointer convergence:
- **Left branch (`height[left] <= height[right]`):** 
  - `leftMax = Math.max(leftMax, height[left]);` updates the maximum height encountered so far from the left.
  - `water += leftMax - height[left];` calculates the water trapped on top of the current bar using the difference between `leftMax` and the bar's own height.
  - `left++;` shifts the left pointer inward.
- **Right branch (else):** 
  - `rightMax = Math.max(rightMax, height[right]);` updates the maximum height encountered so far from the right.
  - `water += rightMax - height[right];` computes the water trapped at the current right bar.
  - `right--;` shifts the right pointer inward.

The loop terminates when `left` meets `right`, and the accumulated `water` value is returned.

## Dry Run

Consider the elevation map: `height = [0, 1, 0, 2]`

| Step | `left` | `right` | `height[left]` | `height[right]` | `leftMax` | `rightMax` | Action & Water Added | Total `water` |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: | :--- | :---: |
| 1 | 0 | 3 | 0 | 2 | 0 | 0 | `height[0] <= height[3]` (0 <= 2). `leftMax` becomes 0. Adds `0 - 0 = 0`. `left` -> 1 | 0 |
| 2 | 1 | 3 | 1 | 2 | 1 | 0 | `height[1] <= height[3]` (1 <= 2). `leftMax` becomes 1. Adds `1 - 1 = 0`. `left` -> 2 | 0 |
| 3 | 2 | 3 | 0 | 2 | 1 | 0 | `height[2] <= height[3]` (0 <= 2). `leftMax` remains 1. Adds `1 - 0 = 1`. `left` -> 3 | 1 |

Loop terminates as `left` (3) is no longer `<` `right` (3). Returns `1`.

## Complexity Analysis

- **Time Complexity:** $\mathcal{O}(n)$, where $n$ is the length of the `height` array. Each element is visited at most once as the `left` and `right` pointers move toward each other.
- **Space Complexity:** $\mathcal{O}(1)$. Only a few primitive integer variables (`left`, `right`, `leftMax`, `rightMax`, `water`) are used, requiring constant extra memory.

## Alternative Approach

An alternative approach is using **Dynamic Programming** or a **Monotonic Stack**. The DP approach builds prefix-max and suffix-max arrays in $\mathcal{O}(n)$ time, but consumes $\mathcal{O}(n)$ auxiliary space. The stack-based approach also runs in $\mathcal{O}(n)$ time and space by keeping track of decreasing bar heights to compute water horizontally layer by layer.

## Important Takeaways

> [!TIP]
> Whenever a problem relies on comparing left and right boundaries (like trapping water or container with most water), two pointers starting at opposite ends often eliminate the need for auxiliary space.

- Relying on the relative ordering `height[left] <= height[right]` safely bounds the unknown side by the known side.
- Always initialize your running maximums to `0` (or the initial boundary values) to avoid incorrect negative water calculations.

## Final Summary

This solution efficiently solves the Trapping Rain Water problem using a two-pointer technique. By evaluating elements from the outside in and leveraging the shorter boundary constraint, it achieves an optimal $\mathcal{O}(n)$ time complexity and $\mathcal{O}(1)$ space complexity without requiring pre-computed arrays.

