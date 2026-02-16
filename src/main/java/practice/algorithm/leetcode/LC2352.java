package practice.algorithm.leetcode;

import lombok.val;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC2352 {

  public int equalPairs(int[][] grid) {
    int cnt = 0;
    int n = grid.length;

    Map<String, Integer> counter = new HashMap<>();
    for (int[] r : grid) {
      String str = Arrays.toString(r);
      counter.put(str, counter.getOrDefault(str, 0) + 1);
    }

    for (int c = 0; c < n; c++) {
      int[] col = new int[n];
      for (int r = 0; r < n; r++) {
        col[r] = grid[r][c];
      }

      cnt += counter.getOrDefault(Arrays.toString(col), 0);
    }

    return cnt;
  }
}
