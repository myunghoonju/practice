package practice.algorithm.nossi.ch02.ex;

import java.util.ArrayDeque;
import java.util.Deque;

public class Parentheses {

  public static void main(String[] args) {
    System.out.println(valid("(]"));
  }

  static boolean valid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char p : s.toCharArray()) {
      switch (p) {
        case '(': stack.push(')'); break;
        case '{': stack.push('}'); break;
        case '[': stack.push(']'); break;
        default:
          if (!stack.isEmpty() && stack.peek() == p) {
            stack.pop();
          } else {
            return false;
          }
      }
    }

    return stack.isEmpty();
  }
}
