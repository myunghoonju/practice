package practice.algorithm;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.toBinaryString;

public class SumBinary {

  public static void main(String[] args) {
    System.out.println(new SumBinary().sumBinary1("11", "1"));
    System.out.println(new SumBinary().sumBinary2("11", "1"));
  }
  String sumBinary1(String a, String b) {
    return toBinaryString(parseInt(a, 2) + parseInt(b, 2));
  }

  String sumBinary2(String a, String b) {
    int n = a.length();
    int m = b.length();
    if (n < m) {
      return sumBinary2(b, a);
    }

    StringBuilder sb = new StringBuilder();
    int carry = 0;
    int jIdx = m - 1;
    int iIdx = n - 1;
    for (int i = iIdx; i >= 0; --i) {
      if (a.charAt(i) == '1') {
        ++carry;
      }

      if (jIdx >= 0 && b.charAt(jIdx--) == '1') {
        ++carry;
      }


      if (carry % 2 == 1) {
        sb.append('1');
      } else {
        sb.append('0');
      }

      carry /= 2;
    }

    if (carry == 1) {
      sb.append('1');
    }

    sb.reverse();

    return sb.toString();
  }
}
