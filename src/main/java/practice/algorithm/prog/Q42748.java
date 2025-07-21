package practice.algorithm.prog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q42748 {

  private static final int BEGIN = 0;
  private static final int END = 1;
  private static final int POINTER = 2;

  // i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째 있는 수
  public int[] solution(int[] array, int[][] commands) {
    int[] ans = new int[commands.length];

    for (int i = 0; i < ans.length; i++) {
      int[] command = commands[i];
      int begin = command[BEGIN] - 1;
      int end = command[END];
      int pointer = command[POINTER] - 1;

//      int[] ints = Arrays.copyOfRange(array, begin, end);
      int[] ints = extract(array, begin, end);
      Arrays.sort(ints);
      ans[i] = ints[pointer];
    }



    return ans;
  }

  private int[] extract(int[] array, int begin, int end) {
    List<Integer> list = new ArrayList<>();
    for (int i = begin; i < end; i++) {
      list.add(array[i]);
    }

    return list.stream().mapToInt(Integer::intValue).toArray();
  }
}
