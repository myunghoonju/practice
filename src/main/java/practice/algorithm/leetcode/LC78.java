package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LC78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, new ArrayList<>(), res, nums);
        return res;
    }

    private void backtrack(int idx, List<Integer> current, List<List<Integer>> res, int[] nums) {
        res.add(new ArrayList<>(current));

        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[nums.length - 1]) {
                continue;
            }
            current.add(nums[i]);
            backtrack(i + 1, current, res, nums);
            current.removeLast();
        }
    }
}
