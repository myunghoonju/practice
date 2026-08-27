package practice.algorithm.leetcode;

import java.util.TreeSet;

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 *
 *
 */
public class SmallestInfiniteSet {

  private final TreeSet<Integer> set;

  public SmallestInfiniteSet() {
    set = new TreeSet<>();

    for (int i = 1; i <= 1000; i++) {
      set.add(i);
    }
  }

  public int popSmallest() {
    return set.pollFirst();
  }

  public void addBack(int num) {
    set.add(num);
  }
}
