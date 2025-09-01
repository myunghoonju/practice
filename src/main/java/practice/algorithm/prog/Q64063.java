package practice.algorithm.prog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q64063 {

  private static class Node {
    private  int depth = 1;
    private  Node parent;
    private long max;

    public boolean connected(Node node) {
      return root() == node.root();
    }

    public long max() {
      return root().max;
    }

    private Node root() {
      if (parent == null) {
        return this;
      }

      return parent.root();
    }

    public void merge(Node node) {
      if (connected(node)) {
        return;
      }

      Node root1 = root();
      Node root2 = node.root();
      if (root1.depth > root2.depth) {
        root2.parent = root1;
      } else if (root1.depth < root2.depth) {
        root1.parent = root2;
      } else {
        root2.parent = root1;
        root1.depth += 1;
      }

      root1.max = root2.max = Math.max(root1.max, root2.max);
    }

    public Node(long max) {
      this.max = max;
    }
  }

  public long[] solution(long k, long[] room_number) {
    List<Long> assigned = new ArrayList<>();
    Map<Long, Node> nodes = new HashMap<>();
    for (long num : room_number) {
      if (nodes.containsKey(num)) {
        num = nodes.get(num).max() + 1;
      }

      Node node = new Node(num);
      nodes.put(num, node);
      if (nodes.containsKey(num - 1)) {
        node.merge(nodes.get(num - 1));
      }
      if (nodes.containsKey(num + 1)) {
        node.merge(nodes.get(num + 1));
      }

      assigned.add(num);
    }

    return assigned.stream().mapToLong(Long::longValue).toArray();
  }
}
