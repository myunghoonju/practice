package practice.algorithm.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueNumber {

    public static final int MAX_SERIAL_NUMBER = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] freqTable = new int[MAX_SERIAL_NUMBER];
        for (int i = 0; i < size; i++) {
            int index = data[i];
            freqTable[index] += 1;
        }

        List<Integer> answer = new ArrayList<>();
        for (int j = 0; j < freqTable.length; j++) {
            if (freqTable[j] == 1) {
                answer.add(j);
            }
        }

        for(int k = 0; k < answer.size(); k++) {
            int element = answer.get(k);
            if (k > 0) {
                System.out.print(" ");
            }
            System.out.print(element);
        }
    }
}
