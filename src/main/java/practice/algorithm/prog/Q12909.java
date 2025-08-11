package practice.algorithm.prog;

import java.util.Stack;

public class Q12909 {

  static boolean solution(String s) {
    int cnt = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        cnt++;
      }  else if (c == ')') {
        if (cnt-- == 0) {
          return false;
        }
      }
    }
    return cnt == 0;
  }

  public static void main(String[] args) {
    System.out.println(sol("()"));
    System.out.println(sol("(())()"));
    System.out.println(sol("(()("));
  }

  static boolean sol(String S) {
    Stack<Character> objects = new Stack<>();
    for (char c : S.toCharArray()) {
      if (c == '(') {
        objects.push(c);
      } else  if (c == ')') {
        if (objects.isEmpty()) {
          return false;
        }

        objects.pop();
      }
    }

    return objects.empty();
  }
}
