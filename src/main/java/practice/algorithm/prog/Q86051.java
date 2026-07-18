package practice.algorithm.prog;

public class Q86051 {

  public int solution(int[] numbers) {
    int answer = 0;

    if (numbers.length == 1) {
      return 45 - numbers[0];
    }

    if (numbers.length == 10) {
      return 0;
    }

    for (int i = 0; i <= 9; i++) {
      boolean contain = false;
      for (int number : numbers) {
        if (number == i) {
          contain = true;
          break;
        }
      }

      if (!contain) {
        answer += i;
      }
    }

    return answer;
  }
}
