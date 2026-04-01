package practice.algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
  0 representing an empty cell,
  1 representing a fresh orange, or
  2 representing a rotten orange.
*/

public class LC994 {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public int orangesRotting(int[][] grid) {
        int minute = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    visited[i][j] = true;
                    queue.add(new int[] {i, j, 0});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] at = queue.poll();
            int x = at[0];
            int y = at[1];
            minute = at[2];
            for (int i = 0; i < 4; i++) {
                int newx = x + dx[i];
                int newy = y + dy[i];
                if (newx < 0 ||
                    newy < 0 ||
                    newx >= grid.length ||
                    newy >= grid[0].length) {
                    continue;
                }

                if (grid[newx][newy] == 1 && !visited[newx][newy]) {
                    grid[newx][newy] = 2;
                    visited[newx][newy] = true;
                    queue.add(new int[] {newx, newy, (minute + 1)});
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }

        return minute;
    }
}
