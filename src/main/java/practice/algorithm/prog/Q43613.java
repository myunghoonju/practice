package practice.algorithm.prog;

import java.util.LinkedList;
import java.util.Queue;

public class Q43613 {

    private static class State {

        private String word;
        private int step;

        public State(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }

    private boolean convertable(String source, String destination) {
        char[] sourceArr = source.toCharArray();
        char[] destArr = destination.toCharArray();

        int diff = 0;
        for (int i = 0; i < sourceArr.length; i++) {
            if (sourceArr[i] != destArr[i]) {
                diff++;
            }
        }

        return diff == 1;
    }

    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(begin, 0));

        while (!queue.isEmpty()) {
            State state = queue.poll();
            if (state.word.equals(target)) {
                return state.step;
            }

            for (int i = 0; i < words.length; i++) {
                String next = words[i];
                if (!convertable(state.word, next)) {
                    continue;
                }

                if (visited[i]) {
                    continue;
                }

                visited[i] = true;
                queue.add(new State(next, state.step + 1));
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(new Q43613().solution("hit", "cog", words));
    }
}
