package practice.algorithm.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
  Group number : {((r - 1) / 3) * 3 + 1} + {((c - 1) / 3) * 1}
  Row number: ((n - 1) / 9) + 1
  Column number: ((n - 1) % 9) + 1
 */
public class Sudoku {

    private static final int MAX_NUM = 9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseSize = Integer.parseInt(br.readLine());
        List<Integer> cases = new ArrayList<>();

        for (int i = 0; i < caseSize; i++) {
            int caseNo = Integer.parseInt(br.readLine());
            cases.add(caseNo);
        }

        for (int caseIndex = 0; caseIndex < caseSize; caseIndex++) {
            int caseNo = cases.get(caseIndex);
            sol(caseIndex, caseNo);
        }
    }

    private static void sol(int caseIndex, int caseNo) {
        int row = getRow(caseNo);
        int col = getCol(caseNo);
        int group = getGroup(row, col);

        System.out.printf("Case #%d:\n", (caseIndex + 1));
        System.out.printf("%d %d %d\n", row, col, group);
    }

    private static int getRow(int caseNo) {
        return ((caseNo - 1) / MAX_NUM) + 1;
    }

    private static int getCol(int caseNo) {
        return ((caseNo - 1) % MAX_NUM) + 1;
    }

    private static int getGroup(int row, int col) {
        int number = ((row - 1) / 3) * 3 + 1;
        int offset = (col - 1) / 3;
        return (number + offset);
    }

}
