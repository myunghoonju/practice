package practice.algorithm.prog;

public class Q12930 {

  public String solution(String s) {
    StringBuffer sb = new StringBuffer();
    char[] chars = s.toCharArray();

    boolean flip = true;
    for (char aChar : chars) {
      if (!Character.isAlphabetic(aChar)) {
        sb.append(aChar);
        flip = true;
        continue;
      } else {
        if (flip) {
          sb.append(Character.toUpperCase(aChar));
        } else {
          sb.append(Character.toLowerCase(aChar));
        }
      }

      flip = !flip;
    }

    return sb.toString();
  }
}
