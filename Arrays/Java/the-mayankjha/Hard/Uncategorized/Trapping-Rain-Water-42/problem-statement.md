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

The solution uses an optimized **Two Pointer** pattern to compute the trapped rain water in $O(1)$ space. Instead of pre-computing the maximum heights for every index using extra arrays (Dynamic Programming), we maintain two pointers (`left` and `right`) starting at both ends of the `height` array, moving inward while keeping track of the running maximum heights (`leftMax` and `rightMax`).

## Key Idea / Invariant

> [!NOTE]
> The water trapped at any index is determined by the *shorter* of the maximum walls enclosing it from the left and right. 

By comparing `height[left]` and `height[right]`, the pointer pointing to the smaller bar is guaranteed to be bounded by the maximum height seen on its side—regardless of what towers lie beyond the opposing pointer. This allows us to process elements greedily without knowing the absolute maximum of the entire array.

## Code Explanation

- `left` and `right`: Pointers initialized to `0` and `height.length - 1` respectively, defining the current boundaries of our search space.
- `leftMax` and `rightMax`: Running maximum heights encountered so far from the left and right boundaries.
- `water`: Accumulator storing the total volume of trapped water.
- `while (left < right)`: The main loop narrows the window from both sides until the pointers meet.
- `if (height[left] <= height[right])`: Processes the left side when the left bar is shorter or equal, ensuring `leftMax` safely bounds the water calculation.
  - `leftMax = Math.max(leftMax, height[left])`: Updates the highest bar seen on the left.
  - `water += leftMax - height[left]`: Adds the water trapped on top of the current `left` bar.
  - `left++`: Advances the left pointer inward.
- `else`: Processes the right side symmetrically when the right bar is strictly shorter.
  - `rightMax = Math.max(rightMax, height[right])`: Updates the highest bar seen on the right.
  - `water += rightMax - height[right]`: Adds the water trapped on top of the current `right` bar.
  - `right--`: Moves the right pointer inward.

## Dry Run

Let's trace `height = [0, 1, 0, 2]`.

1. Initial state: `left = 0`, `right = 3`, `leftMax = 0`, `rightMax = 0`, `water = 0`.
2. Iteration 1: `height[0]` (0) `<= height[3]` (2) is true.
   - `leftMax = max(0, 0) = 0`
   - `water += 0 - 0 = 0`
   - `left` becomes `1`.
3. Iteration 2: `height[1]` (1) `<= height[3]` (2) is true.
   - `leftMax = max(0, 1) = 1`
   - `water += 1 - 1 = 0`
   - `left` becomes `2`.
4. Iteration 3: `height[2]` (0) `<= height[3]` (2) is true.
   - `leftMax = max(1, 0) = 1`
   - `water += 1 - 0 = 1` (Accumulated water = 1)
   - `left` becomes `3`.
5. Loop terminates because `left` (3) is no longer `< right` (3).
6. Returns `water = 1`.

## Complexity Analysis

- **Time Complexity:** $\mathcal{O}(n)$, where $n$ is the length of `height`. Each element is visited at most once as the `left` and `right` pointers converge toward the center.
- **Space Complexity:** $\mathcal{O}(1)$. Only a few primitive integer variables (`left`, `right`, `leftMax`, `rightMax`, `water`) are used, requiring constant extra space.

## Alternative Approach

An alternative approach uses **Dynamic Programming** with two auxiliary arrays, `leftMax` and `rightMax`, populated in forward and backward passes. While it achieves $\mathcal{O}(n)$ time, it requires $\mathcal{O}(n)$ space. The two-pointer solution shown here optimizes the space complexity down to $\mathcal{O}(1)$ by evaluating bounds on the fly.

## Important Takeaways

> [!TIP]
> Whenever an array problem requires comparing elements relative to left and right maximums, think about whether a two-pointer approach from both extremities can eliminate the need for prefix/suffix arrays.

## Final Summary

This solution provides an optimal, interview-standard implementation for the Trapping Rain Water problem. By leveraging two converging pointers and dynamic boundary tracking, it computes the result in linear time with zero auxiliary memory overhead.

