package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LC199 {

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

  //dfs(last in first out & recursive)
  List<Integer> rights = new ArrayList<>();

  public List<Integer> rightSideView(TreeNode root) {
    addLastFromRight(root, 0);
    return rights;
  }

  private void addLastFromRight(TreeNode node, int level) {
    if (node == null) {
      return;
    }

    if (level == rights.size()) {
      rights.add(node.val);
    }

    addLastFromRight(node.right, level + 1);
    addLastFromRight(node.left, level + 1);
  }

  //bfs (first in first out & iteration)
  List<Integer> rightSide = new ArrayList<>();
  List<TreeNode> currentLevel = new ArrayList<>();

  public List<Integer> rightSideView2(TreeNode root) {
    if (root != null) {
      currentLevel.add(root);
    }

    while (!currentLevel.isEmpty()) {
      rightSide.add(currentLevel.get(currentLevel.size() - 1).val);
      List<TreeNode> next = new ArrayList<>();
      for (TreeNode n : currentLevel) {
        if (n.left != null) {
          next.add(n.left);
        }
        if (n.right != null) {
          next.add(n.right);
        }
      }

      currentLevel = next;
    }

    return rightSide;
  }
}
