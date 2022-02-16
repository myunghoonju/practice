package practice.algorithm.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeCards {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] candidates = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] winNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int range = nm[0];
        int selectCnt = nm[1];

        List<Integer> result = sol(range, selectCnt, candidates, winNumbers);
        if (result.size() == 0) {
            System.out.println("NO");
        }
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(result.get(i));
        }
    }

    private static List<Integer> sol(int range, int selectCnt, int[] candidates, int[] winNumbers) {
        List<Integer> data = new ArrayList<>();
        Arrays.sort(candidates);
        for (int winNo : winNumbers) {
            boolean isWinNo = false;
            for (int i = 0; i < range; i++) {
                int x = candidates[i];
                for (int j = 0; j <= i; j++) {
                    int y = candidates[j];
                    int possibleNo = winNo - (x + y);
                    if (Arrays.binarySearch(candidates, possibleNo) >= 0) {
                        isWinNo = true;
                        break;
                    }
                }
                if (isWinNo) {
                    break;
                }
            }
            if (isWinNo) {
                data.add(winNo);
            }
        }

        Collections.sort(data);
        return data;
    }

}
