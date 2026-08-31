package practice.algorithm.prog;

public class Q121685 {
  public String[] solution(int[][] queries) {
    String[] answer = new String[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int n = queries[i][0];
      int p = queries[i][1];
      answer[i] = find(n, p);
    }

    return answer;
  }

  private String find(int n, int p) {
    int steps = n - 1;
    int values = p - 1;
    int[] digits = new int[steps];
    for (int i = steps - 1; i >= 0; i--) {
      digits[i] = values % 4;
      values = values / 4;
    }

    String[] pattern = {"RR", "Rr", "Rr", "rr"};
    String current = "Rr";
    for (int i = 0; i < steps && current.equals("Rr"); i++) {
      current = pattern[digits[i]];
    }

    return  current;
  }
}
