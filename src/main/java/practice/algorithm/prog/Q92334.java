package practice.algorithm.prog;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Q92334 {

  public int[] solution(String[] id_list,
                        String[] report,
                        int k) {
    Map<String, Set<String>> reports = new HashMap<>();
    Map<String, Integer> reported = new HashMap<>();
    for (String id : id_list) {
      reports.put(id, new HashSet<>());
    }

    for (String el : report) {
      String[] v = el.split(" ");
      String plaintiff = v[0];
      String defendant = v[1];
      Set<String> set = reports.get(plaintiff);
      if (set.contains(defendant)) {
        continue;
      }

      set.add(defendant);
      reported.putIfAbsent(defendant, 0);
      reported.put(defendant, reported.get(defendant) + 1);
    }

    Set<String> list = reported.keySet()
                               .stream()
                               .filter(id -> reported.get(id) >= k)
                               .collect(Collectors.toSet());

      return Arrays.stream(id_list)
                   .mapToInt(id -> (int) reports.get(id)
                                                       .stream()
                                                       .filter(list::contains).count())
                                                       .toArray();
    }
}
