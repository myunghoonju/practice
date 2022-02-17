package practice.algorithm.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Invitation {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int range = nm[0];
        int[] cards = new int[range + 1];
        int[] cardsData  = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i <= cardsData.length; i++) {
            cards[i] = cardsData[i - 1];
        }

        int caseRange = nm[1];
        HashMap<Integer, Range> cases = new HashMap<>();
        for (int i = 1; i <= caseRange; i++) {
            int[] picks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Range pick = new Range(i, picks[0], picks[1]);
            cases.put(i, pick);
        }

        getWinner(nm, cards, cases);

    }

    private static void getWinner(int[] nm,
                                  int[] cards,
                                  HashMap<Integer, Range> cases) {

        Range winner = cases.get(1);
        long[] accSum = new long[nm[0]+1];
        for (int i = 1; i <= nm[0] ; i++) {
            accSum[i] = accSum[i-1] + cards[i];
        }

        for (Range r: cases.values()) {
            r.totalPoint = accSum[r.right] - accSum[r.left-1];
            if (r.totalPoint > winner.totalPoint) {
                winner = r;
            }
        }

        System.out.printf("%d %d\n", winner.index, winner.totalPoint);
    }

    static class Range {
        private int index;
        private int left;
        private int right;
        private long totalPoint;

        Range(int index, int left, int right) {
            this.index = index;
            this.left = left;
            this.right = right;
            this.totalPoint = 0;
        }
    }
}
