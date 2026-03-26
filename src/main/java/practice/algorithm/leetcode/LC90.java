package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(0, new ArrayList<>(), res, nums);
        return res;
    }

    private void backtrack(int idx, List<Integer> current, List<List<Integer>> res, int[] nums) {
        res.add(new ArrayList<>(current));

        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i - 1]) {
                continue;
            }
            current.add(nums[i]);
            backtrack(i + 1, current, res, nums);
            current.removeLast();
        }
    }
}
