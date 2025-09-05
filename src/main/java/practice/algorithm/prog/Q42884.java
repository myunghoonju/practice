package practice.algorithm.prog;

import java.util.Arrays;
import java.util.Comparator;

public class Q42884 {

  public int solution(int[][] routes) {
    Arrays.sort(routes, Comparator.comparing(r -> r[1]));

    int cnt = 0;
    int camera = Integer.MIN_VALUE;
    for (int[] route : routes) {
      if (camera >= route[0] && camera <= route[1]) {
        continue;
      }

      camera = route[1];
      cnt++;
    }

    return cnt;
  }
}
