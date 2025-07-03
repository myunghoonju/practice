package practice.algorithm.prog;

import java.util.Stack;

public class Q43162 {

  public static void main(String[] args) {
    Q43162 q43162 = new Q43162();
    int[][] computers = new int[][] {
            {1,1,0}, {1,1,0}, {0,0,1}
    };

    System.out.println(q43162.solution(3, computers));
  }

  public int solution(int n, int[][] computers) {
    int ans = 0;
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (visited[i]) {
        continue;
      }

      visit(i, computers, visited);
      ans++;
    }

    return ans;
  }

  private static void visit(int pc,
                            int[][] computers,
                            boolean[] visited)   {
    Stack<Integer> stack = new Stack<>();
     stack.push(pc);

    while (!stack.isEmpty()) {
      int cur = stack.pop();
      if (visited[cur]) {
        continue;
      }
      visited[cur] = true;

      for (int i = 0; i < computers[cur].length; i++) {
        if (computers[cur][i] == 0) {
          continue;
        }

        stack.push(i);
      }
    }
  }
}
