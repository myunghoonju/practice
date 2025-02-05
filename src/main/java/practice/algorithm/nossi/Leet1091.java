package practice.algorithm.nossi;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/shortest-path-in-binary-matrix/description
public class Leet1091 {

  private static final int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

  private static int solWithBfs(int [][] grid) {
    int length = grid.length;

    if (grid[0][0] == 1 || grid[length -1][length -1] == 1) {
      return -1;
    }

    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{0, 0});
    grid[0][0] = -1;
    int ans = 0;
    while (!queue.isEmpty()) {
      ans++;
      for (int i = queue.size(); i > 0 ; i--) {
        int[] at = queue.poll();
        int row = at[0];
        int col = at[1];

        if (row == length - 1 && col == length - 1) {
          return ans;
        }

        for (int[] dir : dir) {
          int newRow = row + dir[0];
          int newCol = col + dir[1];
          if (newRow < 0 || newRow == length || newCol < 0 || newCol == length) {
            continue;
          }

          if (grid[newRow][newCol] == 0) {
            continue;
          }

          grid[newRow][newCol] = -1;
          queue.offer(new int[]{newRow, newCol});
        }
      }
    }

    return -1;
  }
}
