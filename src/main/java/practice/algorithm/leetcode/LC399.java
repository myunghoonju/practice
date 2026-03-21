package practice.algorithm.leetcode;

import java.util.*;

public class LC399 {

  public double[] calcEquation(List<List<String>> equations,
                               double[] values,
                               List<List<String>> queries) {
    Map<String, Map<String, Double>> graph = new HashMap<>();
    for (int idx = 0; idx < equations.size(); idx++) {
      List<String> eq = equations.get(idx);
      String dividend = eq.get(0);
      String divisor = eq.get(1);
      double value = values[idx];

      graph.computeIfAbsent(dividend, key -> new HashMap<>());
      graph.computeIfAbsent(divisor, key -> new HashMap<>());
      graph.get(dividend).put(divisor, value);
      graph.get(divisor).put(dividend, 1 / value);
    }

    double[] res = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      List<String> query = queries.get(i);
      String dividend = query.get(0);
      String divisor = query.get(1);

      if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
        res[i] = -1.0;
      } else if (dividend.equals(divisor)) {
        res[i] = 1.0;
      } else {
        Set<String> visited = new HashSet<>();
        Double found = dfs(graph, dividend, divisor, 1.0d, visited);
        res[i] = found == null ? -1.0d : found;
      }
    }
    return res;
  }

  private Double dfs(Map<String, Map<String, Double>> graph,
                     String dividend,
                     String divisor,
                     double mul,
                     Set<String> visited) {
    visited.add(dividend);

    Double result = null;
    Map<String, Double> neighbor = graph.get(dividend);
    if (neighbor.containsKey(divisor)) {
      result = mul * neighbor.get(divisor);
    } else {
      for (Map.Entry<String, Double> e : neighbor.entrySet()) {
        String key = e.getKey();
        if (visited.contains(key)) {
          continue;
        }

        result = dfs(graph, key, divisor, mul * e.getValue(), visited);
        if (result != null) {
          break;
        }
      }
    }

    return result;
  }
}
