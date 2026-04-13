package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC200 {

    public static final int[] dx = {0, 1, 0, -1};
    public static final int[] dy = {1, 0, -1, 0};

    public int numIslands(char[][] grid) {
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        int cnt = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == '1' && !visit[x][y]) {
                    cnt++;  // 새 섬 발견
                    search(grid, visit, x, y);  // 섬 전체 방문 처리
                }
            }
        }

        return cnt;
    }

    private void search(char[][] grid, boolean[][] visit, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y });
        visit[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x1 = cur[0] + dx[i];
                int y1 = cur[1] + dy[i];

                if (x1 < 0 ||
                    y1 < 0 ||
                    x1 > grid.length - 1 ||
                    y1 > grid[0].length - 1) {
                    continue;
                }

                if (grid[x1][y1] == '1' && !visit[x1][y1]) {
                    visit[x1][y1] = true;
                    queue.add(new int[] { x1, y1 });
                }
            }
        }
    }
}
