package practice.algorithm.ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Consecutivity {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean result = isConsecutive(data, n);

        if (result) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static boolean isConsecutive(int[] data, int n) {
        int maximum = data[0];
        int minimum = data[0];

        for (int i = 0; i < n; i++) {
            if (maximum < data[i]) {
                maximum = data[i];
            }
            if (minimum > data[i]) {
                minimum = data[i];
            }
        }

        if (maximum - minimum + 1 == n) {
            return true;
        } else {
            return false;
        }
    }
}