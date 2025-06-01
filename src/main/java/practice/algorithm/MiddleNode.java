package practice.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MiddleNode {
  
  ListNode sol1(ListNode head) {
    List<ListNode> aa = new ArrayList<>();
    int result = 0;
    while (head != null) {
      aa.add(result++, head);
      head = head.next;
    }

    return aa.get(result / 2);
  }

  ListNode sol2(ListNode head) {
    ListNode mid = head;
    ListNode end = head;
    while (end != null && end.next != null) {
      mid = mid.next;
      end = end.next.next;
    }

    return mid;
  }
  
  static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
