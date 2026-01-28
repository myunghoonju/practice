package practice.algorithm.leetcode;

public class LC643 {

  static void main() {
    int[] nums = new int[]{1,12,-5,-6,50,3};
    System.out.println(findMaxAverage(nums, 4));
  }

  public static double findMaxAverage(int[] nums, int k) {
    double sum = 0.0;
    // init sum
    for (int i = 0; i < k; i++) {
      sum += nums[i];
    }

    double max = sum;
    for (int i = k; i < nums.length; i++) {
      // 이전 sum + 들어온 값 - 나가는 값
      sum += nums[i] - nums[i - k];
      max = Math.max(max, sum);
    }

    return max / k;
  }
}
