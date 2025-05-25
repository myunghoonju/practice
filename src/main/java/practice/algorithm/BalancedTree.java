package practice.algorithm;

import lombok.Getter;
import lombok.Setter;

public class BalancedTree {

  private static final int ROOT = 1;

  // [1,2,2,3,3,null,null,4,4]
  public boolean isBalanced() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(3);
    root.left.left.left = new TreeNode(4);
    root.left.left.right = new TreeNode(4);

    return !(helper(root)==-1);
  }
  public int helper(TreeNode node){
    if(node==null) {
      return 0;
    }

    TreeNode left = node.left;
    TreeNode right = node.right;
    int lh= helper(left);
    int rh= helper(right);

    if(lh == -1 || rh == -1) {
      return -1;
    }
    if(Math.abs(lh - rh) > 1) {
      return -1;
    }
    int i = Math.max(lh, rh) + ROOT;
    System.err.println("lh " + lh + " rh" + rh + " max + 1 = " + i);

    return i;
  }

  @Getter @Setter
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int val) { this.val = val; }
  }

  public static void main(String[] args) {
    BalancedTree f = new BalancedTree();
    System.err.println(f.isBalanced());
  }
}
