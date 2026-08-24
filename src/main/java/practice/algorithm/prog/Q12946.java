package practice.algorithm.prog;

import java.util.ArrayList;
import java.util.List;

public class Q12946 {

  public int[][] solution(int n) {
    List<int[]> answer = new ArrayList<>();
    move(n, 1, 2, 3, answer);
    return answer.toArray(new int[answer.size()][]);
  }

  private void move(int n,
                    int from,
                    int via,
                    int to,
                    List<int[]> answer) {
    if (n == 0) {
      return;
    }

    move(n - 1, from, to, via, answer);
    answer.add(new int[] {from, to});
    move(n - 1, via, from, to, answer);
  }
}
