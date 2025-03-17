package practice.algorithm.nossi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/network-delay-time/description/
// https://jaime-note.tistory.com/336
public class NetworkDelayTime {

  public static void main(String[] args) {
    int[][] times = {
            {2, 1, 1}, // 2 to 1 cost 1
            {2, 3, 1}, // 2 to 3 cost 1
            {3, 4, 1}  // 3 to 4 cost 1
    };

    System.out.println(sol(times, 4, 2));
  }

  static int sol(int[][] times, int n, int k) {
    Map<Integer, List<int[]>> map = new HashMap<>();
    for (int[] time : times) {
      map.putIfAbsent(time[0], new ArrayList<>());
      map.get(time[0]).add(new int[]{time[1], time[2]});
    }
    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(j -> j[1]));
    queue.offer(new int[]{k, 0});
    boolean[] visited = new boolean[n + 1];
    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      if (visited[current[0]]) {
        continue;
      }

      visited[current[0]] = true;
      if (--n == 0) {
        return current[1];
      }

      if (map.containsKey(current[0])) {
        for (int[] next : map.get(current[0])) {
          queue.offer(new int[]{next[0], current[1] + next[1]});
        }
      }
    }

    return -1;
  }
}
