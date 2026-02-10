package practice.algorithm.leetcode;

public class LC724 {

  public int pivotIndex(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      if (i == 0) {
        if (i == rightSum(i, nums)) {
          return i;
        }
      }

      if (leftSum(i, nums) == rightSum(i, nums)) {
        return i;
      }
    }

    return -1;
  }

  private int leftSum(int idx, int[] nums) {
    int sum = 0;
    for (int i = idx - 1; i >= 0; i--) {
      sum+=nums[i];
    }

    return sum;
  }

  private int rightSum(int idx, int[] nums) {
    int sum = 0;
    for (int i = idx + 1; i < nums.length; i++) {
      sum+=nums[i];
    }

    return sum;
  }
}
