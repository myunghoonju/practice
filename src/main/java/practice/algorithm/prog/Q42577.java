package practice.algorithm.prog;

import java.util.HashSet;
import java.util.Set;

public class Q42577 {

  public boolean solution(String[] phone_book) {
    Set<String> set = new HashSet<>();
    for (String s : phone_book) {
      for (int i = 1; i < s.length(); i++) {
        set.add(s.substring(0, i));
      }
    }

    for (String nm : phone_book) {
      if (set.contains(nm)) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    Q42577 q = new Q42577();
    String[] phone_book = new String[]{"119", "97674223", "1195524421"};
    System.out.println(q.solution(phone_book));
  }
}
