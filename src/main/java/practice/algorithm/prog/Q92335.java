package practice.algorithm.prog;

public class Q92335 {

  public int solution(int n, int k) {
    String str = Long.toString(n, k);
    String[] split = str.split("0+"); // remove recursive 0

    int cnt = 0;
    for (String s : split) {
      if (primary(Long.parseLong(s))) {
        cnt++;
      }
    }

    return cnt;
  }

  private boolean primary(long n) {
    if (n <= 1) {
      return false;
    }

    for (long i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }
}
