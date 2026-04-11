package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC1926 {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public int nearestExit(char[][] maze, int[] entrance) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[entrance[0]][entrance[1]] = true;
        queue.add(new int[] {entrance[0], entrance[1], 0});
        for (int i = 0; i < maze.length; i++) {
            for (int i1 = 0; i1 < maze[0].length; i1++) {
                if (maze[i][i1] == '+') {
                    visited[i][i1] = true;
                }
            }
        }

        return findExit(queue, maze, visited);
    }

    private int findExit(Queue<int[]> queue,
                         char[][] maze,
                         boolean[][] visited) {
            while (!queue.isEmpty()) {
                int[] xy = queue.poll();
                int x = xy[0];
                int y = xy[1];
                int step = xy[2];
                for (int i = 0; i < 4; i++) {
                    int newx = x + dx[i];
                    int newy = y + dy[i];
                    if (newx < 0 ||
                        newy < 0 ||
                        newx >= maze.length ||
                        newy >= maze[0].length ||
                        visited[newx][newy]) {
                        continue;
                    }

                    if (maze[newx][newy] == '.' && !visited[newx][newy]) {
                        visited[newx][newy] = true;
                        queue.add(new int[] {newx, newy, step + 1});
                        if (newx == 0 ||
                            newy == 0 ||
                            newx == maze.length - 1 || newy == maze[0].length - 1) {
                                return step + 1;
                        }
                    }
                }
            }

        return -1;
    }
}
