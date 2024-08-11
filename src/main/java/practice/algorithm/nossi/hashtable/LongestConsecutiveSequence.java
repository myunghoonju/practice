package practice.algorithm.nossi.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] ints = new int[4];
        ints[0] = 1;
        ints[1] = 2;
        ints[2] = 3;
        ints[3] = 15;

        System.err.println(longestConsecutive(ints));
    }

    static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = 0;
        int count = 0;
        Arrays.sort(nums);

        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                list.add(nums[i]);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (i >0 &&
                list.get(i) == (list.get(i - 1) + 1)) {
                count++;
            } else  {
                count = 1;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}

