package practice.algorithm.nossi;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BasicGraph {

  static Map<Integer, Boolean> visited = new HashMap<>();
  static Map<Integer, List<Integer>> graph = new HashMap<>();

  static void bfs(int startVertex) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(startVertex);
    visited.put(startVertex, true);

    while (!queue.isEmpty()) {
      int curVertex = queue.poll();
      for (int nextVertex : graph.get(curVertex)) {
        if (!visited.containsKey(nextVertex)) {
          queue.offer(nextVertex);
          visited.put(nextVertex, true);
        }
      }

      queue.forEach(System.err::print);
    }
  }

  static void prepareGraph() {
    graph.put(0, List.of(1, 3, 6));
    graph.put(1, List.of(0, 3));
    graph.put(2, List.of(3));
    graph.put(3, List.of(0,1,2,7));
    graph.put(4, List.of(5));
    graph.put(5, List.of(4,6,7));
    graph.put(6, List.of(0,5));
    graph.put(7, List.of(3,5));
  }

  public static void main(String[] args) {
    prepareGraph();
    bfs(0);
  }
}
