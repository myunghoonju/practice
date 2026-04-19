package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC695 {

    private static int[] dx = { 0, 1, 0, -1 };
    private static int[] dy = { 1, 0, -1, 0 };

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        boolean[][] visit = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visit[i][j]) {
                    max = Math.max(max, bfs(grid, visit, i, j));
                }
            }
        }

        return max;
    }

    private int bfs(int[][] grid, boolean[][] visit, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visit[x][y] = true;

        int area = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x1 = poll[0];
            int y1 = poll[1];
            area++;

            for (int i = 0; i < 4; i++) {
                int x2 = x1 + dx[i];
                int y2 = y1 + dy[i];

                if (x2 < 0 ||
                    y2 < 0 ||
                    x2 >= grid.length ||
                    y2 >= grid[0].length) {
                    continue;
                }

                if (grid[x2][y2] == 1 && !visit[x2][y2]) {
                    visit[x2][y2] = true;
                    queue.add(new int[]{x2, y2});
                }
            }
        }

        return area;
    }
}
