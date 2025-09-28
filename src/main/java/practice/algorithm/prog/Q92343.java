package practice.algorithm.prog;

import java.util.HashSet;
import java.util.Set;

public class Q92343 {

  public int solution(int[] info, int[][] edges) {
    boolean[][] tree = new boolean[info.length][info.length];
    for (int[] edge : edges) {
      tree[edge[0]][edge[1]] = true;
    }

    Set<Integer> nodes = new HashSet<>();
    nodes.add(0);

    return sheep(nodes, 0, 0, info, tree);
  }

  private int sheep(Set<Integer> nodes,
                    int sheep,
                    int wolf,
                    int[] info,
                    boolean[][] tree) {
    int maxSheep = sheep;
    for (int node : nodes) {
      int nextSheep = sheep;
      int nextWolf = wolf;

      if (info[node] == 0) {
        nextSheep += 1;
      } else {
        nextWolf += 1;
      }

      if (nextWolf >= maxSheep) {
        continue;
      }

      int max = sheep(nextNode(nodes, node, tree),
                      nextSheep,
                      nextWolf,
                      info,
                      tree);

      if (max > maxSheep) {
        maxSheep = max;
      }
    }

    return maxSheep;
  }

  private Set<Integer> nextNode(Set<Integer> nodes,
                                int node,
                                boolean[][] tree) {
    Set<Integer> nextNodes = new HashSet<>(nodes);
    nextNodes.remove(node);

    for (int i = 0; i < tree[node].length; i++) {
      if (!tree[node][i]) {
        continue;
      }

      nextNodes.add(i);
    }

    return nextNodes;
  }
}
