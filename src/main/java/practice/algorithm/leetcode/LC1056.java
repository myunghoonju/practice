package practice.algorithm.leetcode;

public class LC1056 {

  private static final Integer[] FLIPS = {0, 1, null, null, null, null, 9, null, 8, 6};

  public boolean confusingNumber2(int n) {
    int nClone = n;
    int rotated = 0;

    while (n != 0) {
      Integer rotatedTail = FLIPS[n % 10];
      if (rotatedTail == null) {
        return false;
      }

      rotated = rotated * 10 + rotatedTail;
      n = n / 10;
    }

    return nClone != rotated;
  }

  static void main() {
    IO.println(new LC1056().confusingNumber2(69));
  }
}
