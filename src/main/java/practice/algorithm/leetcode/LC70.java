package practice.algorithm.leetcode;

public class LC70 {

    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return memoization(0, n, memo);
    }

    //top-down dp
    private int memoization(int step, int n, int[] memo) {
        if (step == n) {
            return 1;
        }

        if (step > n) {
            return 0;
        }

        if (memo[step] != 0) {
            return memo[step];
        }

        memo[step] = memoization(step + 1, n, memo) + memoization(step + 2, n, memo);

        return memo[step];
    }

    //bottom-up dp
    private int bottomUp(int n) {
        if (n < 3) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
