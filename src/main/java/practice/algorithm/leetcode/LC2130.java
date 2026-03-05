package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LC2130 {

  static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  int mySol(ListNode head) {
      int max = Integer.MIN_VALUE;
      List<ListNode> nodes = new ArrayList<>();
      ListNode cur = head;
      while (cur != null) {
        nodes.add(cur);
        cur = cur.next;
      }

      if (nodes.size() == 2) {
        return nodes.get(0).val + nodes.get(1).val;
      }

      for (int i = 0; i < nodes.size(); i++) {
        int twin = nodes.size() - 1 - i;
        max = Math.max(max, nodes.get(i).val + nodes.get(twin).val);
      }

      return max;
    }
}
