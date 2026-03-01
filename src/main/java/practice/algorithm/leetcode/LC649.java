package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC649 {

  public String predictPartyVictory(String senate) {
    int length = senate.length();
    Queue<Integer> radients = new LinkedList<>();
    Queue<Integer> dires = new LinkedList<>();
    for (int idx = 0; idx < length; idx++) {
      if (senate.charAt(idx) == 'R') {
        radients.add(idx);
        continue;
      }

      dires.add(idx);
    }

    while (!radients.isEmpty() && !dires.isEmpty()) {
      int radient = radients.poll();
      int dire = dires.poll();
      if (radient < dire) {
        radients.add(radient + length);
        continue;
      }

      dires.add(dire +  length);
    }

    return radients.isEmpty() ? "Dire" : "Radiant";
  }
}
