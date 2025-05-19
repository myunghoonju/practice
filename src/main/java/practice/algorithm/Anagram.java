package practice.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Anagram {

  public static void main(String[] args) {
    System.out.println(anagram("ba", "ab"));
  }

  //fixme use hashmap to search
  public static boolean anagram(String s, String t) {
    List<String> split = new ArrayList<>(List.of(s.split("")));
    List<String> tSplit = new ArrayList<>(List.of(t.split("")));
    if (split.size() != tSplit.size()) {
      return false;
    }

    for (String string : tSplit) {
      if (split.isEmpty()) {
        break;
      }

      if (!split.contains(string)) {
        return false;
      }

      split.remove(string);
    }

    return true;
  }
}
