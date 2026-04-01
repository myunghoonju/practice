package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

// 0 - sea, 1 - land
public class LC1020 {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    // count 1s surrounded by 0s && not edge
    public int numEnclaves(int[][] grid) {
        int enclave = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if ((i == 0 ||
                    i == grid.length-1 ||
                    j == 0 ||
                    j == grid[0].length-1) && (grid[i][j] == 1)) {
                    q.add(new int[] { i, j });
                    visit[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            for (int i = 0; i < dx.length; i++) {
                int x1 = x + dx[i];
                int y1 = y + dy[i];
                if (x1 < 0 || y1 < 0 || x1 >= grid.length || y1 >= grid[0].length) {
                    continue;
                }

                if (grid[x1][y1] == 1 && !visit[x1][y1]) {
                    visit[x1][y1] = true;
                    q.add(new int[] {x1, y1});
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visit[i][j]) {
                    enclave++;
                }
            }
        }


        return enclave;
    }
}
