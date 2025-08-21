package practice.algorithm.prog;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q42627 {

  private static class Job {

    public final int start;

    public final int duration;

    public Job(int start, int duration) {
      this.start = start;
      this.duration = duration;
    }
  }

  public int solution(int[][] jobs) {
    Job[] jobArr = new Job[jobs.length];
    for (int i = 0; i < jobs.length; i++) {
      jobArr[i] = new Job(jobs[i][0], jobs[i][1]);
    }

    Arrays.sort(jobArr, Comparator.comparing(job -> job.start));
    Queue<Job> q = new LinkedList<>(Arrays.asList(jobArr));
    PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparingInt(job -> job.duration));

    int exec = 0;
    int time = 0;

    while (!q.isEmpty() || !pq.isEmpty()) {
      while (!q.isEmpty() && q.peek().start <= time) {
        pq.offer(q.poll());
      }

      if (pq.isEmpty()) {
        time = q.peek().start;
        continue;
      }

      Job job = pq.poll();
      exec += time + job.duration - job.start;
      time += job.duration;

    }
    return exec / jobArr.length;
  }
}
