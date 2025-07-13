package practice.algorithm.prog;

public class Q68935 {

  public int solution(int n) {
    String string = Integer.toString(n, 3);
    StringBuffer reverse = new StringBuffer(string).reverse();
    return Integer.parseInt(reverse.toString(), 3);
  }

  public static void main(String[] args) {
    Q68935 q68935 = new Q68935();
    System.out.println(q68935.solution(45));
  }
}
