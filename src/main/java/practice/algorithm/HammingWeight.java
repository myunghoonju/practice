package practice.algorithm;

public class HammingWeight {

  public int hammingWeight(int n) {
    String[] arr = Integer.toBinaryString(n).split("");
    int cnt = 0;
    for (String s : arr) {
      if ("1".equals(s)) {
        cnt++;
      }
    }
    return cnt;
  }
}
