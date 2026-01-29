package practice.algorithm.leetcode;

import java.util.Set;

public class LC1456 {

  public int maxVowels(String s, int k) {
    Set<Character> vowel = Set.of('a', 'e', 'i', 'o', 'u');

    int cnt = 0;
    for (int i = 0; i < k; i++) {
      cnt += vowel.contains(s.charAt(i)) ? 1 : 0;
    }

    int ans = cnt;
    for (int i = k; i < s.length(); i++) {
      cnt += vowel.contains(s.charAt(i)) ? 1 : 0;
      cnt -= vowel.contains(s.charAt(i - k)) ? 1 : 0;
      ans = Math.max(ans, cnt);
    }

    return ans;
  }
}
