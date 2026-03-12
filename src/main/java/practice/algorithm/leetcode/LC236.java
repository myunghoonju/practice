package practice.algorithm.leetcode;

//Lowest Common Ancestor of a Binary Tree

public class LC236 {

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

  private TreeNode node;

  public TreeNode lowestCommonAncestor(TreeNode root,
                                       TreeNode p,
                                       TreeNode q) {
    tree(root, p , q);
    return node;
  }

  private boolean tree(TreeNode root, TreeNode target1, TreeNode target2) {
    if (root == null) {
      return false;
    }

    int left = tree(root.left, target1, target2) ? 1 : 0;
    int right = tree(root.right, target1, target2) ? 1 : 0;
    int mid = (root == target1 || root == target2) ? 1 : 0;
    if (left + right + mid >= 2) {
      node = root;
    }

    return left + right + mid > 0;
  }
}
