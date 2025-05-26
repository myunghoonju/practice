package practice.algorithm;

public class ClimbStairs {

  // dynamic programming
  public int climbStairs(int n) {
    if (n == 1) {
      return 1;
    }

    int[] dp = new int[n + 1];

    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[n];
  }

  public int fibonacci(int n) {
    if (n == 1) {
      return 0;
    }

    int first = 0;
    int second = 0;
    for (int i = 3; i <= n; i++) {
      int third = first + second;
      first = second;
      second = third;
    }

    return second;
  }

}
