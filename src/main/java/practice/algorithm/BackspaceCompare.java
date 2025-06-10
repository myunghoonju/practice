package practice.algorithm;

import java.util.Stack;

public class BackspaceCompare {

  public static void main(String[] args) {
    boolean sol = new BackspaceCompare().b("ab#c", "ad#c");
    System.out.println(sol);
  }

  public boolean b(String s, String t) {
    return build(s).equals(build(t));
  }

  public String build(String str) {
    Stack<Character> ans = new Stack();
    for (char c : str.toCharArray()) {
      if (c != '#') {
        ans.push(c);
      } else if (!ans.empty()) {
        ans.pop();
      }
    }

    return String.valueOf(ans);
  }
}
