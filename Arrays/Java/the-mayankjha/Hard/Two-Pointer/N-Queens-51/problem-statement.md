# N-Queens

<p>
  <a href="https://leetcode.com/problems/n-queens/">
    <img src="https://img.shields.io/badge/LeetCode-%2351-orange?style=for-the-badge&logo=leetcode&logoColor=white" height="40">
  </a>
  <img src="https://img.shields.io/badge/Difficulty-Hard-yellow?style=for-the-badge" height="40">
  <img src="https://img.shields.io/badge/Pattern-Two%20Pointer-blue?style=for-the-badge" height="40">
</p>

**Tags**

![Array](https://img.shields.io/badge/Array-444?style=flat-square)
![Backtracking](https://img.shields.io/badge/Backtracking-444?style=flat-square)
![Algorithm X](https://img.shields.io/badge/Algorithm%20X-444?style=flat-square)

## AI Notes

## 🧠 Approach

The solution employs **Backtracking** to explore the state space of queen placements row by row. Since each queen must occupy a unique row and column, we process row `r` from `0` to `n-1`. For each row, we attempt to place a queen in every column `c` that is not under attack.

1.  **State Representation**: We maintain a 2D `char[][] board` initialized with `.` to track placements.
2.  **Constraint Tracking**: To achieve $O(1)$ lookup for safety checks, we use three boolean arrays:
    *   `cols`: Tracks if a column is occupied.
    *   `diag1`: Tracks "major" diagonals (top-left to bottom-right). Using the formula `row - col + n - 1` maps these to a unique index in the range `[0, 2n-2]`.
    *   `diag2`: Tracks "minor" diagonals (top-right to bottom-left). Using the formula `row + col` maps these to a unique index in the range `[0, 2n-2]`.
3.  **Recursion**: The `backtrack` method attempts to place a queen in `row`. If successful, it marks the constraints, recurses to `row + 1`, and then performs **backtracking** (resetting the board and boolean flags) to explore other configurations.

## 🔍 Key Idea: Constraint Mapping
The core efficiency comes from the mathematical transformation of diagonal coordinates into array indices:
*   **Major Diagonals (`diag1`)**: Along any such diagonal, `row - col` is constant. Adding `n - 1` shifts this to a non-negative index.
*   **Minor Diagonals (`diag2`)**: Along any such diagonal, `row + col` is constant.

## 🧪 Dry Run
For `n = 4`:
1.  `backtrack(0)`: Try `col = 0`. Mark `cols[0]`, `diag1[0-0+3]`, `diag2[0+0]`.
2.  `backtrack(1)`: Try `col = 2`. Check if `cols[2]`, `diag1[1-2+3]`, `diag2[1+2]` are free.
3.  If a row has no valid columns, the function returns, effectively pruning that branch of the search tree.
4.  When `row == n`, the current `board` state is converted to a `List<String>` and added to `ans`.

## ⏱️ Complexity Analysis
*   **Time Complexity**: $O(N!)$. While there are $N^N$ possible placements, the constraints significantly prune the search tree. The number of valid configurations is much smaller than $N!$.
*   **Space Complexity**: $O(N^2)$ to store the `board`, plus $O(N)$ for the recursion stack and the boolean tracking arrays.

## 🎯 Why this pattern
Backtracking is ideal here because the problem requires finding *all* valid configurations. The state-space tree is naturally defined by the row-by-row placement, and the boolean arrays allow us to prune invalid branches immediately without iterating through the entire board to check for safety.

## 🧠 Pattern Recognition
*   **Constraint Satisfaction**: Whenever you see "no two [items] can share the same [row/col/diagonal]," boolean tracking arrays are the standard optimization.
*   **Backtracking**: Use this when you need to explore all paths in a decision tree and "undo" a choice to explore the next branch.

## 📝 Important Takeaways
> [!IMPORTANT]
> *   **Coordinate Transformation**: Memorize `row - col + (n - 1)` and `row + col` for diagonal tracking.
> *   **Backtracking Step**: Always reset the state (`board[r][c] = '.'` and `boolean = false`) immediately after the recursive call returns.
> *   **Base Case**: The recursion terminates when `row == n`, indicating a successful placement of all $N$ queens.

## 🧩 Quick Cheat Sheet
```text
      C0 C1 C2
R0 | .  Q  . |  diag1: row - col + (n-1)
R1 | .  .  . |  diag2: row + col
R2 | .  .  . |
```
```java
// Check safety
if (!cols[c] && !diag1[r - c + n - 1] && !diag2[r + c]) {
    // Place queen
    // Recurse
    // Remove queen (Backtrack)
}
```

## 🏁 Final Summary
> [!SUCCESS]
> You have implemented an efficient N-Queens solver using backtracking and constant-time constraint lookups. By mapping diagonal indices to boolean arrays, you've optimized the safety check from $O(N)$ to $O(1)$, ensuring the algorithm performs optimally within the constraints of the search space.


## Problem

The **n-queens** puzzle is the problem of placing `n` queens on an `n x n` chessboard such that no two queens attack each other.

Given an integer `n`, return *all distinct solutions to the **n-queens puzzle***. You may return the answer in **any order**.

Each solution contains a distinct board configuration of the n-queens' placement, where `'Q'` and `'.'` both indicate a queen and an empty space, respectively.

![Problem diagram](https://assets.leetcode.com/uploads/2020/11/13/queens.jpg)

### Examples

### Example 1

**Input:**

```text
n = 4
```

**Output:**

```text
[
    [
        ".Q..",
        "...Q",
        "Q...",
        "..Q."
    ],
    [
        "..Q.",
        "Q...",
        "...Q",
        ".Q.."
    ]
]
```

> [!NOTE]
> There exist two distinct solutions to the 4-queens puzzle as shown above

### Example 2

**Input:**

```text
n = 1
```

**Output:**

```text
[
    [
        "Q"
    ]
]
```

## Constraints

- `1 <= n <= 9`


---

## 📝 Notes & Insights

This is manual test for Notes and Insightss


## Solution

```java
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();

        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // row - col + n - 1
        boolean[] diag2 = new boolean[2 * n - 1]; // row + col

        backtrack(0, n, board, cols, diag1, diag2, ans);

        retur
```

## Complexity

- **Time:** See AI Notes for the analysis of this implementation.
- **Space:** See AI Notes for the analysis of this implementation.

