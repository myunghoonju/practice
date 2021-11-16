package practice.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Summation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(br.readLine());
        List<int[]> data = new ArrayList<>();
        for(int i = 1; i <= cases; i++)
        {
            int[] arrs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            data.add(arrs);
        }

        for (int j = 0; j < cases; j++) {
            int sum = testCase(data.get(j));
            System.out.println("Case #"+(j+1));
            System.out.println(sum);
        }
    }

    private static int testCase(int[] arrs) {
        int sum = 0;
        for (int val : arrs) {
            sum += val;
        }

        return sum;
    }
}
