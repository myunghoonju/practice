package practice.algorithm.prog;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Q42862 {

  public int solution(int n, int[] lost, int[] reserve) {
      Arrays.sort(lost);
      Arrays.sort(reserve);

      Set<Integer> owns = Arrays.stream(lost).boxed().collect(Collectors.toSet());
      owns.retainAll(Arrays.stream(reserve).boxed().collect(Collectors.toSet()));

      Queue<Integer> needy = new LinkedList<>();
      for (int i : lost) {
        needy.add(i);
      }

      int get = 0;
      for (int r : reserve) {
        if (owns.contains(r)) {
          continue;
        }

        while (!needy.isEmpty() &&
                needy.peek() < r - 1 ||
                owns.contains(needy.peek())) {
          needy.poll();
        }

        if (needy.isEmpty()) {
          break;
        }

        if (needy.peek() <= r + 1) {
          needy.poll();
          get++;
        }

      }

      // 전체학생 - 분실자 + 보유자 + 빌린자
      return n - lost.length + owns.size() + get;
  }
}
