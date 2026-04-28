package practice.algorithm.prog;

import java.util.LinkedList;
import java.util.Queue;

public class Q1844 {

  private static final int[] dx = {0, 1, 0, -1};
  private static final int[] dy = {-1, 0, 1, 0};

  public int solution(int[][] maps) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visit = new boolean[maps.length][maps[0].length];
    visit[0][0] = true;
    queue.offer(new int[]{0, 0, 1});

    while (!queue.isEmpty()) {
      int[] data = queue.poll();
      int x = data[0];
      int y = data[1];
      int z = data[2];
      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 0 ||
            ny < 0 ||
            nx >= maps.length ||
            ny >= maps[0].length ||
            visit[nx][ny]) {
          continue;
        }

        if (maps[nx][ny] == 1) {
          visit[nx][ny] = true;
          queue.offer(new int[]{nx, ny, z + 1});

          if (nx == maps.length-1 && ny == maps[0].length-1) {
            return z + 1;
          }
        }
      }
    }

    return -1;
  }
}
