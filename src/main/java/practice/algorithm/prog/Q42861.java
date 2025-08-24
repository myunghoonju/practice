package practice.algorithm.prog;

import java.util.Arrays;
import java.util.Comparator;

public class Q42861 {

  private static class UnionNode {

    private int depth = 1;
    private UnionNode parent = null;

    public boolean connected (UnionNode un) {
      return root() == un.root();
    }

    public void merge(UnionNode un) {
      if (connected(un)) {
        return;
      }

      UnionNode root = root();
      UnionNode unRoot = un.root();
      if (root.depth > unRoot.depth) {
        unRoot.parent = root;
      } else if (root.depth < unRoot.depth) {
        root.parent = unRoot;
      } else {
        unRoot.parent = root;
        root.depth = root.depth + 1;
      }
    }

    private UnionNode root() {
      if (parent == null) {
        return this;
      }

      return parent.root();
    }
  }

  private static class EdgeNode {

    public final int u;

    public final int v;

    public final int cost;

    public EdgeNode(int u, int v, int cost) {
      this.u = u;
      this.v = v;
      this.cost = cost;
    }
  }

  public int solution(int n, int[][] costs) {
    // ascending order of cost
    EdgeNode[] edges = Arrays.stream(costs)
                             .map(edge -> new EdgeNode(edge[0], edge[1], edge[2]))
                             .sorted(Comparator.comparing(e -> e.cost))
                             .toArray(EdgeNode[]::new);
    // prepare nodes
    UnionNode[] unionNodes = new UnionNode[n];
    for (int i = 0; i < n; i++) {
      unionNodes[i] = new UnionNode();
    }

    // accumulate cost
    int total = 0;
    for (EdgeNode edge : edges) {
      UnionNode node1 = unionNodes[edge.u];
      UnionNode node2 = unionNodes[edge.v];
      if (node1.connected(node2)) {
        continue;
      }

      node1.merge(node2);
      total = total + edge.cost;
    }

    return total;
  }

  public static void main(String[] args) {
    System.err.println(new Q42861().solution(4, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}));
  }
}
