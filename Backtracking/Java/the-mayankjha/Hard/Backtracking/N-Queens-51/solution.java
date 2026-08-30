[d1] || diag2[d2]) {
                continue;
            }

            // Place queen
            board[row][col] = 'Q';
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            // Move to next row
            backtrack(row + 1, n, board, cols, diag1, diag2, ans);

            // Backtrack / remove queen
            board[row][col] = '.';
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}