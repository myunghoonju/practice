package practice.algorithm.prog;

import java.util.Arrays;

public class Q43105 {

  private static int[][] mem = new int[501][501];

  public int solution(int[][] triangle) {
    for (int[] ints : mem) {
      Arrays.fill(ints, -1);
    }

    return max(0, 0, triangle);
  }

  private int max(int x, int y, int[][] triangle) {
    if (y == triangle.length) {
      return 0;
    }

    if (mem[x][y] != -1) {
      return mem[x][y];
    }

    return mem[x][y] = triangle[y][x] + Math.max(max(x, y + 1, triangle),
                                                 max(x + 1, y + 1, triangle));
  }
}
