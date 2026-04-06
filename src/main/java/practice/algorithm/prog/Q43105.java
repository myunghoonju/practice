package practice.algorithm.prog;

import java.util.Arrays;

public class Q43105 {

  public int solution(int[][] triangle) {
    int[][] visit = new int[triangle.length][triangle.length];
    for (int[] ints : visit) {
      Arrays.fill(ints, -1);
    }
    return recursive(triangle, 0, 0, visit);
  }

  private int recursive(int[][] triangle,
                         int direction,
                         int level,
                         int[][] visit) {
    if (level == triangle.length) {
      return 0;
    }

    if (visit[level][direction] >= 0) {
      return visit[level][direction];
    }

    visit[level][direction] =  triangle[level][direction] + Math.max(recursive(triangle, direction, level + 1, visit), recursive(triangle, direction + 1, level + 1, visit));

    return visit[level][direction];
  }

}
