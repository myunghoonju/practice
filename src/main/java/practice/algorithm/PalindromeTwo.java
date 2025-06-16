package practice.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PalindromeTwo {

  private static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  //arrayList
  public boolean palindrome1(ListNode head) {
    List<Integer> first = new ArrayList<>();
    ListNode node = head;
    while (node != null) {
      first.add(node.val);
      node = node.next;
    }

    List<Integer> reverse = new ArrayList<>(first);
    Collections.reverse(reverse);

    for (int i = 0; i < first.size(); i++) {
      if (first.get(i) != reverse.get(i)) {
        return false;
      }
    }

    return true;
  }

  //two runner point
  public boolean palindrome2(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }

    ListNode listNode = endOfFirstHalf(head);
    ListNode reverse = reverse(listNode.next);

    ListNode f1 = head;
    ListNode f2 = reverse;
    boolean flag = true;
    while (flag && f2 != null) {
      if (f1.val != f2.val) {
        return false;
      }

      f1 = f1.next;
      f2 = f2.next;
    }

    listNode.next = reverse(listNode.next);

    return flag;
  }

  private ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode curTemp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = curTemp;
    }

    return prev;
  }

  private ListNode endOfFirstHalf(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    if (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    return slow;
  }

  public static void main(String[] args) {
    ListNode root = new ListNode(1);
    root.next = new ListNode(2);
    root.next.next = new ListNode(2);
    root.next.next.next = new ListNode(1);

    System.out.println(new PalindromeTwo().palindrome1(root));
    System.out.println(new PalindromeTwo().palindrome2(root));
  }
}
