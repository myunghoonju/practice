package practice.algorithm.nossi.ch02.ex;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Temperature {

  public static void main(String[] args) {
    int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
    Arrays.stream(sol(temperatures)).forEach(System.out::println);
  }

  static int[] sol(int[] temperatures) {
    Deque<Integer> stack = new ArrayDeque<>();
    int[] ans = new int[temperatures.length];
    for (int day = 0; day < temperatures.length; day++) {
      while (!stack.isEmpty() &&
             temperatures[stack.peek()] < temperatures[day]) {
        int prev = stack.pop();
        ans[prev] = day - prev;
      }

      stack.push(day);
    }

    return ans;
  }
}
