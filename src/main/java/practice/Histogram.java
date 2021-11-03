package practice;

import java.util.Stack;

public class Histogram {

    public static void main(String[] args) {
        int[] a = new int[4];
        a[0] = 2;
        a[1] = 2;
        a[2] = 2;
        a[3] = 3;
        solution(a);
    }

    public static int solution(int[] histogram) {
        int answer = -1;
        int len = histogram.length;
        Stack<Integer> area = new Stack<>();

        for (int val : histogram) {
            area.push(val);
        }

        if (!area.isEmpty()) {
            for (int i = 0; i < len; i++) {
                while (histogram[area.peek()] >= histogram[i]) {
                    answer = getMaxArea(histogram, area, len, answer);
                }

                area.push(i);
            }

            for (int i = 0; i < area.size(); i++) {
                answer = getMaxArea(histogram, area, len, answer);
            }
        }

        return answer;
    }

    private static int getMaxArea(int[] histogram, Stack<Integer> area, int size, int temp) {
        int height = histogram[area.pop()];
        int width = area.isEmpty() ? size : size - 1 - area.peek();

        return Math.max(temp, (height * width));
    }
}

