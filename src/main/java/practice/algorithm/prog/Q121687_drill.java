package practice.algorithm.prog;

/**
 * [구현 전용 드릴] PCCP 모의고사 1회 - 실습용 로봇 (121687)
 * https://school.programmers.co.kr/learn/courses/15008/lessons/121687
 *
 * 확정된 설계 (이미 맞다고 검증됨 — 알고리즘 고민 X, 코드로 옮기기만):
 *
 * 1. 방향 4개를 시계방향 순서로 배열에 둔다: 위(0,+1), 오른쪽(+1,0), 아래(0,-1), 왼쪽(-1,0)
 * 2. 상태: x=0, y=0, dir=0(위)
 * 3. command의 문자 하나씩:
 *    - 'R' → dir = (dir + 1) % 4
 *    - 'L' → dir = (dir + 3) % 4
 *    - 'G' → 현재 방향으로 한 칸 전진 (x += dx[dir], y += dy[dir])
 *    - 'B' → 현재 방향 반대로 한 칸 후진 (x -= dx[dir], y -= dy[dir])
 * 4. return {x, y}
 *
 * 테스트: "GRGLGRG" → [2, 2],  "GRGRGRB" → [2, 0]
 */
public class Q121687_drill {

  public int[] solution(String command) {
    // 여기에 구현
    return null;
  }
}
