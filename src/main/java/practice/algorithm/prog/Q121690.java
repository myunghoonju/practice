package practice.algorithm.prog;

import java.util.LinkedList;
import java.util.Queue;

public class Q121690 {

  public static final int[] dx = {1, 0, -1, 0};
  public static final int[] dy = {0, -1, 0, 1};

  // 한번 두칸 갈 수 있음(hole을 가로지름)
  public int solution(int n,
                      int m,
                      int[][] hole) {
    boolean[][] isHole = new boolean[n + 1][m + 1]; // 1. hole 위치를 visited와 같은 방식으로 표시
    for (int[] h : hole) {
      isHole[h[0]][h[1]] = true;
    }

    boolean[][][] visited = new boolean[n + 1][m + 1][2]; // 점프 사용여부까지 상태에 포함
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {1, 1, 0, 0}); // {x, y, dist, 점프사용여부}
    visited[1][1][0] = true;

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int x = cur[0], y = cur[1], dist = cur[2], used = cur[3];

      if (x == n && y == m) { // 목적지 도달 체크
        return dist;
      }

      for (int d = 0; d < dx.length; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (!inBounds(nx, ny, n, m)) { // 경계 체크
          continue;
        }

        int nextUsed = used;
        if (isHole[nx][ny]) { // 함정이면, 아직 점프를 안 썼을 때만 한 번 뛰어넘기
          if (used == 1) {
            continue;
          }
          nx += dx[d];
          ny += dy[d];
          if (!inBounds(nx, ny, n, m) || isHole[nx][ny]) {
            continue;
          }
          nextUsed = 1;
        }

        if (!visited[nx][ny][nextUsed]) {
          visited[nx][ny][nextUsed] = true;
          queue.offer(new int[] {nx, ny, dist + 1, nextUsed});
        }
      }
    }

    return -1;
  }

  private boolean inBounds(int x, int y, int n, int m) {
    return x >= 1 && x <= n && y >= 1 && y <= m;
  }
}
