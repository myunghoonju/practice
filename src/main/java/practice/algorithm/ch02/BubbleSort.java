package practice.algorithm.ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int range = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bubbleSort(data, range);
        int num = 0;
        for (int d: data) {
            if (num > 0) {
                System.out.print(" ");
            }
            System.out.print(d);
            num++;
        }
    }

    private static void bubbleSort(int[] data, int range) {
        for (int i = 0; i < range; i++) {
            int count = 0;
            for (int j = 0; j < range-i-1; j++) {
                if (data[j] > data[j+1]) {
                    int temp = data[j+1];
                    data[j+1] = data[j];
                    data[j] = temp;
                    count++;
                }
            }
            if (count < 1) {
                break;
            }
        }
    }
}
