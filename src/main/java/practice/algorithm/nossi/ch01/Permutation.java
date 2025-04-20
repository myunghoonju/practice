package practice.algorithm.nossi.ch01;

public class Permutation {

  private static final int[] factorial = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

  public static void main(String[] args) {
    Permutation permutation = new Permutation();
    System.out.println(permutation.permutation(4, 9));
  }

  public String permutation(int n, int k) {
    int[] orders = new int[n + 1];
    search(orders, n, k, 0, 0);
    return res(n, orders);
  }

  void search(int[] orders, int n, int k, int order, int cnt) {
    for (int i = 1; i <= n; i++) {
      if (orders[i] != 0) {
        continue;
      }

      if (k > cnt + factorial[n - order - 1]) {
        cnt += factorial[n - order - 1];
        continue;
      }

      orders[i] = order + 1;
      search(orders, n, k, order + 1, cnt);
      break;
    }
  }

  private String res(int n, int[] orders) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 1; j <= n; j++) {
        if (orders[j] == i + 1) {
          b.append(j);
        }
      }
    }
    return b.toString();
  }
}
