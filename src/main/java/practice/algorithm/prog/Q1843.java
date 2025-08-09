package practice.algorithm.prog;

import java.util.Arrays;

public class Q1843 {

  private final int[][] maxMem = new int[202][202];
  private final int[][] minMem = new int[202][202];

  private int max(int start, int end, String[] arr) {
    int max = Integer.MIN_VALUE;

    if (maxMem[start][end] != Integer.MIN_VALUE) {
      return maxMem[start][end];
    }

    if (end - start == 1) {
      return Integer.parseInt(arr[start]);
    }

    for (int i = start + 1; i < end; i += 2) {
      int l = max(start, i, arr);
      int v;
      if (arr[i].equals("+")) {
        int r = max(i + 1, end, arr);
        v = l + r;
      } else {
        int r = min(i + 1, end, arr);
        v = l - r;
      }

      if (v > max) {
        max = v;
      }
    }

    return maxMem[start][end] = max;
  }

  private int min(int start, int end, String[] arr) {
    int min = Integer.MAX_VALUE;

    if (minMem[start][end] != Integer.MIN_VALUE) {
      return minMem[start][end];
    }

    if (end - start == 1) {
      return Integer.parseInt(arr[start]);
    }

    for (int i = start + 1; i < end; i += 2) {
      int l = min(start, i, arr);
      int v;
      if (arr[i].equals("+")) {
        int r = min(i + 1, end, arr);
        v = l + r;
      } else  {
        int r = min(i + 1, end, arr);
        v = l - r;
      }

      if (v < min) {
        min = v;
      }
    }

    return min;
  }

  public int solution(String[] arr) {
    for (int[] ints : maxMem) {
      Arrays.fill(ints, Integer.MIN_VALUE);
    }

    for (int[] ints : minMem) {
      Arrays.fill(ints, Integer.MIN_VALUE);
    }

    return max(0, arr.length, arr);
  }
}
