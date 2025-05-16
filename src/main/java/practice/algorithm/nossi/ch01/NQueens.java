package practice.algorithm.nossi.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
  
  List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    char[][] board = new char[n][n];
    for (char[] row : board) {
      Arrays.fill(row, '.');
    }

    sol(0, board, result, n);

    return result;
  }

  void sol(int r, char[][] board, List<List<String>> result, int n) {
    // 행 번호가 n, res 정답
    if (r == n) {
      result.add(create(board));
      return;
    }

    for (int col = 0; col < n; col++) {
      // 현재 위치(r, col) 유효한가 검사
      if (valid(board, r, col, n)) {
        board[r][col] = 'Q';
        sol(r + 1, board, result, n);
        board[r][col] = '.';
      }
    }
  }

  List<String> create(char[][] board) {
    List<String> result = new ArrayList<>();
    for (char[] row : board) {
      result.add(new String(row));
    }

    return result;
  }

  boolean valid(char[][] board, int row, int col, int n) {
    // 열 확인
    for (int i = 0; i < row; i++) {
      if (board[i][col] == 'Q') {
        return false;
      }
    }

    // 왼쪽 대각선
    for (int idx = row - 1, j = col - 1; idx >= 0 && j >= 0; idx--, j--) {
        if (board[idx][j] == 'Q') {
          return false;
        }
    }

    // 오른쪽 대각선
    for (int idx = row - 1, j = col + 1; idx >= 0 && j >= 0; idx--, j++) {
      if (board[idx][j] == 'Q') {
        return false;
      }
    }

    return true;
  }
}
