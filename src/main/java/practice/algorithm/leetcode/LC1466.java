package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1466 {

    int count = 0;

    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<List<Integer>>> graph = new HashMap<>();
        for (int[] connection : connections) {
            graph.computeIfAbsent(connection[0], key -> new ArrayList<>()).add(Arrays.asList(connection[1], 1));
            graph.computeIfAbsent(connection[1], key -> new ArrayList<>()).add(Arrays.asList(connection[0], 0));
        }

        dfs(0, -1, graph);

        return count;
    }

    private void dfs(int node, int parent, Map<Integer, List<List<Integer>>> graph) {
        if (!graph.containsKey(node)) {
            return;
        }

        for (List<Integer> neighbors : graph.get(node)) {
            int n = neighbors.get(0);
            int v = neighbors.get(1);
            if (n != parent) {
                count += v;
                dfs(n, node, graph);
            }
        }
    }
}
