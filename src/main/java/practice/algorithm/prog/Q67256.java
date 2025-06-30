package practice.algorithm.prog;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Q67256 {

  public static void main(String[] args) {
    int[] n = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    Q67256 q67256 = new Q67256();
    System.out.println(q67256.solution(n, "right"));
  }

  public String solution(int[] numbers, String hand) {
    Hand r = new Hand("R", hand.equals("right"), 2);
    Hand l = new Hand("L", hand.equals("left"), 0);

    return Arrays.stream(numbers)
                 .mapToObj(h -> press(h, r, l).hand)
                 .collect(Collectors.joining());
  }

  private int x(int number) {
    if (number == 0) {
      return 1;
    }

    return (number - 1) % 3;
  }

  private int y(int number) {
    if (number == 0) {
      return 3;
    }

    return (number - 1) / 3;
  }

  private Hand press(int number, Hand r, Hand l) {
    int x = x(number);
    int y = y(number);

    float rDist = r.distance(x, y);
    float lDist = l.distance(x, y);

    Hand hand = r;
    if (rDist > lDist) {
      hand = l;
    }

    hand.move(x, y);
    return hand;
  }

  private static class Hand {

    private final int baseX;

    private final String hand;

    private final float preference;

    private int x;

    private int y;

    public Hand(String hand, boolean prefer, int x) {
      this.hand = hand;
      this.baseX = x;
      this.preference = prefer ? 0.5f : 0;
      this.x = x;
      this.y = 3;
    }

    public float distance(int x, int y) {
      if (x == baseX) {
        return 0;
      }

      int dist = Math.abs(x - this.x) + Math.abs(y - this.y);
      return dist - preference;
    }

    public void move(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
