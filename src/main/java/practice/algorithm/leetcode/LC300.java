package practice.algorithm.leetcode;

import java.util.Arrays;

public class LC300 {

  public int lengthOfLIS(int[] nums) {
    int[] ints = new int[nums.length];
    Arrays.fill(ints, 1);
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          ints[i] = Math.max(ints[i], ints[j] + 1);
        }
      }
    }

    int ans = 0;
    for (int anInt : ints) {
      ans = Math.max(ans, anInt);
    }

    return ans;
  }
}
