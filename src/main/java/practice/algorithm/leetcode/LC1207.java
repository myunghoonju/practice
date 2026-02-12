package practice.algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC1207 {

  Map<Integer, Integer> map = new HashMap<>();

  public boolean uniqueOccurrences(int[] arr) {
    Map<Integer, Integer> map = new HashMap<>();
      for (int i : arr) {
        map.put(i , map.getOrDefault(i, 0) + 1);
      }

      Set<Integer> freq = new HashSet<>(map.values());

      return map.size() == freq.size();
  }
}
