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
}
