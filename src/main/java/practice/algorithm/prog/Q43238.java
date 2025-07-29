package practice.algorithm.prog;

public class Q43238 {

  public long solution(int n, int[] times) {
    long start = 1;
    long end = 1000000000000000000L; // 대기자 x 심사시간
    while (end > start) {
      long t = (start + end) / 2; // 이진탐색
      if (valid(t, n, times)) {
        end = t;
      } else {
        start = t + 1;
      }
    }

    return start;
  }

  private boolean valid(long t, int n, int[] times) {
    long c = 0;
    for (int time : times) {
      c += t / time;
    }

    return c >= n;
  }

  public static void main(String[] args) {
    Q43238 q = new Q43238();
    int p = 6;
    int[] times = {7, 10};
    System.out.println(q.solution(p, times));
  }
}
