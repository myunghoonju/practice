package practice.algorithm.nossi.tree;

public class MaximumDepth {

  private static final int SELF_DEPTH = 1;

  public static void main(String[] args) {
    TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20));
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    System.out.println(postOrderMaxDepth(root));
  }

  static int postOrderMaxDepth(TreeNode root) {
    return move(root, 0);
  }

  static int move(TreeNode current, int depth) {
    if (current == null) {
      return depth;
    }
    int maxDepth = Math.max(move(current.left, depth), move(current.right, depth));
    return maxDepth + SELF_DEPTH;
  }
}
