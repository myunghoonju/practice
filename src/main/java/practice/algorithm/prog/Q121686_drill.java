package practice.algorithm.prog;

import java.util.*;

/**
 * [구현 전용 드릴] PCCP 모의고사 2회 - 운영체제 (121686)
 * https://school.programmers.co.kr/learn/courses/15009/lessons/121686
 *
 * 입력: program[i] = {우선순위(1~10), 호출시각, 실행시간}
 * 반환: long[11] — [0] = 모든 프로그램 종료 시각, [p] = 우선순위 p 프로그램들의 대기시간 합
 *
 * 확정된 설계 (이미 맞다고 검증됨 — 알고리즘 고민 X, 코드로 옮기기만):
 *
 * 1. program을 호출시각 기준으로 정렬한다.
 * 2. 우선순위 1~10마다 대기 큐(버킷) 하나씩 만든다. (인덱스 0은 안 씀)
 * 3. 상태: now(현재 시각), idx(정렬 배열에서 다음에 도착할 프로그램 위치), processed(실행 끝난 개수)
 * 4. processed < 전체 개수 동안 반복:
 *    a. 호출시각 <= now 인 프로그램을 idx부터 꺼내 자기 우선순위 버킷에 넣는다.
 *    b. 모든 버킷이 비었으면 → now를 다음 도착 프로그램의 호출시각으로 점프하고 a로 돌아간다.
 *    c. 우선순위 1부터 보며 처음 비어 있지 않은 버킷에서 하나 꺼낸다:
 *       대기시간 += now - 호출시각,  now += 실행시간,  processed++
 * 5. answer[0] = now
 *
 * 테스트:
 *  {{2,0,10},{1,5,5},{3,5,3},{3,12,2}} → [20, 5, 0, 16, 0, 0, 0, 0, 0, 0, 0]
 *  {{3,6,4},{4,2,5},{1,0,5},{5,0,5}}   → [19, 0, 0, 4, 3, 14, 0, 0, 0, 0, 0]
 */
public class Q121686_drill {

  public long[] solution(int[][] program) {
    long[] answer = new long[11];
    Arrays.sort(program, (a, b) -> a[1] - b[1]);
    List<ArrayDeque<int[]>> buckets = new ArrayList<>();
    for (int i = 0; i <= 10; i++) {
      buckets.add(new ArrayDeque<>());
    }

    long now = 0;
    int idx = 0;
    int processed = 0;

    int n = program.length;
    while (processed < n) {
      //4.a
      while (idx < n && program[idx][1] <= now) {
        buckets.get(program[idx][0]).add(program[idx]);
        idx++;
      }
      //4.b
      boolean allEmpty = true;
      for (int i = 1; i <= 10; i++) {
        if (!buckets.get(i).isEmpty()) {
          allEmpty = false;
          break;
        }
      }

      if (allEmpty) {
        now = program[idx][1];
        continue;
      }

      //4.c
      for (int i = 1; i <= 10; i++) {
        if (!buckets.get(i).isEmpty()) {
          int[] poll = buckets.get(i).poll();
          answer[i] += now - poll[1];
          now += poll[2];
          processed++;
          break;
        }
      }
    }

    answer[0] = now;

    return answer;
  }
}
