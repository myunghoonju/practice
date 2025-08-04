package practice.algorithm.prog;

import java.util.Arrays;

public class Q12945 {

  private static int[] mem = new int[100001];

  public int solution(int n) {
    Arrays.fill(mem, -1);
    for (int i = 0; i <= n; i++) {
      fibo(i);
    }

    return fibo(n);
  }

  private int fibo(int n) {
    if (mem[n] != -1) {
      return mem[n];
    }

    if(n == 0 || n == 1) {
      return n;
    }

    return mem[n] = (fibo(n - 1) + fibo(n - 2)) % 1234567;
  }
}
