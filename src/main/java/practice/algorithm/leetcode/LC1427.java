package practice.algorithm.leetcode;

public class LC1427 {

  public String stringShift(String string, int[][] shift) {
    for (int[] ints : shift) {
      int dir = ints[0];
      int move = ints[1];
      int cnt =  move % string.length();

      if (dir == 0) {
        string = string.substring(cnt) + string.substring(0, cnt);
      } else {
        string = string.substring(string.length() - cnt) + string.substring(0, string.length() - cnt);
      }
    }

    return string;
  }

  static void main() {
    IO.println(new LC1427().stringShift("abc", (new int[][]{{0, 1}, {1, 2}})));
  }
}
