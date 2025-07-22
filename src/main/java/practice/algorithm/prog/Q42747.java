package practice.algorithm.prog;

import java.util.Arrays;

public class Q42747 {

  public int solution(int[] citations) {
    Arrays.sort(citations);
    for (int i = citations.length; i >= 1; i--) {
      int idx = citations.length - i;
      if (citations[idx] >= i) {
        return i;
      }
    }

    return 0;
  }
}
