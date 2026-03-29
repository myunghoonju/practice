package practice.algorithm.leetcode;

import java.util.Arrays;

public class LC322 {

  public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 0; i <= amount; i++) {
        for (int coin : coins) {
            if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }

    if (dp[dp.length - 1] == Integer.MAX_VALUE) {
      return -1;
    }

    return dp[dp.length - 1];
  }
}
