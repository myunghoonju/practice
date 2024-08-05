package practice.algorithm;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/problems/daily-temperatures/description/
public class DailyTemperature {

    public static void main(String[] args) {
        Arrays.stream(dailyTemperatures2(73, 74, 75, 71, 69, 72, 76, 73)).forEach(System.out::println);
    }

    static int[] dailyTemperatures2(int... temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && (temperatures[stack.peek()] < temperatures[i])) {
                int idx = stack.pop();
                res[idx] = i - idx;
            }

            stack.push(i);

        }

        return res;
    }
}
