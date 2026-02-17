package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC2390 {

  //LIFO
  public String removeStars(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '*') {
        stack.pop();
        continue;
      }

      stack.push(s.charAt(i));
    }

    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }

    return sb.reverse().toString();
  }

  public String removeStars2(String s) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '*') {
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        continue;
      }

      stringBuilder.append(s.charAt(i));
    }

    return stringBuilder.toString();
  }

  //my sol
  public String overTimeLimit(String s) {
    char[] arr = s.toCharArray();
    List<Character> list = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      list.add(arr[i]);
    }

    for (int i = 0; i < arr.length; i++) {
      int idx = findIdx(list);
      if (idx <= 0) {
        continue;
      }

      removeLetter(idx - 1, list);
    }

    StringBuilder sb = new StringBuilder();
    for (Character c : list) {
      sb.append(c);
    }

    return sb.toString();
  }

  public int findIdx(List<Character> list) {
    for (int i = 0; i < list.size(); i++) {
     if ('*' == list.get(i)) {
       return i;
     }
    }

    return -1;
  }

  public void removeLetter(int at, List<Character> list) {
    list.remove(at);
    list.remove(at);
  }
}
