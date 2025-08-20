package practice.algorithm.prog;

import java.util.PriorityQueue;

public class Q42628 {

  private static class DoublePriorityQueue {
    private int size = 0;
    private final PriorityQueue<Integer> min = new PriorityQueue<>();
    private final PriorityQueue<Integer> max = new PriorityQueue<>((left, right) -> right - left);

    public void add(int value) {
      min.add(value);
      max.add(value);
      size++;
    }

    public void removeMax() {
      if (size == 0) return;
      max.poll();
      if (--size == 0) {
        max.clear();
        min.clear();
      }
    }

    public void removeMin() {
      if (size == 0) return;
      min.poll();
      if (--size == 0) {
        max.clear();
        min.clear();
      }
    }

    public int max() {
      if (size == 0) {
        return 0;
      }

      return max.peek();
    }

    public int min() {
      if (size == 0) {
        return 0;
      }

      return min.peek();
    }
  }

  public int[] solution(String[] operations) {
    DoublePriorityQueue dpq = new DoublePriorityQueue();
    for (String op : operations) {
      String[] token = op.split(" ");
      String command = token[0];
      String value = token[1];
      switch (command) {
        case "I" -> dpq.add(Integer.parseInt(value));
        case "D" -> {
          if (value.equals("1")) {
            dpq.removeMax();
          } else {
            dpq.removeMin();
          }
        }
      }
    }

    return new  int[]{dpq.max(), dpq.min()};
  }
}
