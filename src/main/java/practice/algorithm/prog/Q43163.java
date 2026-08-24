package practice.algorithm.prog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Q43163 {

  public int solution(String begin,
                      String target,
                      String[] words) {
    List<String> list = new ArrayList<>(Arrays.asList(words));
    if (!list.contains(target)) {
      return 0;
    }

    Map<String, Integer> depth = new HashMap<>();
    Queue<String> queue = new LinkedList<>();
    queue.offer(begin);
    depth.put(begin, 0);

    while (!queue.isEmpty()) {
      String cur = queue.poll();
      if (cur.equals(target)) {
        return depth.get(cur);
      }

      for (String word : words) {
        if (depth.containsKey(word)) {
          continue;
        }

        if (onlyLetterDifferent(cur, word)) {
          depth.put(word, depth.get(cur) + 1);
          queue.offer(word);
        }
      }
    }

    return 0;
  }

  private boolean onlyLetterDifferent(String a, String b) {
    int count = 0;
    String[] split = a.split("");
    String[] split1 = b.split("");
    for (int i = 0; i < split.length; i++) {
      if (split[i].equals(split1[i])) {
        continue;
      }

      count++;
    }

    return count == 1;
  }

}
