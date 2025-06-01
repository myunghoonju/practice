package practice.algorithm;

public class DiameterBTree {
  
  private int depth;

  public int diameterOfBinaryTree(TreeNode root) {
    depth(root);
    return depth;
  }

  private int depth(TreeNode node) {
    if (node == null) {
      return -1;
    }

    int lDepth = depth(node.left);
    int rDepth = depth(node.right);

    // 2 == root + the other side of node
    depth = Math.max(depth, lDepth + rDepth + 2);

    // 1 == root
    return Math.max(lDepth, rDepth) + 1;
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
