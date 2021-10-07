package practice.algorithm.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxNum = Integer.parseInt(br.readLine());
        int summed = 0;
        for (int i = 1; i <= maxNum; i++) {
            int pSum = 0;
            for (int j = 1; j <= i; j++) {
                pSum += j;
            }
            summed += pSum;
        }
        System.out.println(summed);
    }
}
