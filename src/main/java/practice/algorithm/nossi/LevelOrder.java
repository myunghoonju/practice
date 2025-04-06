package practice.algorithm.nossi;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class LevelOrder {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20));
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    levelOrder(root).forEach(System.err::println);
  }

  static Set<Integer> levelOrder(TreeNode root) {
    if (root == null) {
      return Set.of();
    }

    Set<Integer> visited = new HashSet<>();
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      TreeNode current = queue.remove();
      visited.add(current.val);

      if (current.left != null) {
        queue.add(current.left);
      }

      if (current.right != null) {
        queue.add(current.right);
      }
    }

    return visited;
  }
}
