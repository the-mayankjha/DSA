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

The solution employs a **Two Pointers** pattern to efficiently compute the trapped rain water in linear time and constant extra space. Instead of precomputing the maximum boundary heights for every element using extra arrays (which would take $O(n)$ space), we maintain two pointers (`left` and `right`) starting at opposite ends of the `height` array, along with `leftMax` and `rightMax` variables to track the highest walls seen so far from each side.

## Key Idea / Invariant

> [!NOTE]
> The amount of water trapped at any given index is determined by the *shorter* of the maximum boundaries on its left and right. 

By comparing `height[left]` and `height[right]`, we can guarantee that the side with the smaller height is bounded by the current opposite maximum. This allows us to process the side with the smaller height safely without knowing the exact maximum on the other side.

## Code Explanation

1. **Initialization**: 
   - `left` is set to `0` and `right` is set to `height.length - 1`.
   - `leftMax` and `rightMax` are initialized to `0` to track peak heights.
   - `water` accumulates the total trapped water.

2. **Traversal (`while (left < right)`)**:
   - The loop continues until the two pointers meet.
   - **`if (height[left] <= height[right])`**: Since the left bar is shorter or equal, the water level at `left` is strictly bounded by `leftMax`. 
     - `leftMax` is updated via `Math.max(leftMax, height[left])`.
     - The trapped water at the current `left` index (`leftMax - height[left]`) is added to `water`.
     - `left` is incremented to move inward.
   - **`else`**: The right bar is shorter, meaning the water level at `right` is bounded by `rightMax`.
     - `rightMax` is updated via `Math.max(rightMax, height[right])`.
     - The trapped water at the current `right` index (`rightMax - height[right]`) is added to `water`.
     - `right` is decremented to move inward.

## Dry Run

Consider `height = [0, 1, 0, 2]`:
- Initial state: `left = 0`, `right = 3`, `leftMax = 0`, `rightMax = 0`, `water = 0`.
- **Iteration 1**: `height[0]` (`0`) `<=` `height[3]` (`2`). 
  - `leftMax = Math.max(0, 0) = 0`. 
  - `water += 0 - 0 = 0`. 
  - `left` becomes `1`.
- **Iteration 2**: `height[1]` (`1`) `>` `height[3]` (`2` is false; `height[1]` is `1`, `height[3]` is `2`, so `height[left] <= height[right]` is true? Wait, `height[1]` is `1`, `height[3]` is `2`. `1 <= 2` is `true`).
  - `leftMax = Math.max(0, 1) = 1`.
  - `water += 1 - 1 = 0`.
  - `left` becomes `2`.
- **Iteration 3**: `height[2]` (`0`) `<=` `height[3]` (`2`).
  - `leftMax = Math.max(1, 0) = 1`.
  - `water += 1 - 0 = 1` (`water` becomes `1`).
  - `left` becomes `3`.
- Loop terminates since `left` (`3`) is no longer `< right` (`3`). Returns `1`.

## Complexity Analysis

- **Time Complexity**: $\mathcal{O}(n)$, where $n$ is the length of the `height` array. Each element is visited at most once as the `left` and `right` pointers move toward each other.
- **Space Complexity**: $\mathcal{O}(1)$. Only a few primitive variables (`left`, `right`, `leftMax`, `rightMax`, `water`) are used, requiring constant extra space.

## Alternative Approach

An alternative approach uses **Dynamic Programming** by precalculating prefix maximums and suffix maximums in two separate arrays. While conceptually straightforward, it requires $\mathcal{O}(n)$ extra space. Another alternative uses a **Monotonic Stack** to keep track of bars, which also runs in $\mathcal{O}(n)$ time and $\mathcal{O}(n)$ space. The two-pointer approach implemented here is optimal because it achieves $\mathcal{O}(1)$ space complexity.

## Important Takeaways

> [!TIP]
> When a problem asks to compute trapped area or boundary-dependent values in an array, a two-pointer approach starting from both ends can often eliminate the need for auxiliary prefix/suffix arrays.

> [!IMPORTANT]
> Always verify pointer boundaries (`left < right`) and ensure updates to running maximums (`leftMax`, `rightMax`) happen *before* calculating trapped water for the current index.

## Final Summary

This solution efficiently calculates trapped rain water using two inward-moving pointers and running maximums. By evaluating the smaller of the two boundary heights at each step, it guarantees accurate water-level calculations in linear time ($\mathcal{O}(n)$) and constant space ($\mathcal{O}(1)$).

