package practice.algorithm.prog;

import java.util.Arrays;

public class Q12915 {

  public static String[] solution(String[] strings, int n) {
    Arrays.sort(strings, (a, b) -> {
      if (a.charAt(n) != b.charAt(n)) {
        return a.charAt(n) - b.charAt(n);
      }

      return a.compareTo(b);
    });

    return strings;
  }

}
