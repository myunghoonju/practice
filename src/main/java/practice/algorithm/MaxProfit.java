package practice.algorithm;

public class MaxProfit {

  public static void main(String[] args) {
    System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
  }

  // sliding window...
  public static int maxProfit(int[] prices) {
    int left = 0;
    int right = 1;
    int max = 0;

    while (right < prices.length) {
      if (prices[right] - prices[left] > 0) {
        max = Math.max(max, prices[right] - prices[left]);
      } else {
        left = right;
      }

      right++;
    }

    return max;
  }
}
