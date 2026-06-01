package practice.algorithm.prog;

import java.util.LinkedList;
import java.util.Queue;

public class Q42587 {

  public int solution(int[] priorities, int location) {
    Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < priorities.length; i++) {
      queue.add(new int[]{priorities[i], i});
    }

    int idx = 0;
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      boolean hasHigher = queue.stream().anyMatch(item -> item[0] > cur[0]);
      if (hasHigher) {
        queue.add(cur);
      } else {
        idx++;
        if (cur[1] == location) {
          return idx;
        }
      }
    }

    return idx;
  }

}
