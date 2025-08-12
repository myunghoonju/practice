package practice.algorithm.prog;

import java.util.Arrays;
import java.util.Stack;

public class Q42584 {

  public int[] solution(int[] prices) {
    int[] answer = new int[prices.length];

    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < prices.length; i++) {
      while (!stack.isEmpty() &&
             prices[stack.peek()] > prices[i]) {
        int top = stack.pop();
        answer[top] = i - top;
      }

      stack.push(i);
    }

    while (!stack.isEmpty()) {
      int top = stack.pop();
      answer[top] = prices.length - top -1;
    }

    return answer;
  }

  public static void main(String[] args) {
    Q42584 q = new Q42584();
    System.out.println(Arrays.toString(q.solution(new int[]{1,2,3,2,3})));
  }
}
