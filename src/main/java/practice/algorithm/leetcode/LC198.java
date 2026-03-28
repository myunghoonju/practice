package practice.algorithm.leetcode;

public class LC198 {
  //tabulation
  public int rob(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }

    int[] memo = new int[nums.length];
    memo[0] = nums[0];
    memo[1] = Math.max(nums[0], nums[1]);

    //bottom-up: iteration
    for (int idx = 2; idx < nums.length; idx++) {
      memo[idx] = Math.max(memo[idx - 1], nums[idx] + memo[idx - 2]);
    }

    return memo[memo.length - 1];
  }
}
