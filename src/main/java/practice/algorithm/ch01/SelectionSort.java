package practice.algorithm.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectionSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int index = 0;
        int range = Integer.parseInt(br.readLine());
        int[] data = new int[range];
        String[] dataStr = br.readLine().split(" ");
        for (String num : dataStr) {
            data[index++] = Integer.parseInt(num);
        }

        int minIndex = 0;
        for (int i = 0; i < range; i++) {
            minIndex = getMinIndex(data, range, i);

            int temp = data[minIndex];
            data[minIndex] = data[i];
            data[i] = temp;
        }

        index = 0;
        for (int num: data) {
            index++;
            if (index > 0) {
                System.out.print(" ");
            }
            System.out.print(num);
        }
    }

    private static int getMinIndex(int[] data, int range, int start) {
        int result = start;
        for (int j = start; j < range; j++) {
            if (data[result] > data[j]) {
                result = j;
            }
        }

        return result;
    }
}
