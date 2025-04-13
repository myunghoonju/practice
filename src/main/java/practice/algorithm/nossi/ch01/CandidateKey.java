<<<<<<< HEAD:src/main/java/practice/algorithm/nossi/ch01/CandidateKey.java
package practice.algorithm.nossi.ch01;
=======
package practice.algorithm;
>>>>>>> origin/main:src/main/java/practice/algorithm/CandidateKey.java

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CandidateKey {

    public static void main(String[] args) {
      String[][] strings = {
              {"100","ryan","music","2"},
              {"200","apeach","math","2"},
              {"300","tube","computer","3"},
              {"400","con","computer","4"},
              {"500","muzi","music","3"},
              {"600","apeach","music","2"}
      };

      System.out.println(foo(strings));
    }

    private static int foo(String[][] relation) {
      int rowLen = relation.length;
      int colLen = relation[0].length;
      List<Set<Integer>> candid = new ArrayList<>();

      for (int len = 1; len <= colLen; len++) {
        List<Set<Integer>> comb = new ArrayList<>();

        combination(0, 0, len, new HashSet<>(), comb, colLen);
        candidate(relation, comb, candid, rowLen);
      }

      return candid.size();
    }

  private static void combination(int begin,
                                  int depth,
                                  int maxDepth,
                                  Set<Integer> cur,
                                  List<Set<Integer>> candid,
                                  int colLen) {
    if (depth == maxDepth) {
      candid.add(new HashSet<>(cur));
      return;
    }

    for (int i = begin; i < colLen; i++) {
      cur.add(i);
      combination(i + 1,
                  depth + 1,
                  maxDepth,
                  cur,
                  candid,
                  colLen);
      cur.remove(i);
    }
  }

  private static void candidate(String[][] data,
                                List<Set<Integer>> comb,
                                List<Set<Integer>> candid,
                                int rowLen) {
    for (Set<Integer> combVal : comb) {
      boolean min = true;
      Set<String> rowSet = new HashSet<>();

      for (Set<Integer> candidVal : candid) {
        if (combVal.containsAll(candidVal)) {
          min = false;
          break;
        }
      }

      if (!min) {
        continue;
      }

      for (String[] row : data) {
        StringBuffer rowStr = new StringBuffer();
        for (int col : combVal) {
          rowStr.append(row[col]);
        }

        rowSet.add(rowStr.toString());
      }

      if (rowSet.size() == rowLen) {
        candid.add(new HashSet<>(combVal));
      }
    }
  }
}

