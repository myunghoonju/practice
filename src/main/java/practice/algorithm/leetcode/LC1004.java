package practice.algorithm.leetcode;

public class LC1004 {

  public int longestOnes(int[] nums, int k) {
    int left = 0;
    int current = 0;
    int ans = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        current++;
      }

      while (current > k) {
        if (nums[left] == 0) {
          current--;
        }

        left++;
      }

      ans = Math.max(ans, i - left + 1);
    }
    return ans;
  }
}
