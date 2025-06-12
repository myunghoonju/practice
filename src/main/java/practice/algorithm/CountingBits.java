package practice.algorithm;

import java.util.Arrays;

public class CountingBits {

  public int[] countBits(int n) {
    int[] res = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      String[] split = Integer.toBinaryString(i).split("");
      res[i] = Arrays.stream(split)
                     .filter("1"::equals)
                     .mapToInt(Integer::parseInt)
                     .sum();
    }
    return res;
  }

  public static void main(String[] args) {
    CountingBits countingBits = new CountingBits();
    Arrays.stream(countingBits.countBits(2)).forEach(System.out::println);
  }
}
