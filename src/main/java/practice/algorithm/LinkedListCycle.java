package practice.algorithm;

import lombok.Getter;
import lombok.Setter;

public class LinkedListCycle {

  @Getter @Setter
  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public static boolean cycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }

    ListNode fast = head.next;
    ListNode slow = head;

    while (fast != slow) {
      if (fast == null || fast.next == null) {
        return false;
      }

      fast = fast.next.next;
      slow = slow.next;
    }

    return true;
  }

  //[3,2,0,-4]
  public static void main(String[] args) {
    ListNode head = new ListNode(3);
    head.next = new ListNode(2);
    head.next.next = new ListNode(0);
    head.next.next.next = new ListNode(-4);
    System.err.println(cycle(head));
  }
}
