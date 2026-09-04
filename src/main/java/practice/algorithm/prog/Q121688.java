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
      // queue에 저장
      queue.add(value3);
      queue.add(value3);
    }

    queue.forEach(e -> {
      answer+=e;
    } );

    // 반복 종료 후 모든 값 더하여 반환
    return answer;
  }
}
