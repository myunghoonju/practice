package practice.algorithm.nossi.ch01.ex;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/permutations/description/
public class Permutation {

  static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    backTrack(new ArrayList<>(), nums, ans);

    return ans;
  }

  static void backTrack(List<Integer> current, int[] nums, List<List<Integer>> ans) {
    if (current.size() == nums.length) {
      ans.add(new ArrayList<>(current));
      return;
    }

    for (int num : nums) {
      if (!current.contains(num)) {
        current.add(num);
        backTrack(current, nums, ans);
        current.remove(current.size() - 1);
      }
    }
  }

  public static void main(String[] args) {
    int[] ints = {0, 1};
    System.out.println(permute(ints));

  }
}
