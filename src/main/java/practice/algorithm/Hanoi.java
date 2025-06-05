package practice.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hanoi {

  public int[][] solution(int n) {
    List<int[]> data = new ArrayList<>();
    sol(data, n, 1, 3);
    return data.toArray(new int[0][]);
  }

  private void sol(List<int[]> data,
                    int n,
                    int from,
                    int to) {
    if (n == 1) {
      data.add(new int[]{from, to});
      return;
    }

    int empty = 6 - from - to;

    sol(data, n - 1, from, empty);
    sol(data, 1, from, to);
    sol(data, n - 1, empty, to);
  }

  public static void main(String[] args) {
    Hanoi hanoi = new Hanoi();
    System.out.println(Arrays.deepToString(hanoi.solution(3)));
  }
}
