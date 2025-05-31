package practice.diameterBTree

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

    depth = Math.max(depth, lDepth + rDepth + 2);

    return Math.max(lDepth, rDepth) + 1;
  }
}
