package practice.algorithm.nossi;

// 정렬문제는 대표적인 시간복잡도: nLog(n) https://leetcode.com/problems/two-sum/description/
public class TwoSum {

    public static void main(String[] args) {
        test(14);
    }

    static int[] test(int target) {
        int[] a = new int[4];
        a[0] = 2;
        a[1] = 7;
        a[2] = 11;
        a[3] = 15;

        int[] b = new int[2];
        for (int i = 0; i < a.length; i++) {
            for (int j = i+ 1; j < a.length; j++) {
                if (a[i] + a[j] == target) {
                    b[0] = i;
                    b[1] = j;
                }
            }
        }
        return b;
    }
}
