package practice.algorithm.prog;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Q42746 {

  public String solution(int[] numbers) {
    return Arrays.stream(numbers)
                 .mapToObj(String::valueOf)
                 .sorted((w1, w2) -> {
      int a = Integer.parseInt(w1 + w2);
      int b = Integer.parseInt(w2 + w1);
      return b - a;

    }).collect(Collectors.joining(""))
      .replaceAll("^0+", "0");
  }
}
 