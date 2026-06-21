package practice.algorithm.leetcode;

import java.util.Arrays;

public class Q215 {

  public int findKthLargest(int[] nums, int k) {
    Arrays.sort(nums);
    return nums[nums.length - k];
  }

  static void main() {
    int kthLargest = new Q215().findKthLargest(new int[] { 3,2,3,1,2,4,5,5,6 }, 4);
    System.out.println(kthLargest);
  }
}
