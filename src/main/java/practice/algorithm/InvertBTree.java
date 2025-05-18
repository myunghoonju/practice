package practice.algorithm;

public class InvertBTree {

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return root;
    }

    invert(root.left);
    invert(root.right);

    return root;
  }

  public static void invert(TreeNode root) {
    TreeNode current = root.left;
    root.left = root.right;
    root.right = current;
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
