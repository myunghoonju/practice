package practice.algorithm.nossi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumHashMap {

    public static void main(String[] args) {
        int[] ints = new int[4];
        ints[0] = 2;
        ints[1] = 7;
        ints[2] = 11;
        ints[3] = 15;

        Arrays.stream(twoSum(ints, 9)).forEach(System.err::println);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> result = new HashMap<>();
        int[] idx = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int i1 = target - nums[i];
            if (result.containsKey(i1)) {
                idx[0] = i;
                idx[1] = result.get(target - nums[i]);
            }

            result.put(nums[i], i);
        }

        return idx;
    }
}
