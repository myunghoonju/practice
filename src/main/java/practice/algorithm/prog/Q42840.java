package practice.algorithm.prog;

import java.util.stream.IntStream;

public class Q42840 {

    private static final int[][] RULES = {
            { 1, 2, 3, 4, 5 },
            { 2, 1, 2, 3, 2, 4, 2, 5 },
            { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 }
    };

    public static int[] solution(int[] answers) {
        int max = 0;
        int[] correct = new int[3];
        for (int probIdx = 0; probIdx < answers.length; probIdx++) {
            int ans = answers[probIdx];
            for (int person = 0; person < 3; person++) {
                int picked = pick(person, probIdx);
                if (picked == ans) {
                    if (++correct[person] > max) {
                        max = correct[person];
                    }
                }
            }
        }

        int maxCorrect = max;
        return IntStream.range(0, 3).filter(i -> correct[i] == maxCorrect).map(i -> i + 1).toArray();
    }

    private static int pick(int person, int prob) {
        int[] rule = RULES[person];
        int idx = prob % rule.length;

        return rule[idx];
    }

    public static void main(String[] args) {
        int[] ans = {1,2,3,4,5};
        for (int i : solution(ans)) {
            System.out.println(i);
        }
    }
}
