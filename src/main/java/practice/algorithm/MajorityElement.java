package practice.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    int majority = 0;
    for (Map.Entry<Integer, Integer> entry : finder.entrySet()) {
      if (entry.getValue() > majority) {
        majority = entry.getKey();
      }
    }

    return majority;
  }

  public static void main(String[] args) {
    
  }
}
