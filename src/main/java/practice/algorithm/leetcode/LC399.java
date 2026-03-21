package practice.algorithm.leetcode;

import java.util.*;

public class LC399 {

  public double[] calcEquation(List<List<String>> equations,
                               double[] values,
                               List<List<String>> queries) {
    Map<String, Map<String, Double>> graph = new HashMap<>();
    //build graph
    for (int idx = 0; idx < equations.size(); idx++) {
      List<String> eq = equations.get(idx);
      String dividend = eq.get(0);
      String divider = eq.get(1);
      double value = values[idx];

      if (!graph.containsKey(dividend)) {
        graph.put(dividend, new HashMap<>());
      }

      if (!graph.containsKey(divider)) {
        graph.put(divider, new HashMap<>());
      }

      graph.get(dividend).put(divider, value);
      graph.get(divider).put(divider, 1 / value);
    }

    double[] res = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      List<String> query = queries.get(i);
      String dividend = query.get(0);
      String divider = query.get(1);

      if (!graph.containsKey(dividend) || !graph.containsKey(divider)) {
        res[i] = -1.0;
      } else if (dividend.equals(divider)) {
        res[i] = 1.0;
      } else {
        Set<String> visited = new HashSet<>();
        res[i] = backTrack(graph, dividend, divider, 1.0d, visited);
      }
    }
    return res;
  }

  private double backTrack(Map<String, Map<String, Double>> graph,
                           String dividend,
                           String divider,
                           double mul,
                           Set<String> visited) {
    visited.add(dividend);

    double result = -1.0d;
    Map<String, Double> neighbor = graph.get(dividend);
    if (neighbor.containsKey(divider)) {
      result = mul * neighbor.get(divider);
    } else {
      for (Map.Entry<String , Double> e : neighbor.entrySet()) {
        String key = e.getKey();
        if (visited.contains(key)) {
          continue;
        }

        result = backTrack(graph, key, divider, mul * e.getValue(), visited);
        if (result != -1.0d) {
          break;
        }
      }
    }

    visited.remove(dividend);

    return result;
  }
}
