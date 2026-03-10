package practice.algorithm.leetcode;

public class LC1372 {

  private int length = 0;

  public int longestZigZag(TreeNode root) {
    zigZag(root, false, 0);
    return length;
  }

  private void zigZag(TreeNode node, boolean left, int step) {
    if (node == null) {
      return;
    }

    length = Math.max(length, step);

    if (left) {
      zigZag(node.left, false, step + 1);
      zigZag(node.right, true, 1);
    }

    if (!left) {
      zigZag(node.left, false, 1);
      zigZag(node.right, true, step + 1);
    }
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
