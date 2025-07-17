package practice.algorithm;

public class Q81301 {

  private static final String[] NUMBERS = { "zero","one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

  public int solution(String s) {
    for (int i = 0; i < NUMBERS.length; i++) {
       s = s.replace(NUMBERS[i], Integer.toString(i));
    }

    return Integer.parseInt(s);
  }

  public static void main(String[] args) {
    Q81301 q = new Q81301();
    System.out.println(q.solution("one4seveneight"));
  }
}
