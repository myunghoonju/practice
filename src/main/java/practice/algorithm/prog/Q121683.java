package practice.algorithm.prog;

import java.util.*;

public class Q121683 {

  public String solution(String input_string) {
    Map<String, Integer> lastIdx = new HashMap<>();
    TreeSet<String> result = new TreeSet<>();

    String[] splitted = input_string.split("");
    for (int i = 0; i < splitted.length; i++) {
      String el = splitted[i];
      Integer prevIdx = lastIdx.getOrDefault(el, null);
      if (prevIdx != null && i - prevIdx >= 2) {
        lastIdx.put(el, i);
        result.add(el);
        continue;
      }

      lastIdx.put(el, i);
    }

    if (result.isEmpty()) {
      return "N";
    }

    StringBuffer sb = new StringBuffer();
    result.forEach(sb::append);

    return sb.toString();
  }

}
