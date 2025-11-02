package practice.algorithm.leetcode;

public class LC1055 {

  public int shortestWay(String source, String target) {
    boolean[] sourceChar = new boolean[26];
    for (char c : source.toCharArray()) {
      sourceChar[c - 'a'] = true;
    }

    // if this pass then never infinite loop occurs in while phrase
    for (char c : target.toCharArray()) {
      if (!sourceChar[c - 'a']) {
        return -1;
      }
    }

    String concatenatedSource = source;
    int cnt = 1;
    while (!subsequence(target, concatenatedSource)) {
      concatenatedSource += source;
      cnt++;
    }

    return cnt;
  }

  private boolean subsequence(String target, String source) {
    int i = 0;
    int j = 0;
    while (i < target.length() && j < source.length()) {
      if (target.charAt(i) == source.charAt(j)) {
        i++;
      }
      j++;
    }

    return i == target.length();
  }
}
