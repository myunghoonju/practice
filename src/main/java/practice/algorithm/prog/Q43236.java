package practice.algorithm.prog;

import java.util.Arrays;

public class Q43236 {

  public int solution(int distance, int[] rocks, int n) {
    rocks = Arrays.copyOf(rocks, rocks.length + 1);
    rocks[rocks.length - 1] = distance;
    Arrays.sort(rocks);
    int start = 1;
    int end = distance + 1;
    while (end - start > 1) {
      int d = (end + start) / 2;
      if (valid(d, rocks, n)) {
        start = d;
      } else {
        end = d;
      }
    }

    return start;
  }

  private boolean valid(int d, int[] rocks, int n) {
    int remove = 0;
    int last = 0;
    for (int rock : rocks) {
      if (rock - last < d) {
        remove++;
        continue;
      }

      last = rock;
    }

    return remove <= n;
  }
}
