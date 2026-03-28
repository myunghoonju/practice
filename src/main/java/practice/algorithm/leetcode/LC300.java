package practice.algorithm.leetcode;

import java.util.Arrays;

public class LC300 {

  public int lengthOfLIS(int[] nums) {
    if (nums.length == 1) {
      return  1;
    }

    int[] memo = new int[nums.length];
    Arrays.fill(memo, 1);
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          memo[i] = Math.max(memo[i], memo[j] + 1);
        }
      }
    }

    int max = 0;
    for (int i : memo) {
      max = Math.max(max, i);
    }

    return max;
  }
}
