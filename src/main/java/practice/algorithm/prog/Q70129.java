package practice.algorithm.prog;

public class Q70129 {

  private static final String TARGET = "1";
  private static final char ZERO = '0';

  public int[] solution(String s) {
    int loop = 0;
    int removed = 0;

    while (!TARGET.equals(s)) {
      int zero = cntZero(s);
      loop++;
      removed += zero;

      int one = s.length() - zero;
      s = Integer.toString(one, 2);
    }

    return new int[] {loop, removed};
  }

  private int cntZero(String s) {
    int cnt = 0;
    for (char c : s.toCharArray()) {
      if (ZERO == c) {
        cnt++;
      }
    }

    return cnt;
  }

  public static void main(String[] args) {
    Q70129 solution = new Q70129();
    for (int i : solution.solution("110010101001")) {
      System.out.println(i);
    }
  }
}
