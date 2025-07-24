package practice.algorithm.prog;

public class Q12917 {

  public static String solution(String s) {
    return s.chars().boxed().sorted((w1, w2) -> w2 - w1)
            .collect(StringBuffer::new, StringBuffer::appendCodePoint, StringBuffer::append)
            .toString();
  }

  public static void main(String[] args) {
    System.err.println(solution("Zbcdefg"));
  }
}
