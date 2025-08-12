package practice.algorithm.prog;

import java.util.Stack;

public class Q76502 {

    public int solution(String s) {
      char[] str = s.toCharArray();
      int cnt = 0;
      for (int offset = 0; offset < str.length; offset++) {
        if (correct(str, offset)) {
          cnt++;
        }
      }

      return cnt;
    }

    private boolean correct(char[] str, int offset) {
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < str.length; i++) {
        char c = str[(offset + i) % str.length];
        switch (c) {
          case '[' -> stack.push(']');
          case '(' -> stack.push(')');
          case '{' -> stack.push('}');
          case ')', ']', '}' -> {
            if (stack.isEmpty()) {
              return false;
            }

            if (stack.pop() != c) {
              return false;
            }
          }
        }
      }

      return stack.isEmpty();
    }

  public static void main(String[] args) {
    Q76502 q76502 = new Q76502();
    int solution = q76502.solution("}]()[{");
    System.out.println(solution);
  }
}
