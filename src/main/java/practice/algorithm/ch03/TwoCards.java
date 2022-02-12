package practice.algorithm.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TwoCards {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] candidates = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] winNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int range = nm[0];
        int selectCnt = nm[1];

        System.out.println(sol(range, selectCnt, candidates, winNumbers));
    }

    private static int sol(int range, int selectCnt,
                           int[] candidates, int[] winNumbers) {
        int ans = 0;
        Arrays.sort(candidates);

        for (int number : winNumbers) {
            boolean flag = false;
            for (int card : candidates) {
                int search = number - card;
                int resultIdx = Arrays.binarySearch(candidates, search);
                if (resultIdx > 0) {
                    flag = true;
                }
            }
            if (flag) {
                ans++;
            }
        }
        return ans;
    }
}