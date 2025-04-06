package practice.algorithm.nossi.ch02;

import java.util.Arrays;
import java.util.Comparator;

public class TwoSumTwoPointer {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
  }

  public static int[] twoSum(int[] nums, int target) {
    int[][] arr = new int[nums.length][2];
    for (int i = 0; i < nums.length; i++) {
      arr[i][0] = nums[i];
      arr[i][1] = i;
    }

    Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));

    int l = 0;
    int r = nums.length - 1;
    while (l < r) {
      if (arr[l][0] + arr[r][0] > target) {
        r -=1;
      } else if (arr[l][0] + arr[r][0] < target) {
        l +=1;
      } else {
        return new int[]{arr[l][1], arr[r][1]};
      }
    }

    return new int[]{-1, -1};
  }
}
