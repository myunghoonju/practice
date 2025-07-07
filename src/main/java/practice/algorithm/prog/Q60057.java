package practice.algorithm.prog;

import java.util.ArrayList;
import java.util.List;

public class Q60057 {

  private List<String> split(String source, int length) {
    List<String> tokens = new ArrayList<>();
    for (int startIdx = 0; startIdx < source.length(); startIdx+=length) {
      int endIdx = startIdx + length;
      if (endIdx > source.length()) {
        endIdx = source.length();
      }

      tokens.add(source.substring(startIdx, endIdx));
    }

    return tokens;
  }

  private int compress(String source,int length) {
    StringBuffer sb = new StringBuffer();
    String last = "";
    int cnt = 0;
    for (String token : split(source, length)) {
      if (token.equals(last)) {
        cnt++;
      } else {

        if (cnt > 1) {
          sb.append(cnt);
          last = token;
          cnt = 1;
        }
      }
    }

    return sb.length();
  }

  public int solution(String s) {
    int min = Integer.MAX_VALUE;
    for (int length = 0; length < s.length(); length++) {
      int compressed = compress(s, length);
      if (compressed < min) {
        min = compressed;
      }
    }

    return min;
  }
}
