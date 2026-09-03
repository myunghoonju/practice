package practice.algorithm.prog;

import java.util.*;

public class Q121686 {

  public long[] solution(int[][] program) {
    long[] answer = new long[11];
    int length = program.length;

    // 호출시간(program[i][1]) 기준 정렬
    int[][] sorted = program.clone();
    Arrays.sort(sorted, (a, b) -> a[1] - b[1]);

    Queue<int[]>[] buckets = new Queue[11];
    for (int i = 0; i <= 10; i++) {
      buckets[i] = new LinkedList<>();
    }

    int now = 0;
    int idx = 0;
    int processed = 0;

    while (processed < length) {
      // now까지 도착한 프로세스를 우선순위 큐에 삽입
      while (idx < length && sorted[idx][1] <= now) {
        int priority = sorted[idx][0];
        buckets[priority].add(sorted[idx]);
        idx++;
      }

      // 실행 가능한 프로세스가 없으면 다음 도착시간으로 이동
      boolean empty = true;
      for (int p = 1; p <= 10; p++) {
        if (!buckets[p].isEmpty()) {
          empty = false;
          break;
        }
      }

      if (empty) {
        now = sorted[idx][1];
        continue;
      }

      //가장 높은 우선순위부터 확인해서 실행
      for (int p = 1; p <= 10; p++) {
        if (!buckets[p].isEmpty()) {
          int[] proc = buckets[p].poll();
          answer[p] += (now - proc[1]);
          now += proc[2];
          processed++;
          break;
        }
      }
    }

    answer[0] = now;
    return answer;
  }
}
