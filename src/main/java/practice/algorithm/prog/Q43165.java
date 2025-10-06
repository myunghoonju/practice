package practice.algorithm.prog;

import java.util.Stack;

public class Q43165 {

  private static class State {

    private final int idx;
    private final int acc;

    public State(int idx, int acc) {
      this.idx = idx;
      this.acc = acc;
    }
  }

  public int solution(int[] numbers, int target) {
    Stack<State> stack = new Stack<>();
    stack.push(new State(0, 0));

    int cnt = 0;
    while (!stack.isEmpty()) {
      State state = stack.pop();
      if (state.idx == numbers.length) {
        if (state.acc == target) {
          cnt++;
        }
        continue;
      }

      stack.push(new State(state.idx + 1, state.acc + numbers[state.idx]));
      stack.push(new State(state.idx + 1, state.acc - numbers[state.idx]));
    }

    return cnt;
  }

  static int solution2(int[] numbers, int target) {
    return dfs(numbers, target, 0, 0);
  }

  static int dfs(int[] numbers, int target, int idx, int sum) {
    if (numbers.length == idx) {
      return sum == target ? 1 : 0;
    }

    return dfs(numbers, target, idx + 1, sum + numbers[idx]) +
           dfs(numbers, target, idx + 1, sum - numbers[idx]);
  }

  public static void main(String[] args) {
    System.out.println(new Q43165().solution(new int[]{1, 1, 1, 1, 1}, 5));
  }
}
