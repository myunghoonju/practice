package practice.algorithm.nossi;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraGraph {

  static class Edge {
    public int to;
    public int distance;

    public Edge(int to, int distance) {
      this.to = to;
      this.distance = distance;
    }
  }

  static class Entry implements Comparable<Entry> {

    private int node;

    private int distance;

    public Entry(int node, int distance) {
      this.node = node;
      this.distance = distance;
    }

    @Override
    public int compareTo(@NotNull Entry o) {
      return this.distance - o.distance;
    }
  }

  static int dijkstra(int start, int end) {
    Map<Integer, List<Edge>> graph = new HashMap<>() {{
      put(1, List.of(new Edge(2, 2), new Edge(4, 1)));
      put(2, List.of(new Edge(3, 2), new Edge(5, 2), new Edge(6, 4)));
      put(3, List.of(new Edge(6, 4)));
      put(4, List.of(new Edge(7, 5)));
      put(5, List.of(new Edge(8, 1)));
      put(6, List.of(new Edge(5, 3)));
      put(7, List.of(new Edge(6, 7), new Edge(8, 6)));
      put(8, List.of());
    }};

    int INF = Integer.MAX_VALUE;
    int[] distance = new int[graph.size() + 1];
    Arrays.fill(distance, INF);

    Queue<Entry> queue = new PriorityQueue<>();
    queue.add(new Entry(start, 0));
    distance[start] = 0;

    while (!queue.isEmpty()) {
      Entry current = queue.remove();
      if (distance[current.node] < current.distance) {
        continue;
      }

      for (Edge edge : graph.get(current.node)) {
        int newDistance = current.distance + edge.distance;
        if(newDistance < distance[edge.to]) {
          distance[edge.to] = newDistance;
          queue.add(new Entry(edge.to, newDistance));
        }
      }
    }

    return distance[end];
  }

  public static void main(String[] args) {
    System.out.println(dijkstra(1, 8));
  }
}
