package practice.algorithm.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FourCards {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] candidates = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] winNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int range = nm[0];
        int selectCnt = nm[1];

        List<Integer> result = sol(range, selectCnt, candidates, winNumbers);
        if (result.size() == 0) {
            System.out.println("NO");
        }
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(result.get(i));
        }
    }

    private static List<Integer> sol(int range, int selectCnt, int[] candidates, int[] winNumbers) {
        List<Integer> data = new ArrayList<>();
        List<CardPair> pairs = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            for (int j = 0; j <= i; j++) {
                CardPair cardPair = new CardPair(candidates[i], candidates[j]);
                pairs.add(cardPair);
            }
        }

        Collections.sort(pairs);
        for (int win : winNumbers) {
            boolean isWinNo = false;
            for (CardPair pair : pairs) {
                int x = pair.sum;
                int y = win - x;
                CardPair target = new CardPair(y);
                int pairIdx = Collections.binarySearch(pairs, target);
                if (pairIdx >= 0) {
                    isWinNo = true;
                    break;
                }
            }
            if (isWinNo) {
                data.add(win);
            }
        }

        Collections.sort(data);
        return data;
    }


    static class CardPair implements Comparable<CardPair> {
        int first;
        int second;
        int sum;

        public CardPair(int first, int second) {
            this.first = first;
            this.second = second;
            this.sum = first + second;
        }

        public CardPair(int sum) {
            this.sum = sum;
            this.first = -1;
            this.second = -1;
        }

        @Override
        public int compareTo(CardPair o) {
            return this.sum - o.sum;
        }
    }
}
