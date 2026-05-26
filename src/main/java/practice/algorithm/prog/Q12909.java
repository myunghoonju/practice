package practice.algorithm.prog;

import java.util.Stack;

public class Q12909 {

  public boolean solution(String s) {
    char[] charArray = s.toCharArray();
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < charArray.length; i++) {
      if (stack.isEmpty()) {
        if (charArray[i] == ')') {
          return false;
        }

        stack.push(charArray[i]);
        continue;
      }

      if (stack.peek() == '(' && charArray[i] == ')') {
        stack.pop();
      } else {
        stack.push(charArray[i]);
      }
    }

    return stack.isEmpty();
  }
}
