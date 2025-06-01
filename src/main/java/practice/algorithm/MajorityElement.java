package practice.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

  public int majorityElement(int[] nums) {
    Map<Integer, Integer> finder = new HashMap<>();
    for (int num : nums) {
      if (finder.getOrDefault(num, 0) == 0) {
        finder.put(num, 1);
        break;
      }

      finder.putIfAbsent(num, finder.get(num) + 1);
    }

    for (Map.Entry<Integer, Integer> entry : finder.entrySet()) {
      if (entry.getValue() > nums.length / 2) {
        return entry.getKey();
      }
    }

    return 0;
  }

  public int bruteForce(int[] nums) {
    int major = nums.length / 2;
    for (int num : nums) {
      int cnt = 0;
      for (int el : nums) {
        if (el == num) {
          cnt++;
        }
      }
      if (cnt > major) {
        return num;
      }
    }

    return -1;
  }

  public int sort(int[] nums) {
    Arrays.sort(nums);
    return nums[nums.length / 2];
  }

  public static void main(String[] args) {
    char[] charArray = "aa".toCharArray();
    StringBuffer sb = new StringBuffer();

    System.out.println(Arrays.toString(charArray));
  }
}
