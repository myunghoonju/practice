package practice.algorithm.prog;

import java.util.HashMap;
import java.util.Map;

public class Q120886 {

  private Map<Character, Integer> map(String word) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : word.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    return map;
  }

  public int solution(String before, String after) {
    return map(before).equals(map(after)) ? 1 : 0;
  }

  public static void main(String[] args) {
    Q120886 q = new Q120886();
    System.out.println(q.solution("apple", "elppa"));
  }
}
