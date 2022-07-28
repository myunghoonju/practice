package practice.algorithm.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Factorization {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCnt = Integer.parseInt(br.readLine());
        int[] numbers;

        for (int caseIdx = 1; caseIdx <= caseCnt; caseIdx++) {
            numbers = Arrays.stream(br.readLine().split(" "))
                                  .mapToInt(Integer::parseInt)
                                  .toArray();

            List<Integer> factors = new ArrayList<>();
            for (int num : numbers) {
                factors = getFactors(num);
            }

            System.out.printf("#%d:\n", caseIdx);
            for(int j = 0 ; j < factors.size(); j++) {
                if( j > 0 ) {
                    System.out.print(" ");
                }
                System.out.print(factors.get(j));
            }
            System.out.println();
        }
    }

    private static List<Integer> getFactors(int number) {
        List<Integer> result = new ArrayList<>();
        for (int i = 2; i * i <= number; i++) {
            while (number % i == 0) {
                result.add(i);
                number /= i;
            }
        }

        if (number > 1) {
            result.add(number);
        }

        return result;
    }
}
