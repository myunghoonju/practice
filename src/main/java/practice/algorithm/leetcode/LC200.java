package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC200 {

    /**
     1. 모든 칸 순회  <br>
     2. 방문 안 한 '1'을 만나면 → BFS 시작, 연결된 모든 '1' 방문 처리  <br>
     3. 카운트 + 1
    */

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public int numIslands(char[][] grid) {
        int cnt = 0;
        boolean[][] visit = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visit[i][j]) {
                    search(grid, i, j, visit);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private void search(char[][] grid,
                        int x,
                        int y,
                        boolean[][] visit) {
        visit[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int i = 0; i < dx.length; i++) {
                int nx = dx[i] + xy[0];
                int ny = dy[i] + xy[1];
                if (nx < 0 ||
                    ny < 0 ||
                    nx >= grid.length ||
                    ny >= grid[0].length) {
                    continue;
                }

                if (grid[nx][ny] == '1' && !visit[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    visit[nx][ny] = true;
                }
            }
        }
    }
}
