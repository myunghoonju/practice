package practice.algorithm;

public class MaxDepth {

  public static void main(String[] args) {
    TreeNode treeNode = new TreeNode(3);
    treeNode.left = new TreeNode(9);
    treeNode.right = new TreeNode(20);

    treeNode.left.left = new TreeNode();
    treeNode.left.right = new TreeNode();
    treeNode.right.left = new TreeNode(15);
    treeNode.right.right = new TreeNode(7);

    int i = new MaxDepth().maxDepth(treeNode);
    System.out.println(i);
  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
  }
}
