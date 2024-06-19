package practice.algorithm.nossi;

import org.checkerframework.checker.units.qual.C;

import java.util.*;

// https://leetcode.com/problems/valid-parentheses/description/
public class ValidParentheses {

    private static String[] OPEN = {"(", "{", "["};
    private static Map<String, String> CLOSE = new HashMap<>();

    public static void main(String[] args) {
        System.err.println(validate(")(){}"));
    }

    private static boolean validate(String s) {
        init();
        Stack<String> parentheses = new Stack<>();
        List<String> arr = Arrays.stream(s.split("")).map(String::valueOf).toList();

        if (arr.size() == 1) {
            return false;
        }

        for (int i = 0; i < CLOSE.size(); i++) {
            String s1 = arr.get(0);
            String s2 = CLOSE.get(s1);
            if (s2 == null) {
                return false;
            }
        }

        arr.forEach(sy -> {
            Arrays.stream(OPEN).forEach(str -> {
                if (str.equals(sy)) {
                    String close = CLOSE.get(str);
                    parentheses.push(close);
                }
            });

            if (!parentheses.isEmpty()) {
                if (String.valueOf(parentheses.peek()).equals(sy)) {
                    parentheses.pop();
                }
            }
        });

        return parentheses.isEmpty();
    }

    private static void init() {
        CLOSE.put("(", ")");
        CLOSE.put("{", "}");
        CLOSE.put("[", "]");
    }
}
