package practice.algorithm.leetcode;

public class LC238 {

  // space complexity O(N)
  public int[] productExceptSelf1(int[] nums) {
    int length = nums.length;
    int[] left = new int[length];
    int[] right = new int[length];
    int[] answer = new int[length];
    left[0] = 1;
    for (int i = 1; i < left.length; i++) {
      left[i] = nums[i - 1] * left[i - 1];
    }

    right[length - 1] = 1;
    for (int i = length - 2; i >= 0; i--) {
      right[i] = nums[i + 1] * right[i + 1];
    }

    for (int i = 0; i < length; i++) {
      answer[i] = left[i] * right[i];
    }
    return answer;
  }

  // space complexity O(1)
  public int[] productExceptSelf(int[] nums) {
    int length = nums.length;
    int[] answer = new int[length];
    answer[0] = 1;
    for (int i = 1; i < length; i++) {
      answer[i] = answer[i - 1] * nums[i - 1];
    }

    int right = 1;
    for (int i = length - 2; i >= 0; i--) {
      answer[i] = answer[i + 1] * right;
      right *= nums[i];
    }

    return answer;
  }
}
