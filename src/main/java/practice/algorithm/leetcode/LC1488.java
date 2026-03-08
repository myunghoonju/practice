package practice.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC1488 {

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

  private int CNT = 0;

  public int goodNodes(TreeNode root) {
    countGood(root, root.val);
    return CNT;
  }

  private void countGood(TreeNode root, int prev) {
    if (root.val >= prev) {
      CNT++;
    }

    if (root.left != null ) {
      countGood(root.left, Math.max(root.val, prev));
    }

    if (root.right != null) {
      countGood(root.right, Math.max(root.val, prev));
    }
  }
}
