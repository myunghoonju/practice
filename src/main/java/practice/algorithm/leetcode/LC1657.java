package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1657 {

  public boolean closeStrings(String word1, String word2) {
    if (word1.length() != word2.length()) {
      return false;
    }

    Map<Character, Integer> firstMap = new HashMap<>();
    Map<Character, Integer> secondMap = new HashMap<>();

    for (int i = 0; i < word1.length(); i++) {
      firstMap.put(word1.charAt(i), firstMap.getOrDefault(word1.charAt(i), 0) + 1);
      secondMap.put(word2.charAt(i), secondMap.getOrDefault(word2.charAt(i), 0) + 1);
    }

    if (!firstMap.keySet().equals(secondMap.keySet())) {
      return false;
    }

    List<Integer> firstOccurrence = new ArrayList<>(firstMap.values());
    List<Integer> secondOccurrence = new ArrayList<>(secondMap.values());
    Collections.sort(firstOccurrence);
    Collections.sort(secondOccurrence);

    return firstOccurrence.equals(secondOccurrence);

  }
}
