package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC1926 {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length, cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];   // 2번: maze 크기 기준
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{entrance[0], entrance[1]});  // 3번: entrance부터 시작
        visited[entrance[0]][entrance[1]] = true;

        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;                     // 레벨(=한 걸음)마다 1씩 증가
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dx[d];
                    int nc = cur[1] + dy[d];

                    // TODO: nr, nc가 범위 안인지, 벽('+')이 아닌지, 이미 방문했는지 확인
                    if (nr < 0 ||
                        nr >= rows ||
                        nc < 0 ||
                        nc >= cols ||
                        maze[nr][nc] == '+' ||
                        visited[nr][nc]) {
                        continue;
                    }

                    // TODO: 여기가 4번 - 이 셀이 border이고 entrance가 아니면 return steps
                    boolean isBorder = nr == 0 || nr == rows - 1 || nc == 0 || nc == cols - 1;
                    boolean isEntrance = nr == entrance[0] && nc == entrance[1];
                    if (isBorder && !isEntrance) {
                        return steps;
                    }

                    // TODO: 아니면 visited 표시하고 queue.add(new int[]{nr, nc})
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }

        return -1;
    }
}
