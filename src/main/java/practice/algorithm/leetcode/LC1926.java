package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC1926 {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public int nearestExit(char[][] maze, int[] entrance) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{entrance[0], entrance[1], 0});
        maze[entrance[0]][entrance[1]] = '+';

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int idx = 0; idx < dy.length; idx++) {
                    int x = current[0] + dx[idx];
                    int y = current[1] + dy[idx];

                    if (x < 0 || x >= maze.length ||
                        y < 0 || y >= maze[0].length) {
                        continue;
                    }

                    if (maze[x][y] == '+') {
                        continue;
                    }

                    if (x == 0 ||
                        y == 0 ||
                        x ==  maze.length - 1 ||
                        y == maze[0].length - 1) {
                        return current[2] + 1;
                    }

                    maze[x][y] = '+';

                    queue.add(new int[]{x, y, current[2] + 1});
            }
        }

        return -1;
    }
}
