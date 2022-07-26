package practice.algorithm.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BasicNumber {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseSize = Integer.parseInt(br.readLine());

        for (int caseIdx = 0; caseIdx < caseSize; caseIdx++) {
            int[] numbers = Arrays.stream(br.readLine().split(" "))
                                  .mapToInt(Integer::parseInt)
                                  .toArray();

            System.out.printf("Case #%d:\n", caseIdx);
            System.out.printf("%d %d\n", getGcd(numbers[0], numbers[1]), getLcm(numbers[0], numbers[1]));
        }
    }

    private static long getGcd(long num1, long num2) {
        while (num1 % num2 != 0) {
            long num3 = num1 % num2;
            num1 = num2;
            num2 = num3;
        }
        return num2;
    }

    private static long getLcm(long num3, long num4) {
        long gcdNum = getGcd(num3, num4);
        long tempNum = num3 * num4;
        return tempNum / gcdNum;
    }
}
