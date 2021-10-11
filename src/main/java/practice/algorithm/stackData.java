package practice.algorithm;

import java.util.Stack;

public class stackData {
    public static final String POP = "POP";
    public static final String DUP = "DUP";
    public static final String PLUS = "+";
    public static final String MINUS = "-";

    public static void main(String[] args) {
        String g = "13 DUP 4 POP 5 DUP + DUP + -"; // input data
        String[] inputData = g.split(" ");
        System.out.println(getAnswer(inputData)); // return first value [1, 2] then return 2

    }

    private static int getAnswer(String[] inputData) {
        Stack<Integer> stackData = new Stack<>();
        try {
            for (String input: inputData) {
                stackData = getSolution(stackData, input);
            }
            return stackData.pop();
        } catch (Exception e) {
            e.getStackTrace();
            return -1;
        }
    }

    private static Stack<Integer> getSolution(Stack<Integer> stackData, String input) {
        switch (input) {
            case POP:
                stackData.pop();
                break;
            case DUP:
                int number = stackData.lastElement();
                stackData.push(number);
                break;
            case PLUS:
                if (stackData.size() < 2) {
                    throw new RuntimeException("not enough numbers");
                }
                int val1 = stackData.pop();
                int val2 = stackData.pop();
                int val3 = val1 + val2;
                stackData.push(val3);
                break;
            case MINUS:
                if (stackData.size() < 2) {
                    throw new RuntimeException("not enough numbers");
                }
                int val4 = stackData.pop();
                int val5 = stackData.pop();
                int val6 = val4 - val5;
                if (val6 < 0) {
                    throw new RuntimeException("Subtraction yields a negative result");
                }
                stackData.push(val6);
                break;
            default:
                stackData.push(Integer.parseInt(input));
        }
        return stackData;
    }
}
