package practice.algorithm.prog;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q67257 {

    public static final String [][] PRECEDENCES = {
            "+-*".split(""),
            "+*-".split(""),
            "-+*".split(""),
            "-*+".split(""),
            "*-+".split(""),
            "*+-".split(""),
    };

    public long solution(String expression) {
        long max = 0;
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*", true);
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }

        for (String[] precedence : PRECEDENCES) {
            List<String> copied = new ArrayList<>(tokens);
            long val = Math.abs(calc(copied, precedence));
            if (val > max) {
                max = val;
            }
        }


        return max;
    }

    private long calc(List<String> tokens, String[] precedence) {
        for (String op : precedence) {
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals(op)) {
                    long l = Long.parseLong(tokens.get(i - 1));
                    long r = Long.parseLong(tokens.get(i + 1));

                    long result = calc(l, r, op);

                    tokens.remove(i - 1);
                    tokens.remove(i - 1);
                    tokens.remove(i - 1);
                    tokens.add(i - 1, String.valueOf(result));
                    i -= 2;
                }
            }
        }

        return Long.parseLong(tokens.get(0));
    }

    private long calc(long l, long r, String op) {
        return switch (op) {
            case "+" -> l + r;
            case "-" -> l - r;
            case "*" -> l * r;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        new Q67257().solution("100-200*300-500+20");
    }
}
