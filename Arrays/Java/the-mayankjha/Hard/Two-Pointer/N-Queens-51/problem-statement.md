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

The solution employs **Backtracking** to explore the state space of queen placements row by row. Since each queen must occupy a unique row, we process the board from `row = 0` to `n-1`.

1.  **Board Representation**: A `char[][] board` initialized with `'.'` tracks the current configuration.
2.  **Constraint Tracking**: To achieve $O(1)$ validation for each placement, we use three boolean arrays:
    *   `cols`: Tracks if a column is occupied.
    *   `diag1`: Tracks "major" diagonals (top-left to bottom-right). Using the property `row - col + (n - 1)`, all cells on the same major diagonal map to the same index.
    *   `diag2`: Tracks "minor" diagonals (top-right to bottom-left). Using the property `row + col`, all cells on the same minor diagonal map to the same index.
3.  **Recursion**: The `backtrack` function attempts to place a queen in every column of the current `row`. If a placement is valid (checked via the boolean arrays), we mark the state, recurse to `row + 1`, and then **backtrack** by resetting the state.

## 🔍 Key Invariant: Diagonal Mapping
The core of the efficiency lies in the coordinate transformation for diagonals:
*   **Major Diagonals (`diag1`)**: Constant `row - col`. We add `n - 1` to ensure the index is non-negative, resulting in range `[0, 2n - 2]`.
*   **Minor Diagonals (`diag2`)**: Constant `row + col`. The range is `[0, 2n - 2]`.

## 🧪 Dry Run
For `n = 4`:
1.  `backtrack(0, ...)`: Try `cols[0]`. `board[0][0] = 'Q'`.
2.  `backtrack(1, ...)`: Try `cols[0]` (blocked), `cols[1]` (blocked by diag), `cols[2]` (valid). `board[1][2] = 'Q'`.
3.  Continue until `row == n`. When `row == n`, convert the `board` to a `List<String>` and add to `ans`.
4.  Backtrack: Reset `board[row][col] = '.'` and set boolean flags back to `false`.

## ⏱️ Complexity Analysis
*   **Time Complexity**: $O(N!)$. We have $N$ choices for the first row, $N-2$ for the second, and so on. While the diagonal constraints prune the tree, the upper bound remains factorial.
*   **Space Complexity**: $O(N^2)$ to store the `board` and $O(N)$ for the recursion stack and boolean arrays.

## 🎯 Why this pattern
Backtracking is ideal here because we need to generate *all* valid permutations of queen placements. The boolean arrays act as a "look-up" table, transforming an $O(N)$ validation check into an $O(1)$ check, significantly pruning the search tree.

## 🧠 Pattern Recognition
*   **Constraint Satisfaction**: Whenever you see "no two [items] can share the same [row/col/diagonal]", boolean tracking arrays are the standard optimization.
*   **Exhaustive Search**: If the problem asks for *all* solutions, it is almost certainly a backtracking problem.

## 📝 Important Takeaways
> [!IMPORTANT]
> * **State Reset**: Always reset the board and boolean arrays after the recursive call returns. This is the "back" in backtracking.
> * **Diagonal Math**: Memorize `row - col + n - 1` and `row + col` as the standard way to identify diagonals in a grid.
> * **Base Case**: The recursion terminates when `row == n`, signaling a successful board configuration.

## 🧩 Quick Cheat Sheet
```text
      0 1 2 3 (col)
    0 Q . . .
    1 . . Q .
    2 . . . Q
    3 . Q . .
```
*   `cols[c]` : `true` if column `c` is taken.
*   `diag1[r - c + n - 1]` : `true` if major diagonal is taken.
*   `diag2[r + c]` : `true` if minor diagonal is taken.

## 🏁 Final Summary
> [!SUCCESS]
> You have implemented an efficient N-Queens solver. By using boolean arrays to track constraints, you successfully reduced the validation overhead from $O(N)$ to $O(1)$, allowing the backtracking algorithm to explore the search space as efficiently as possible.


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

