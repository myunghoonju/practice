package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LC933 {

  private static final int RANGE = 3_000;
  List<Integer> list;

  public LC933() {
    this.list = new ArrayList<>();
  }

  public int ping(int t) {
    list.add(t);

    int cnt = 0;
    for (Integer i : list) {
      if (i >= t - RANGE && i <= t) {
        cnt++;
      }
    }

    return cnt;
  }

  public int pingFaster(int t) {
    list.add(t);
    while (list.getFirst() < t - RANGE) {
      list.removeFirst();
    }

    return list.size();
  }
}
