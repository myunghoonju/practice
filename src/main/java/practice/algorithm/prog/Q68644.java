package practice.algorithm.prog;

import java.util.HashSet;
import java.util.Set;

public class Q68644 {

  public int[] solution(int[] numbers) {
    Set<Integer> objects = new HashSet<>();
    for (int i = 0; i < numbers.length; i++) {
      for (int i1 = i + 1; i1 < numbers.length; i1++) {
        objects.add(numbers[i] +  numbers[i1]);
      }
    }
    return  objects.stream().mapToInt(Integer::intValue).sorted().toArray();
  }
}
