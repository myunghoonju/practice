package practice.algorithm.prog;

public class Q121684 {

  private int maxSum = 0;

  public int solution(int[][] ability) {
    boolean[] usedCol = new boolean[ability.length];
    fun(0, 0, usedCol, ability);
    return maxSum;
  }

  private void fun(int col, int currentSum, boolean[] usedCol, int[][] ability) {
    if (col == ability[0].length) {
      maxSum = Math.max(currentSum, maxSum);
      return;
    }

    for (int row = 0; row < ability.length; row++) {
      if (usedCol[row]) {
        continue;
      }

      usedCol[row] = true;
      fun(col+1, currentSum + ability[row][col], usedCol, ability);
      usedCol[row] = false;
    }
  }
}
