package practice.algorithm.leetcode;

public class LC334 {

  public boolean increasingTriplet(int[] nums) {
    int first = Integer.MAX_VALUE;
    int second = Integer.MAX_VALUE;
    for (int num : nums) {
      if (num <= first) {
        first = num;
        continue;
      }

      if (num <= second) {
        second = num;
        continue;
      }

      return true;
    }

    return false;
  }
}
