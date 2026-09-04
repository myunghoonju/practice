package practice.algorithm.prog;

import java.util.PriorityQueue;

public class Q121688 {

  int answer = 0;

  public int solution(int[] ability, int number) {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (int j : ability) {
      queue.add(j);
    }

    for (int i = 0; i < number; i++) {
      Integer value1 = queue.poll();
      Integer value2 = queue.poll();
      int value3 = value1 + value2;
      queue.add(value3);
      queue.add(value3);
    }

    queue.forEach(e -> answer+=e);

    return answer;
  }
}
