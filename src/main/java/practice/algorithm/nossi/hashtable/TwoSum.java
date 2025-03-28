package practice.algorithm.nossi.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSum {

    public static void main(String[] args) {
        int[] ints = new int[4];
        ints[0] = 2;
        ints[1] = 7;
        ints[2] = 11;
        ints[3] = 15;

        Arrays.stream(twoSum(ints, 9)).forEach(System.err::println);
        Arrays.stream(twoSum2(ints, 9)).forEach(System.err::println);
    }

    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[2];
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }

        return result;
    }

    public static int[] twoSum2(int[] nums, int target) {
        return backTrack(nums, target, 0, new ArrayList<>());
    }

    public static int[] backTrack(int[] nums,
                                  int target,
                                  int start,
                                  List<Integer> ans) {
        if (ans.size() == 2) {
            if(nums[ans.get(0)] + nums[ans.get(1)] == target) {
                return new int[]{ans.get(0), ans.get(1)};
            }

            return null;
        }

        for (int i = start; i < nums.length; i++) {
            ans.add(i);
            int[] res = backTrack(nums, target, i + 1, ans);
            if (res != null) {
                return res;
            }

            ans.remove(ans.size() - 1);
        }

        return null;
    }
}
