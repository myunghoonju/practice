package practice.algorithm.ch02;

/*

수정이는 1이상 10억 이하의 자연수를 하나 마음속으로 정한다.
수정이는 스탑워치를 사용해 시작버튼을 누름과 동시에 예인이에게 정한 숫자를 말해준다.
예인이는 숫자를 듣고 2초안에 그 숫자가 소수인지 아닌지를 대답하여야 한다.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrimeNumberSearch {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for (int i = 0; i < cases; i++) {
            int number = Integer.parseInt(br.readLine());
            System.out.println(isPrime(i, number));
        }
    }

    private static String isPrime(int caseIndex, int number) {
        System.out.println("Case #" + (caseIndex + 1));
        if (number == 1) {
            return "NO";
        }
        if (number == 2) {
            return "YES";
        }
        if (number % 2 == 0) {
            return "NO";
        }
        for (int j = 3; (j*j) < number; j += 2) {
            if (number % j == 0) {
                return "NO";
            }
        }
        return "YES";
    }
}