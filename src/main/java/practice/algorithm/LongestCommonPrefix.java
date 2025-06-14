package practice.algorithm;

public class LongestCommonPrefix {

  //horizontal scanning
  public String sol1(String[] strs) {
    if (strs.length == 0) {
      return "";
    }

    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
      while (strs[i].indexOf(prefix) != 0) {
        prefix = prefix.substring(0, prefix.length() - 1);
      }

      if (prefix.isEmpty()) {
        return "";
      }
    }

    return prefix;
  }

  //vertical scanning
  public String sol2(String[] strs) {
    if (strs == null) {
      return "";
    }

    if (strs.length == 0) {
      return "";
    }

    for (int i = 0; i < strs[0].length(); i++) {
      char c = strs[0].charAt(i);
      for (int j = 1; j < strs.length; j++) {
        if (i == strs[j].length() || strs[j].charAt(i) != c) {
          return strs[0].substring(0, i);
        }
      }
    }

    return strs[0];
  }
  
  public String longestCommonPrefix(String[] strs) {
    return sol1(strs);
    //return sol2(strs);
  }
  
  public static void main(String[] args) {
    String[] a = {"flower", "flow", "flight"};
    System.out.println(new Note().longestCommonPrefix(a));
  }
}
