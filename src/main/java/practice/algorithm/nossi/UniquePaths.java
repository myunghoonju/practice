package practice.algorithm.nossi;

import java.util.ArrayList;
import java.util.List;

// https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
public class UniquePaths {
  
  // Memoization(top-down)
  public int sol(int row, int col) {
    List<List<Integer>> memo = new ArrayList<>();
    for (int i = 0; i <= row; i++) {
      List<Integer> r = new ArrayList<>();
      for (int j = 0; j <= col; j++) {
        r.add(0);
      }

      memo.add(r);
    }

    return cnt(row, col, memo);
  }

  static int cnt(int row,
                 int col,
                 List<List<Integer>> memo) {
    if (row == 1 || col == 1) {
      memo.get(row).set(col, 1);
      return 1;
    }

    if (memo.get(row).get(col) == 0) {
      int paths = cnt(row - 1, col, memo) + cnt(row, col - 1, memo);
      memo.get(row).set(col, paths);
    }

    return memo.get(row).get(col);
  }

  // Tabulation(bottom-up)
  static int solution(int row, int col) {
    int[][] path = new int[row][col];
    for (int i = 0; i < row; i++) {
      path[i][0] = 1;
    }

    for (int i = 0; i < col; i++) {
      path[0][i] = 1;
    }

    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        path[i][j] = path[i - 1][j] + path[i][j - 1];
      }
    }
    return path[row - 1][col - 1];
  }

  // time exceeded
  static int solutionRecursion(int row, int col) {
    if (row == 1 || col == 1) {
      return 1;
    }

    return solutionRecursion(row - 1, col) +
           solutionRecursion(row, col - 1);
  }

}
