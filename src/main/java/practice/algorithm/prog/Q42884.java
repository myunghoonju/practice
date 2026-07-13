package practice.algorithm.prog;

import java.util.Arrays;
import java.util.Comparator;

public class Q42884 {

  public int solution(int[][] routes) {
    int ans = 0;
    Arrays.sort(routes, Comparator.comparingInt(b -> b[1]));

    int last = Integer.MIN_VALUE;
    for (int[] route : routes) {
      if (last < route[0]) {
        last = route[1];
        ans++;
      }
    }

    return ans;
  }
}
