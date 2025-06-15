package practice.algorithm;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {

  // use hashmap, and if same key exists then increase its value
  public int sol(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    for (int num : nums) {
      if (map.get(num) == 1) {
        return num;
      }
    }

   return 0;
  }

  public static void main(String[] args) {
    SingleNumber singleNumber = new SingleNumber();
    System.out.println(singleNumber.sol(new int[]{1}));
  }
}
