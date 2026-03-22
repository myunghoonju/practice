package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC994 {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] { i, j, 0 });
                }

                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        int elapsed = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < dx.length; i++) {
                int x = poll[0] + dx[i];
                int y = poll[1] + dy[i];

                if (x < 0 || x >= grid.length ||
                    y < 0 || y >= grid[0].length) {
                    continue;
                }

                if (grid[x][y] == 1) {
                    grid[x][y] = 2;
                    fresh--;
                    elapsed = Math.max(elapsed, poll[2] + 1);
                    queue.add(new int[] { x, y, poll[2] + 1});
                }
            }
        }

        return fresh > 0 ? -1 : elapsed;
    }
}
