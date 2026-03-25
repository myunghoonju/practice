package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LC46 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }

        backTrack(nums, new ArrayList<>(), res);

        return res;
    }

    private void backTrack(int[] nums,
                           List<Integer> current,
                           List<List<Integer>> res) {
        if(current.size() == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }

        for (int num : nums) {
            if (current.contains(num)) {
                continue;
            }

            current.add(num);
            backTrack(nums, current, res);
            current.remove(Integer.valueOf(num));
        }
    }
}
