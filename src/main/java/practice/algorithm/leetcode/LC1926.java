package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC1926 {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public int nearestExit(char[][] maze, int[] entrance) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(entrance);

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[entrance[0]][entrance[1]] = true;

        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int i1 = 0; i1 < 4; i1++) {
                    int nx = current[0] + dx[i1];
                    int ny = current[1] + dy[i1];
                    if (nx < 0 || ny < 0 || nx >= maze.length || ny >= maze[0].length) {
                        continue;
                    }

                    if (visited[nx][ny]) {
                        continue;
                    }

                    if (maze[nx][ny] == '+') {
                        continue;
                    }

                    if (nx == 0 || nx == maze.length - 1 || ny == 0 || ny == maze[0].length - 1) {
                        return steps;
                    }

                    visited[nx][ny] = true;

                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return -1;
    }

    public int nearestExit2(char[][] maze, int[] entrance) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        queue.add(new int[] {entrance[0], entrance[1], 0});
        visited[entrance[0]][entrance[1]] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            int d = point[2];

            if ((x == 0 ||
                 y == 0 ||
                 x == maze.length - 1 ||
                 y == maze[0].length - 1) &&
                !(x == entrance[0] && y == entrance[1])) {
                return d;
            }


            for (int j = 0; j < 4; j++) {
                int newx = x + dx[j];
                int newy = y + dy[j];
                if (newx >= 0 &&
                    newy >= 0 &&
                    newx < maze.length &&
                    newy < maze[0].length &&
                    maze[newx][newy] == '.') {
                    if (visited[newx][newy]) {
                        continue;
                    }

                    visited[newx][newy] = true;
                    queue.add(new int[] {newx, newy, d+1});
                }
            }
        }

        return -1;
    }
}
