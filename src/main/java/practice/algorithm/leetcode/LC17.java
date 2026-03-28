package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC17 {

    List<String> TWO = List.of("a", "b", "c");
    List<String> THREE = List.of("d", "e", "f");
    List<String> FOUR = List.of("g", "h", "i");
    List<String> FIVE = List.of("j", "k", "l");
    List<String> SIX = List.of("m", "n", "o");
    List<String> SEVEN = List.of("p", "q", "r", "s");
    List<String> EIGHT = List.of("t", "u", "v");
    List<String> NINE = List.of("w", "x", "y", "z");
    Map<String, List<String>> map = new HashMap<>();

    List<String> result = new ArrayList<>();

    List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        map.put("2", TWO);
        map.put("3", THREE);
        map.put("4", FOUR);
        map.put("5", FIVE);
        map.put("6", SIX);
        map.put("7", SEVEN);
        map.put("8", EIGHT);
        map.put("9", NINE);

        backtrack(digits, 0, new StringBuilder(), result);

        return result;
    }

    void backtrack(String digits,
                   int index,
                   StringBuilder current,
                   List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        List<String> strings = map.get(String.valueOf(digits.charAt(index)));
        for (String string : strings) {
            current.append(string);
            backtrack(digits, index + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
