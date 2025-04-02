package practice.algorithm.nossi;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backTrack(nums, 0, new ArrayList<>(), result);
    return result;
  }

  private void backTrack(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
    result.add(new ArrayList<>(path));
    for (int i = start; i < nums.length; i++) {
      path.add(nums[i]);
      backTrack(nums, i + 1, path, result);
      path.remove(path.size() - 1);
    }
  }

  public static void main(String[] args) {
    Subsets subsets = new Subsets();
    List<List<Integer>> res = subsets.subsets(new int[]{1, 2, 3});
    res.forEach(System.out::println);
  }
}
