package practice.algorithm;

// https://school.programmers.co.kr/learn/courses/30/lessons/68936
public class QuardTree {

  public int[] solution(int[][] arr) {
    int[] answer = {};
    Count count = count(arr, 0, 0, arr.length);

    return new int[] {count.zero, count.one};
  }

  public static class Count {
    private int zero;
    private int one;

    public Count(int zero, int one) {
      this.zero = zero;
      this.one = one;
    }

    public Count add(Count cnt) {
      return new Count(this.zero + cnt.zero, this.one + cnt.one);
    }
  }

  private Count count(int[][] arr, int oZero, int oOne, int size) {
    int length = size / 2;
    for (int idx = oZero; idx < oZero + size; idx++) {
      for (int idx2 = oOne; idx2 < oOne + size; idx2++) {
        if (arr[idx2][idx] != arr[oOne][oZero]) {
          return count(arr, oZero, oOne, length).add(count(arr, oZero + length, oOne, length))
                                                .add(count(arr, oZero, oOne + length, length))
                                                .add(count(arr, oZero + length, oOne + length, length));
        }
      }
    }

    if (arr[oOne][oZero] == 1) {
      return new Count(0, 1);
    }

    return new Count(1,0);
  }
}
