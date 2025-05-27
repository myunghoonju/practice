package practice.algorithm;

public class ReverseList {

  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


  public ListNode reverseList(ListNode head) {
    return execute(head);
  }

  private ListNode execute(ListNode node) {
    if (node == null || node.next == null) {
      return node;
    }

    ListNode head = execute(node.next);
    node.next.next = node;
    node.next = null;

    return head;
  }
}
