package practice.algorithm.leetcode;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC1834 {

  public int[] getOrder(int[][] tasks) {
    int n = tasks.length;
    int[] ans = new int[n];
    int[][] indexed = new int[n][3];

    for (int i = 0; i < n; i++) {
      indexed[i][0] = tasks[i][0]; // enqueueTime
      indexed[i][1] = tasks[i][1]; // processingTime
      indexed[i][2] = i;           // original index
    }

    Arrays.sort(indexed, comparingInt(t -> t[0]));
    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.<int[]>comparingInt(t -> t[1])
                                                               .thenComparingInt(t -> t[2]));

    int cnt = 0;
    int enqueue = 0;
    int process = 0;
    while (enqueue < n || !queue.isEmpty()) {
      while (enqueue < n && indexed[enqueue][0] <= process) {
        queue.add(indexed[enqueue++]);
      }

      if (queue.isEmpty()) {
        process = indexed[enqueue][0];
        continue;
      }

      int[] poll = queue.poll();
      process += poll[1];
      ans[cnt++] = poll[2];
    }

    return ans;
  }
}
