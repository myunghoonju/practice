package practice.algorithm.ch03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;

public class Fibonacci {
    static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int MAX_N = 1_000_000;
    static int[] TABLE;

    static int[] getFiboTable() {
        int[] tempTable = new int[MAX_N + 1];
        tempTable[1] = 0;
        tempTable[2] = 1;
        for (int i = 3; i <= MAX_N; i++) {
            tempTable[i] = (tempTable[i - 1] + tempTable[i - 2]) % 100_000_000;
        }

        return tempTable;
    }

    public static void main(String... args) throws IOException {
        TABLE = getFiboTable();
        int range = Integer.parseInt(br.readLine());

        for (int i = 1; i < range; i++) {
            int index = Integer.parseInt(br.readLine());
            int answer = TABLE[index];
            writer.write(answer + "\n");
        }

        TABLE = null;
        writer.flush();
        writer.close();
    }
}
