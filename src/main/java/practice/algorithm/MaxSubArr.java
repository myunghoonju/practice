package practice.algorithm;

public class MaxSubArr {

  public int maxSubArray(int[] nums) {
    int length = nums.length;
    int max = nums[0];
    int current = 0;
    for (int i = 0; i < length; i++) {
      current = Math.max(0, current) + nums[i];
      max = Math.max(max, current);
    }

    return max;
  }
}
