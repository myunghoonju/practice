package practice.algorithm.nossi.ch01;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {

  public List<List<String>> palindromes(String s) {
    List<List<String>> result = new ArrayList<>();
    if (validate(s)) {
      return result;
    }

    List<String> partition = new ArrayList<>();
    backTrack(s, 0, partition, result);

    return result;
  }

  private static boolean validate(String s) {
    return (s == null || s.isEmpty());
  }

  private void backTrack(String s,
                         int start,
                         List<String> partition,
                         List<List<String>> result) {
    if (start == s.length()) {
      result.add(new ArrayList<>(partition));
      return;
    }

    for (int i = start + 1; i <= s.length(); i++) {
      String substring = s.substring(start, i);
      if (match(substring)) {
        partition.add(substring);
        backTrack(s, i, partition, result);
        partition.remove(partition.size() - 1);
      }
    }
  }

  private boolean match(String substring) {
    return substring.contentEquals(new StringBuffer(substring).reverse());
  }

  public static void main(String[] args) {
    Palindrome p = new Palindrome();
    System.err.println(p.palindromes("aab"));
    System.err.println(p.palindromes("a"));
  }
}
