package practice.algorithm.prog;

public class Q121689 {

  public int solution(int[] menu,
                      int[] order,
                      int k) {
    int n = order.length;
    int[] complete = new int[n];// i번째 주문이 완료되는 시간
    int front = 0; // 아직 완료 안된 오랜된 주문
    int preComplete = 0; // 직전 주문 완료 시각
    int answer = 0;
    for (int i = 0; i < n; i++) {
      int startTime = i * k;
      int cookTime = menu[order[i]];
      int c = Math.max(startTime, preComplete) + cookTime;
      complete[i] = c;
      preComplete = c;

      while (front <= i && complete[front] <= startTime) {
        front++;
      }

      int waiting = i - front + 1;
      answer = Math.max(answer, waiting);
    }

    return answer;
  }
}
