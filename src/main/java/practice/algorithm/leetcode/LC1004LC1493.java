package practice.algorithm.leetcode;

public class LC1004LC1493 {

  public int lc1004(int[] nums, int k) {
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

  public int lc1493(int[] nums) {
    int left = 0;
    int current = 0;
    int ans = 0;
    for (int right = 0; right < nums.length; right++) {
      if (nums[right] == 0) {
        current++;
      }

      while (current > 1) {
        if (nums[left] == 0) {
          current--;
        }

        left++;
      }

      ans = Math.max(ans, right - left);
    }

    return ans;
  }
}
