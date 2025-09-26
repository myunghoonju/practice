package practice.algorithm.prog;

import java.util.Arrays;

public class Q92342 {

  private int diff(int[] apeach, int[] ryan) {
    int diff = 0;
    for (int i = 0; i < apeach.length; i++) {
      if (apeach[i] == 0 && ryan[i] == 0) {
        continue;
      }

      if (apeach[i] >= ryan[i]) {
        diff -= 10 - i;
      } else {
        diff += 10 - i;
      }
    }

    return diff;
  }

  private boolean prior(int[] base, int[] compare) {
    for (int i = 10; i >= 0; i--) {
      if (compare[i] == base[i]) {
        continue;
      }

      return compare[i] > base[i];
    }

    return false;
  }

  private int[] ryan(int index,
                     int[] hits,
                     int n,
                     int[] apeach) {
    // end
    if (index == hits.length) {
      if (n > 0) {
        return null;
      }

      if (diff(apeach, hits) <= 0) {
        return null;
      }

      return Arrays.copyOf(hits, hits.length);
    }

    // cond
    int max = 0;
    int[] result = null;
    for (int hit = 0; hit <= n; hit++) {
      hits[index] = hit;
      int[] ryan = ryan(index + 1, hits, n, apeach);
      if (ryan == null) {
        continue;
      }

      int diff = diff(apeach, ryan);
      if (diff > max || (diff == max && prior(result, ryan))) {
        max = diff;
        result = ryan;
      }
    }

    return result;
  }

  public int[] solution(int n, int[] info) {
    int[] ryan = ryan(0, new int[11], n, info);
    if (ryan == null) {
      return new int[] { -1 };
    }

    return ryan;
  }
}
