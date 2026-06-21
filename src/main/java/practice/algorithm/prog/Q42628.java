package practice.algorithm.prog;

import java.util.TreeMap;

public class Q42628 {

  public int[] solution(String[] operations) {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (String operation : operations) {
      if (digit(operation)) {
        map.merge(value(operation), 1, Integer::sum);
        continue;
      }

      if (map.isEmpty()) {
        continue;
      }

      if (head(operation)) {
        removeKey(map, map.lastKey());
        continue;
      }

      removeKey(map, map.firstKey());
    }

    if (map.isEmpty()) {
      return new int[]{0, 0};
    }

    return new int[]{map.lastKey(), map.firstKey()};
  }

  private void removeKey(TreeMap<Integer, Integer> map, int key) {
    if (map.get(key) == 1) {
      map.remove(key);
      return;
    }

    map.put(key, map.get(key) - 1);
  }

  private Integer value(String el) {
    return Integer.valueOf(el.split(" ")[1]);
  }

  private boolean digit(String op) {
    return "I".equals(op.split(" ")[0]);
  }

  private boolean head(String op) {
    return "1".equals(op.split(" ")[1]);
  }
}
