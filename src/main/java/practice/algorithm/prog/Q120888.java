package practice.algorithm.prog;

import java.util.HashSet;
import java.util.Set;

public class Q120888 {

  public String solution(String my_string) {
    Set<Character> set = new HashSet<>();

    StringBuffer sb = new StringBuffer();
    for (char c : my_string.toCharArray()) {
      if (set.contains(c)) {
        continue;
      }

      set.add(c);
      sb.append(c);
    }

    return sb.toString();
  }
}
