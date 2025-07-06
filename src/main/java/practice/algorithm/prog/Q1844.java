package practice.algorithm.prog;

import java.util.LinkedList;
import java.util.Queue;

public class Q1844 {

  private static final int[] DX = {0, 1, 0, -1};
  private static final int[] DY = {-1, 0, 1, 0};

  private static class State {
    private final int x;
    private final int y;
    private final int step;

    public State(int x, int y, int step) {
      this.x = x;
      this.y = y;
      this.step = step;
    }
  }

  public int solution(int[][] maps) {
    boolean[][] visited = new boolean[maps.length][maps[0].length];
    Queue<State> q = new LinkedList<>();
    q.add(new State(0, 0, 1));
    visited[0][0] = true;

    while (!q.isEmpty()) {
      State s = q.poll();
      if (s.y == maps.length - 1 && s.x == maps[s.y].length - 1) {
        return s.step;
      }

      for (int i = 0; i < 4; i++) {
        int nx = s.x + DX[i];
        int ny = s.y + DY[i];
        if (ny < 0 ||
            ny >= maps.length ||
            nx < 0 ||
            nx >= maps[0].length) {
          continue;
        }

        if (maps[ny][nx] != 1) {
          continue;
        }

        if (visited[ny][nx]) {
          continue;
        }

        visited[ny][nx] = true;
        q.add(new State(nx, ny, s.step + 1));
      }
    }
      return -1;
  }

  public static void main(String[] args) {
    int[][] map = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
    System.out.println(new Q1844().solution(map));
  }
}
