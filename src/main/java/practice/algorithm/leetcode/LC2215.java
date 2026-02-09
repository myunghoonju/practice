package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC2215 {

  public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    for (int n1 : nums1) {
      list1.add(n1);
    }

    for (int n2 : nums2) {
      list2.add(n2);
    }

    Set<Integer> res1 = new HashSet<>();
    for (int i : list1) {
      if (!list2.contains(i)) {
        res1.add(i);
      }
    }

    Set<Integer> res2 = new HashSet<>();
    for (int j : list2) {
      if (!list1.contains(j)) {
        res2.add(j);
      }
    }

    List<List<Integer>> objects = new ArrayList<>();
    objects.add(res1.stream().toList());
    objects.add(res2.stream().toList());

    return objects;
  }
}
