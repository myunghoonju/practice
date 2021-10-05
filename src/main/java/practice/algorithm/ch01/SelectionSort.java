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

        //selection sort
    }
}
