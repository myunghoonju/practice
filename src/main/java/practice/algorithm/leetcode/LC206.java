package practice.algorithm.leetcode;

public class LC206 {

  public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null && curr.next != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }

  public ListNode reverseList2(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode reversed = reverseList2(head.next);
    head.next.next = head;
    head.next = null;

    return reversed;
  }

 public static class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

}
