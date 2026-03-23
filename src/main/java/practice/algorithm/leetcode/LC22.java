package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LC22 {

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public void backtrack(List<String> result,
                          String current,
                          int openCnt,
                          int closeCnt,
                          int n) {
        if (current.length() == n * 2) {
            result.add(current);
            return;
        }

       if (openCnt < n) {
           backtrack(result,
                     current + "(",
                     openCnt + 1,
                     closeCnt,
                     n);
       }

       if (closeCnt < openCnt) {
           backtrack(result,
                     current + ")",
                     openCnt,
                     closeCnt + 1,
                     n);
       }
    }
}
