package practice.algorithm.prog;

import java.util.PriorityQueue;
import java.util.Queue;

public class Q42626 {

  public int solution(int[] scoville, int K) {
    int answer = 0;
    // ordered queue(asc)
    Queue<Integer> queue = new PriorityQueue<>();
    for (int i = 0; i < scoville.length; i++) {
      queue.offer(scoville[i]);
    }

    while (queue.peek() < K) {
      int size = queue.size();
      if (size < 2) {
        return -1;
      }

      Integer first = queue.poll();
      Integer second = queue.poll();
      queue.offer(mixup(first, second));
      answer++;
    }

    return answer;
  }

  private int mixup(int min, int val) {
    return min + (val * 2);
  }
}
