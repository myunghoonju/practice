package practice.algorithm.nossi;

public class ClimbingStairs {

  // bottom - up
  public int climbStairs(int n) {
    if (n <= 2) { // 1 + 1 or 2
      return n;
    }

    int[] array = new int[n + 1];
    array[1] = 1;
    array[2] = 2;
    for (int i = 3; i <= n; i++) {
      array[i] = array[i - 1] + array[i - 2];
    }

    return array[n];
  }
}
