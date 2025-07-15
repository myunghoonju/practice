package practice.algorithm;

public class Q12916 {

  boolean solution1(String s) {
    s = s.toLowerCase();
    int pl = s.replace("p", "").length();
    int yl = s.replace("y", "").length();


    return pl == yl;
  }

  boolean solution2(String s) {
    int pl = 0;
    int yl = 0;

    for (char c : s.toCharArray()) {
      switch (c) {
        case 'p', 'P' -> pl++;
        case 'y', 'Y' -> yl++;
      }
    }

    return pl == yl;
  }
}
