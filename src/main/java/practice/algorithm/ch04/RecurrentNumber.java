package practice.algorithm.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RecurrentNumber {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseSize = Integer.parseInt(br.readLine());
        long[] numbers = new long[caseSize];
        
        for (int caseIdx = 0; caseIdx < caseSize; caseIdx++) {
            numbers = Arrays.stream(br.readLine().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();

        }

        long ans = 1 + getPoint(numbers);
        System.out.println(ans);
    }

    private static long getPoint(long[] numbers) {
        long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = getLcm(result, numbers[i]);
        }

        return result;
    }

    private static long getGcd(long num1, long num2) {
        if (num1 % num2 == 0) {
            return num2;
        }

        return getGcd(num2, num1 % num2);
    }

    private static long getLcm(long num3, long num4) {
        return num3 * num4 / getGcd(num3, num4);
    }
}
