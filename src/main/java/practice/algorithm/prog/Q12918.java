package practice.algorithm.prog;

public class Q12918 {

  public boolean solution(String s) {
    return s.matches("[0-9]{4}|[0-9]{6}");
  }

  public boolean solution1(String s) {
    if (s.length() != 4 && s.length() != 6) {
      return false;
    }

    for (char c : s.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    Q12918 q = new Q12918();
    System.out.println(q.solution("a234"));
    System.out.println(q.solution1("a264pp"));
  }
}
