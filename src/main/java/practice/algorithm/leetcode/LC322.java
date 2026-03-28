package practice.algorithm.leetcode;

import java.util.Arrays;

//fixme
public class LC322 {

  public int coinChange(int[] coins, int amount) {
    if (amount == 0) {
      return 0;
    }

    int[] memo = new int[amount + 1];
    Arrays.fill(memo, amount + 1);
    memo[0] = 0;
    for (int i = 0; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (coins[j] <= i) {
          memo[i] = Math.min(memo[i], memo[i - coins[j]] + 1);
        }
      }
    }

    return memo[amount];
  }
}
