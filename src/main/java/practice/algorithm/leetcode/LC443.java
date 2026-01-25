package practice.algorithm.leetcode;

import lombok.val;

public class LC443 {

  static void main() {
    val lc443 = new LC443();
    char[] a = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
    System.out.println(lc443.compress(a));
  }

  public int compress(char[] chars) {
   int idx = 0;
   int result = 0;
   while (idx < chars.length) {
     int compress = 1;
     while (idx + compress < chars.length &&
            chars[idx + compress] == chars[idx]) {
       compress++;
     }

     chars[result++] = chars[idx];
     if (compress > 1) {
       for (char c : Integer.toString(compress).toCharArray()) {
         chars[result++] = c;
       }
     }

     idx  += compress;
   }

   return result;
  }
}
