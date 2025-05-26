package practice.algorithm;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {

  public int longestPalindrome(String s) {
    // hwo can i make a word that reads same forward and backward
    int oddFreqCharsCount = 0;
    Map<Character, Integer> freqMap = new HashMap<>();
    for (char c : s.toCharArray()) {
      freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
      if ((freqMap.get(c) % 2) != 0) {
        oddFreqCharsCount++;
      } else {
        oddFreqCharsCount--;
      }
    }

    if (oddFreqCharsCount > 0) {
      return s.length() - oddFreqCharsCount + 1;
    }

    return s.length();
  }

  public int longestPalindrome2(String s) {
    Map<Character, Integer> freqMap = new HashMap<>();
    for (char c : s.toCharArray()) {
      freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
    }

    int res = 0;
    boolean oddFreqChars = false;
    for (int freq : freqMap.values()) {
      if (freq % 2 == 0) {
        res += freq;
      } else {
        res += freq - 1;
        oddFreqChars = true;
      }
    }

    if(oddFreqChars) {
      return res + 1;
    }

    return res;
  }
}
