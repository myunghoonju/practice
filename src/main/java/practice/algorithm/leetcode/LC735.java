package practice.algorithm.leetcode;

import java.util.Stack;

public class LC735 {

  public int[] asteroidCollision(int[] asteroids) {
    Stack<Integer> stack = new Stack<>();
    for (int asteroid : asteroids) {
      boolean flag = true;
      while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
        if (Math.abs(stack.peek()) < Math.abs(asteroid)) {
          stack.pop();
          continue;
        }

        if (stack.peek() + asteroid == 0) {
          stack.pop();
        }

        flag = false;
        break;
      }

      if (flag) {
        stack.push(asteroid);
      }
    }


    return stack.stream().mapToInt(Integer::intValue).toArray();
  }
}
