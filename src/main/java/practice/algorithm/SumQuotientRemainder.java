package practice.algorithm;

public class SumQuotientRemainder {

    public static void main(String[] args) {
        // n * quotient + remainder = natural number
        // if n == remainder then remainder is 0
        long n = 2;
        long result = 0;

        for (long i = 0; i < n; i++) {
            result += (n * i) + i;
        }

        System.out.println(result);
    }
}
