package practice.algorithm.nossi;

public class MinCostClimbingStairs {

  public static void main(String[] args) {
    sol(new int[]{1,100,1,1,1,100,1,1,100,1});
    System.out.println(sol2(new int[]{1,100,1,1,1,100,1,1,100,1}));
  }

  static int sol(int[] cost) {
    int size = cost.length;
    int[] memo = new int[size];
    memo[0] = cost[0];
    memo[1] = cost[1];

    for(int i = 2; i < memo.length; i++) {
      memo[i] = Math.min(memo[i - 1], memo[i - 2]) + cost[i];
    }

    return Math.min(memo[size - 2], memo[size - 1]);
  }

  private static Integer[] f;
  private static int[] cost;

  public static int sol2(int[] cost) {
    MinCostClimbingStairs.cost = cost;
    f = new Integer[cost.length];
    return Math.min(dfs(0), dfs(1));
  }

  private static int dfs(int i) {
    if (i >= cost.length) {
      return 0;
    }
    if (f[i] == null) {
      f[i] = cost[i] + Math.min(dfs(i + 1), dfs(i + 2));
    }
    return f[i];
  }
}
