package practice.algorithm.leetcode;

public class LC693 {

  //convert to binary
  // compare same value adjacent
  public boolean hasAlternatingBits(int n) {
    char[] charArray = Integer.toBinaryString(n).toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      char c = charArray[i];
        if (charArray.length - 1 >= i + 1 && c == charArray[i+1]) {
          return false;
        }
    }

    return true;
  }

  public boolean slowerButCleanVersion(int n) {
    String binaryStr = Integer.toBinaryString(n);
    for (int i = 0; i < binaryStr.length() - 1; i++) {
      if (binaryStr.charAt(i) == binaryStr.charAt(i + 1)) {
        return false;
      }
    }

    return true;
  }
}
