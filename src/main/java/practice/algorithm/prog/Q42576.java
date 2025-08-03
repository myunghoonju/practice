package practice.algorithm.prog;

import java.util.HashMap;
import java.util.Map;

public class Q42576 {

  public String solution(String[] participant, String[] completion) {
    Map<String, Integer> ma = new HashMap<>();
    for (String s : participant) {
      ma.put(s, ma.getOrDefault(s, 0) + 1);
    }

    for (String s : completion) {
      int cnt = ma.get(s) - 1;
      ma.put(s, cnt);
      if (cnt == 0) {
        ma.remove(s);
      }
    }

    return ma.keySet().iterator().next();
  }
}
