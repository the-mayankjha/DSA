# ♛ N-Queens

> [!IMPORTANT]
> **LeetCode:** #51  
> **Difficulty:** Hard  
> **Pattern:** Two Pointer  
> **Tags:** `Array` `Backtracking` `Algorithm X`

---

## 🧩 Problem

The **n-queens** puzzle is the problem of placing `n` queens on an `n x n` chessboard such that no two queens attack each other.

Given an integer `n`, return *all distinct solutions to the **n-queens puzzle***. You may return the answer in **any order**.

Each solution contains a distinct board configuration of the n-queens' placement, where `'Q'` and `'.'` both indicate a queen and an empty space, respectively.

---

## 📌 Examples

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

---

## ⚙️ Constraints

> [!WARNING]
> `1 <= n <= 9`


---

# 🧠 Approach

The N-Queens problem is a classic backtracking challenge. Since we must place exactly one queen per row, we can process the board row-by-row. For each row, we attempt to place a queen in every column, checking if the position is "safe" before proceeding to the next row.

To optimize the "is safe" check, we maintain three boolean arrays that track occupied columns and diagonals. This avoids the $O(n)$ scan of the board for every placement, reducing the safety check to $O(1)$.

# 🔍 Key Invariants
*   **Column Constraint:** `cols[col]` tracks if column `col` is occupied.
*   **Main Diagonal (`\`) Constraint:** `diag1[row - col + n - 1]` is constant for all cells on the same top-left to bottom-right diagonal.
*   **Anti-Diagonal (`/`) Constraint:** `diag2[row + col]` is constant for all cells on the same top-right to bottom-left diagonal.

# 💻 Java Solution

The solution initializes a character grid filled with `.` and three boolean arrays to track conflicts. The `backtrack` method acts as the recursive engine:

1.  **Base Case:** If `row == n`, we have successfully placed $n$ queens. Convert the `board` to the required `List<String>` format and add to `ans`.
2.  **Recursive Step:** Iterate through columns `0` to `n-1`. If the current cell is safe (checked via the boolean arrays), place a `'Q'`, mark the arrays as `true`, and recurse to `row + 1`.
3.  **Backtrack:** After the recursive call returns, reset the cell to `'.'` and the boolean arrays to `false` to explore other possibilities.

```java
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; 
        boolean[] diag2 = new boolean[2 * n - 1]; 
        backtrack(0, n, board, cols, diag1, diag2, ans);
        return ans;
    }
    
    private void backtrack(int r, int n, char[][] board, boolean[] cols, boolean[] d1, boolean[] d2, List<List<String>> ans) {
        if (r == n) {
            List<String> res = new ArrayList<>();
            for (char[] row : board) res.add(new String(row));
            ans.add(res);
            return;
        }
        for (int c = 0; c < n; c++) {
            if (!cols[c] && !d1[r - c + n - 1] && !d2[r + c]) {
                board[r][c] = 'Q';
                cols[c] = d1[r - c + n - 1] = d2[r + c] = true;
                backtrack(r + 1, n, board, cols, d1, d2, ans);
                board[r][c] = '.';
                cols[c] = d1[r - c + n - 1] = d2[r + c] = false;
            }
        }
    }
}
```

> [!CAUTION]
> The provided user snippet was truncated at `retur`. Ensure the `backtrack` method is fully implemented as shown above to close the logic.

# 🧪 Dry Run
For `n=4`:
1. Place `Q` at `(0,0)`. Mark `cols[0]`, `diag1[3]`, `diag2[0]` as true.
2. Move to `row 1`. Try `(1,0)` (blocked), `(1,1)` (blocked), `(1,2)` (safe).
3. Place `Q` at `(1,2)`. Mark `cols[2]`, `diag1[2]`, `diag2[3]` as true.
4. Continue until `row 4` is reached or no valid moves remain.

# ⏱️ Complexity Analysis
*   **Time Complexity:** $O(N!)$. We have $N$ choices for the first row, $N-2$ for the second, and so on. While pruning reduces the search space, the upper bound remains factorial.
*   **Space Complexity:** $O(N^2)$ to store the board and $O(N)$ for the recursion stack and tracking arrays.

# 🎯 Why this pattern
Backtracking is ideal here because we need to explore all valid configurations. The boolean arrays transform the $O(N)$ safety check into an $O(1)$ lookup, which is critical for performance in a factorial-time algorithm.

# 🧠 Pattern Recognition
*   **Constraint Satisfaction:** Whenever you need to place items with specific "no-attack" rules, use boolean tracking arrays.
*   **Backtracking Template:** `Choose -> Explore -> Un-choose`.

# 📝 Important Takeaways
> [!IMPORTANT]
> - **Diagonal Indexing:** Remember the formulas: `row - col + (n-1)` for main diagonals and `row + col` for anti-diagonals.
> - **State Reset:** Always reset the board and boolean arrays after the recursive call to ensure the next branch starts with a clean state.
> - **Pruning:** The boolean arrays act as a pruning mechanism, preventing the algorithm from exploring invalid branches early.

# 🧩 Quick Cheat Sheet
```text
  Diagonals:
  \ (row-col+n-1) | / (row+col)
  0 1 2           | 0 1 2
  1 2 3           | 1 2 3
  2 3 4           | 2 3 4
```

# 🏁 Final Summary
> [!SUCCESS]
> You have successfully implemented an optimized backtracking solution for N-Queens. By using boolean arrays to track constraints, you've ensured that your solution is efficient and clean. Great work!

---

## 🔖 Tags

#leetcode #n-queens #two-pointer #array #backtracking #algorithm-x
