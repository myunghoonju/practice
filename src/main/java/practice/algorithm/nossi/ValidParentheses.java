package practice.algorithm.nossi;

import java.util.Map;
import java.util.Stack;

// https://leetcode.com/problems/valid-parentheses/description/
public class ValidParentheses {

    public static void main(String[] args) {
        System.err.println(validate("()[]{}"));
    }

    private static boolean validate(String s) {
        Stack<String> stack = new Stack<>();
        Map<String, String> map = Map.of(")", "(", "{", "}", "[", "]");

        String[] chars = s.split("");
        for (int i = 0; i < chars.length; i++) {
            String aChar = chars[i];
            if (aChar.equals("(")||
                aChar.equals("{")||
                aChar.equals("[")) {
                stack.push(aChar);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            String open = stack.pop();
            if (aChar.equals(")") && !open.equals("(")) {
                return false;
            }

            if (aChar.equals("}") && !open.equals("{")) {
                return false;
            }

            if (aChar.equals("]") && !open.equals("[")) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
