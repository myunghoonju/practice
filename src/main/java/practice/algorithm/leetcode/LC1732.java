package practice.algorithm.leetcode;

import java.util.Arrays;

public class LC1732 {

  public int largestAltitude(int[] gain) {
    int length = gain.length;
    int[] alt = new int[gain.length + 1];
    alt[0] = 0;
    for (int i = 0; i < length; i++) {
      int value = 0;
      int end = i;
      while (end >= 0) {
        value += gain[end];
        end--;
      }

      alt[i + 1] = value;
    }

    return Arrays.stream(alt).max().getAsInt();
  }
}
