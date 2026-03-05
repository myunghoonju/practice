package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

  int pairSum2(ListNode head) {
    ListNode cur = head;
    List<Integer> nodes = new ArrayList<>();
    while (cur != null) {
      nodes.add(cur.val);
      cur = cur.next;
    }

    int i = 0;
    int j = nodes.size() - 1;
    int max = 0;
    while (i < j) {
      max = Math.max(max, nodes.get(i) + nodes.get(j));
      i++;
      j--;
    }

    return max;
  }

  int pairSum3(ListNode head) {
    ListNode cur = head;
    Stack<Integer> nodes = new Stack<>();
    while (cur != null) {
      nodes.push(cur.val);
      cur = cur.next;
    }

    cur = head;
    int size = nodes.size();
    int cnt = 1;
    int max = 0;
    while (cnt <= size / 2) {
      max = Math.max(max, cur.val + nodes.peek());
      cur = cur.next;
      nodes.pop();
      cnt++;
    }

    return max;
  }
}
