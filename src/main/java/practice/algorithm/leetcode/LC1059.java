package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LC1059 {

  public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
    List<Integer>[] graph = graph(n, edges);
    return dfs(graph, source, destination, new String[n]);
  }

  private boolean dfs(List<Integer>[] graph, int node, int dest, String[] state) {
    if (state[node] != null) {
      return state[node].equals("black");
    }

    if (graph[node].isEmpty()) {
      return node == dest;
    }

    state[node] = "gray";
    for (int next : graph[node]) {
      if (!dfs(graph, next, dest, state)) {
        return false;
      }
    }

    state[node] = "black";

    return true;
  }

  private List<Integer>[] graph(int n, int[][] edges) {
    List<Integer>[] graph = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      graph[edge[0]].add(edge[1]);
    }

    return graph;
  }
}
