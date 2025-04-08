package practice.algorithm.nossi.ch01.ex;

import java.util.ArrayList;
import java.util.List;

public class Combination {

  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    backTrack(1, new ArrayList<>(), n, k, result);
    return result;
  }

  private void backTrack(int start,
                         List<Integer> path,
                         int n,
                         int k,
                         List<List<Integer>> result) {
    if (path.size() == k) {
      result.add(new ArrayList<>(path));
      return;
    }

    for (int i = start; i <= n; i++) {
      path.add(i);
      backTrack(i + 1, path, n, k, result);
      path.remove(path.size() - 1);
    }
  }

  public static void main(String[] args) {

  }
}
