package practice.algorithm.prog;

import java.util.Arrays;

public class Q42898 {

  private final int[][] mem = new int[101][101];

  public int solution(int m, int n, int[][] puddles) {
    for (int[] ints : mem) {
      Arrays.fill(ints, -1);
    }

    boolean[][] puddle = new boolean[n + 1][m + 1];
    for (int[] ints : puddles) {
      puddle[ints[1]][ints[0]] = true;
    }

    return count(1, 1, m, n, puddle);
  }

  private int count(int x, int y,
                    int w, int h,
                    boolean[][] puddle) {
    if (x > w || y > h) {
      return 0;
    }

    if (puddle[y][x]) {
      return 0;
    }

    if (mem[x][y] != -1) {
      return mem[x][y];
    }

    if (x == w && y == h) {
      return 1;
    }

    int total = count(x + 1, y, w, h, puddle) + count(x, y + 1, w, h, puddle);

    return mem[x][y] = total % 1000000007;
  }
}
