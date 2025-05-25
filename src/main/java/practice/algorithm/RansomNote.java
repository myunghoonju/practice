package practice.algorithm;

import java.util.HashMap;
import java.util.Map;

// O(n*m)
public class RansomNote {

  public boolean canConstruct(String ransomNote, String magazine) {
    for (char r : ransomNote.toCharArray()) {
      int index = magazine.indexOf(r);
      if (index == -1) {
        return false;
      }

      // before "index" (but not including), and the characters after "index".
      magazine = magazine.substring(0, index) + magazine.substring(index + 1);
    }

    return true;
  }

  public boolean canConstruct2(String ransomNote, String magazine) {
    Map<Character, Integer> map = map(magazine);
    if (ransomNote.length() > magazine.length()) {
      return false;
    }

    for (char c : ransomNote.toCharArray()) {
      if (map.getOrDefault(c, 0) == 0) {
        return false;
      }

      map.put(c, map.getOrDefault(c, 0) - 1);
    }

    return true;
  }

  private Map<Character, Integer> map(String magazine) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : magazine.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    return map;
  }
}
