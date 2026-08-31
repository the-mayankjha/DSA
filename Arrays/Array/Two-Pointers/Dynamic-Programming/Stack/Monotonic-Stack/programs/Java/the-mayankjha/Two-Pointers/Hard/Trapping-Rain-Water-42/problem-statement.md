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

The solution employs a **Two Pointers** pattern to efficiently compute the trapped rain water in $O(n)$ time and $O(1)$ extra space. Instead of using auxiliary arrays to precompute the maximum heights from the left and right for every index, we maintain two boundary pointers (`left` and `right`) and two running maximum variables (`leftMax` and `rightMax`) to process the elevation map inward from both ends simultaneously.

## Key Idea / Invariant

> [!IMPORTANT]
> The water level at any given index is bounded by the *shorter* of the maximum heights found to its left and right. 
> 
> By comparing `height[left]` and `height[right]`, we guarantee that whichever side is smaller is strictly bounded by the maximum height on the *opposite* side. This allows us to safely calculate the trapped water on the smaller side immediately without needing to know the exact maximum height on the other side.

## Code Explanation

We trace the execution flow through the pointers and variables:

1. **Initialization:**
   - `left = 0` and `right = height.length - 1` start at opposite ends of the elevation map.
   - `leftMax = 0` and `rightMax = 0` track the peak heights seen so far from the left and right bounds.
   - `water = 0` accumulates the total trapped volume.

2. **Pointer Convergence (`while (left < right)`):**
   - The loop continues as long as the `left` pointer hasn't met the `right` pointer.
   - **Left Branch (`if (height[left] <= height[right])`):**
     - We process the `left` side because its height is less than or equal to the right boundary, meaning `rightMax` is guaranteed to be at least as tall as `height[right]` (and thus taller than `height[left]`).
     - `leftMax = Math.max(leftMax, height[left])` updates the highest bar seen from the left.
     - `water += leftMax - height[left]` adds the water trapped above the current `left` bar.
     - `left++` shifts the left pointer inward.
   - **Right Branch (else):**
     - We process the `right` side because `height[right] < height[left]`, ensuring `leftMax` acts as a safe upper bound for the right side.
     - `rightMax = Math.max(rightMax, height[right])` updates the highest bar seen from the right.
     - `water += rightMax - height[right]` adds the water trapped above the current `right` bar.
     - `right--` shifts the right pointer inward.

3. **Termination:**
   - When `left` meets `right`, all bars have been evaluated, and the final accumulated `water` value is returned.

## Dry Run

Consider the input `height = [0, 1, 0, 2]`:

| Iteration | `left` | `right` | `height[left]` | `height[right]` | `leftMax` | `rightMax` | Branch Taken | `water` (Added $\rightarrow$ Total) |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :--- | :--- |
| 1 | 0 | 3 | 0 | 2 | 0 | 0 | Left (`0 <= 2`) | $0 - 0 = 0 \rightarrow 0$ |
| 2 | 1 | 3 | 1 | 2 | 1 | 0 | Left (`1 <= 2`) | $1 - 1 = 0 \rightarrow 0$ |
| 3 | 2 | 3 | 0 | 2 | 1 | 0 | Left (`0 <= 2`) | $1 - 0 = 1 \rightarrow 1$ |
| 4 | 3 | 3 | 2 | 2 | — | — | Loop terminates (`left == right`) | Final: `1` |

> [!NOTE]
> Notice how `left` advances past index 2, meeting `right` at index 3, successfully capturing the unit of water trapped at index 2.

## Complexity Analysis

- **Time Complexity:** $O(n)$, where $n$ is the length of the `height` array. Each element is visited at most once as the `left` and `right` pointers move towards each other.
- **Space Complexity:** $O(1)$. Only a few primitive variables (`left`, `right`, `leftMax`, `rightMax`, `water`) are used, requiring constant extra memory regardless of input size.

## Important Takeaways

> [!TIP]
> **Reducing Space from $O(n)$ to $O(1)$:** Dynamic programming solutions typically require two arrays of size $n$ to store prefix and suffix maximums. The two-pointer technique cleverly eliminates this overhead by recognizing that we only need to compare the active outer bounds.

## Final Summary

This solution elegantly solves the Trapping Rain Water problem by leveraging two converging pointers and running maximums. By always processing the smaller height side, it safely calculates trapped water on-the-fly without precomputing or storing boundary heights, achieving optimal $O(n)$ time and $O(1)$ space complexity.

