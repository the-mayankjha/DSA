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

The solution employs a **Two Pointers** pattern to efficiently compute the trapped rain water in linear time and constant extra space. Instead of pre-computing the maximum heights from both the left and right using auxiliary arrays (which takes $O(n)$ space), we can maintain two pointers (`left` and `right`) starting at the opposite ends of the `height` array, alongside `leftMax` and `rightMax` trackers.

> [!TIP]
> The core intuition is that the water level above any bar is determined by the *shorter* of the maximum boundaries enclosing it. By comparing `height[left]` and `height[right]`, we can safely process the side with the smaller height, as its trapped water is bounded by its respective side maximum.

## Key Idea / Invariant

- **Invariant:** The pointer with the smaller height dictates the limiting boundary for water accumulation at that position.
- If `height[left] <= height[right]`, the water trapped at the `left` pointer depends strictly on `leftMax`, because a taller or equal bar exists somewhere at or to the right (`right`). Thus, we do not need to know the exact maximum on the far right to calculate water at the current `left`.

## Code Explanation

The execution unfolds through the following steps:

1. **Initialization**: 
   - `left` is set to `0` and `right` is set to `height.length - 1`.
   - `leftMax` and `rightMax` are initialized to `0` to track the highest bars encountered so far from the left and right extremities.
   - `water` accumulates the total trapped water volume.

2. **Pointer Convergence (`while (left < right)`)**:
   - The loop runs until the two pointers meet.
   - **Left Branch (`height[left] <= height[right]`)**:
     - `leftMax` is updated using `Math.max(leftMax, height[left])`.
     - The water accumulated at the current `left` index is added to `water` via `leftMax - height[left]`.
     - `left` is incremented to evaluate the next bar.
   - **Right Branch (`height[left] > height[right]`)**:
     - `rightMax` is updated using `Math.max(rightMax, height[right])`.
     - The water accumulated at the current `right` index is added to `water` via `rightMax - height[right]`.
     - `right` is decremented to evaluate the previous bar.

3. **Termination**:
   - Once `left` meets `right`, the loop terminates, and the total accumulated `water` is returned.

## Dry Run

Consider `height = [0, 1, 0, 2]`:

| Iteration | `left` | `right` | `height[left]` | `height[right]` | `leftMax` | `rightMax` | Action | `water` |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 0 | 3 | 0 | 2 | 0 | 0 | `height[0] <= height[3]` (Left branch) | `0 + (0 - 0) = 0` |
| 2 | 1 | 3 | 1 | 2 | 1 | 0 | `height[1] <= height[3]` (Left branch) | `0 + (1 - 1) = 0` |
| 3 | 2 | 3 | 0 | 2 | 1 | 0 | `height[2] <= height[3]` (Left branch) | `0 + (1 - 0) = 1` |
| - | 3 | 3 | - | - | - | - | Loop terminates (`left < right` fails) | 1 |

Final output: `1`.

## Complexity Analysis

- **Time Complexity:** $\mathcal{O}(n)$, where $n$ is the length of the `height` array. Each element is visited at most once as the `left` and `right` pointers move towards each other.
- **Space Complexity:** $\mathcal{O}(1)$. Only a few primitive variables (`left`, `right`, `leftMax`, `rightMax`, `water`) are used, requiring constant extra memory regardless of input size.

## Alternative Approach

An alternative approach is using **Dynamic Programming** or a **Monotonic Stack**. 
- The DP approach creates two auxiliary arrays (`leftMax` and `rightMax`) to store the maximum boundaries for every index, taking $\mathcal{O}(n)$ time and $\mathcal{O}(n)$ space.
- The two-pointer solution shown here optimizes the space complexity down to $\mathcal{O}(1)$ by observing that we only need the running maximums relative to the active pointer positions.

## Important Takeaways

- **Space-Time Tradeoff:** By carefully analyzing boundary dependencies, we eliminated the need for auxiliary arrays.
- **Pointer Direction:** Moving the pointer with the smaller height guarantees correctness because water capacity is bounded by the minimum of the two outer maxima.

## Final Summary

This solution efficiently calculates trapped rainwater in $\mathcal{O}(n)$ time and $\mathcal{O}(1)$ space by leveraging two inward-moving pointers and running maximum trackers. It avoids unnecessary pre-computation arrays by resolving water heights greedily from the shorter boundary.

