package practice.algorithm.prog;

import java.util.Arrays;

public class Q42746 {

  public String solution(int[] numbers) {
    Integer[] boxed = Arrays.stream(numbers).boxed().toArray(Integer[]::new);

    Arrays.sort(boxed, (a, b) -> {
      String ab = "" + a + b;
      String ba = "" + b + a;
      return ba.compareTo(ab); // "ba"가 더 크면 양수 -> a가 뒤로 밀림 -> 결과적으로 큰 조합이 앞으로
    });

    // 3. 정렬된 순서대로 이어붙이기
    StringBuffer sb = new StringBuffer();
    for (int n : boxed) {
      sb.append(n);
    }

    // 4. 경계 케이스: 맨 앞이 '0'이면 전부 0이라는 뜻 -> "0" 반환
    if (sb.charAt(0) == '0') {
      return "0";
    }

    return sb.toString();
  }
}
