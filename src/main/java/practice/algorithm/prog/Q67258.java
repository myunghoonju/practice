package practice.algorithm.prog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q67258 {

  public int[] solution(String[] gems) {
    int start = 0;
    int end = gems.length -1;

    Set<String> gemSet = new HashSet<>(List.of(gems));

    int s = 0;
    int e = s;
    Map<String, Integer> include = new HashMap<>();
    include.put(gems[s], 1);
    while (s < gems.length) {
      if (include.keySet().size() == gemSet.size()) {
        if (e - s < end - start) {
          start = s;
          end = e;
        }

        include.put(gems[s], include.get(gems[s]) - 1);
        if (include.get(gems[s]) == 0) {
          include.remove(gems[s]);
        }

        s++;
      } else if (e < gems.length - 1) {
        e++;
        include.put(gems[e], include.getOrDefault(gems[e], 0) + 1);
      } else {
        break;
      }
    }

    return new int[]{start + 1, end + 1};
  }
}
