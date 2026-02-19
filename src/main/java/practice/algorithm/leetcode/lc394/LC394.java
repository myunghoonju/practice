package practice.algorithm.leetcode.lc394;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC394 {

  public String decodeString1(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ']') {
        List<Character> strList = new ArrayList<>();
        while (stack.peek() != '[') {
          strList.add(stack.pop());
        }

        stack.pop();
        int base = 1;
        int num = 0;
        while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
          num = num + (stack.pop() - '0') * base;
          base *= 10;
        }

        while (num != 0) {
          for (int j = strList.size() -1; j >= 0; j--) {
            stack.push(strList.get(j));
          }

          num--;
        }
      } else {
        stack.push(s.charAt(i));
      }
    }

    char[] result = new char[stack.size()];
    for (int i = result.length - 1; i >= 0; i--) {
      result[i] = stack.pop();
    }

    return new String(result);
  }
}
