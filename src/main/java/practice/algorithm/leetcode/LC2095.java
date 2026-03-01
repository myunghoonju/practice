package practice.algorithm.leetcode;

public class LC2095 {

  class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public ListNode deleteMiddle1(ListNode head) {
    if (head.next == null) {
      return null;
    }

    ListNode first = head;
    ListNode second = head.next.next;
    while (second != null && second.next != null) {
      first = first.next;
      second = second.next.next;
    }

    first.next = first.next.next;

    return head;
  }

  public ListNode deleteMiddle2(ListNode head) {
    if (head.next == null) {
      return null;
    }

    int cnt = 0;
    ListNode first = head;
    ListNode second = head;
    while (first != null) {
      cnt += 1;
      first = first.next;
    }

    int mid = cnt / 2;
    for (int idx = 0; idx < mid - 1; ++idx) {
      second = second.next;
    }

    second.next = second.next.next;

    return head;
  }
}
