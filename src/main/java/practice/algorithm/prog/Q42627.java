package practice.algorithm.prog;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q42627 {

  public int solution(int[][] jobs) {
    // current time
    int time = 0;
    // index of jobs
    int idx = 0;
    // total sum of waiting time
    int total = 0;

    // asc sort based on request time
    Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));
    // sorted queue based on shorter work time
    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

    while (idx < jobs.length || !queue.isEmpty()) {
      while (idx <jobs.length && jobs[idx][0] <= time) {
        queue.add(jobs[idx++]);
      }

      // if queue's empty then jump to next job's request time
      if (queue.isEmpty()) {
        time = jobs[idx][0];
        continue;
      }

      //get shortest work time job
      int[] job = queue.poll();
      // add
      time += job[1];
      // accumulate waiting time(work time - requested time)
      total += time - job[0];
    }

    return total / jobs.length;
  }
}
