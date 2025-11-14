package practice.algorithm.leetcode;

public class LC549 {

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

  static int max = 0;

  public static int longestConsecutive(TreeNode root) {
    longestPath(root);
    return max;
  }

  private static int[] longestPath(TreeNode root) {
    if (root == null) {
      return new int[] {0, 0};
    }

    int inc = 1;
    int dcr = 1;
    if (root.left != null) {
      int[] left = longestPath(root.left);
      if (root.val == root.left.val + 1) {
        dcr = left[1] + 1;
      } else if (root.val == root.left.val - 1) {
        inc = left[0] + 1;
      }
    }

    if (root.right != null) {
      int[] right = longestPath(root.right);
      if (root.val == root.right.val + 1) {
        dcr = Math.max(dcr, right[1] + 1);
      } else if (root.val == root.right.val - 1) {
        inc = Math.max(inc, right[0] + 1);
      }
    }

    max = Math.max(max, inc + dcr - 1);

    return new int[] {inc, dcr};
  }
}
