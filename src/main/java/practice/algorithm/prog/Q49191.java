package practice.algorithm.prog;

public class Q49191 {

    public int solution(int n, int[][] results) {
        boolean[][] graph = new boolean[n][n];
        for (int[] result : results) {
            int u = result[0] - 1;
            int v = result[1] - 1;
            graph[u][v] = true;
        }

        int count = 0;
        for (int u = 0; u < n; u++) {
            int wins = countForward(u, graph, new boolean[n]) - 1;
            int loses = countBackward(u, graph, new boolean[n]) - 1;
            if (wins + loses + 1 == n) {
                count++;
            }
        }

        return count;
    }

    private int countForward(int u,
                             boolean[][] graph,
                             boolean[] visited) {
        int cnt = 1;
        for (int v = 0; v < graph[u].length; v++) {
            if (!graph[u][v] || visited[v]) {
                continue;
            }

            visited[v] = true;
            cnt += countForward(v, graph, visited);
        }

        return cnt;
    }

    private int countBackward(int u,
                             boolean[][] graph,
                             boolean[] visited) {
        int cnt = 1;
        for (int v = 0; v < graph.length; v++) {
            if (!graph[v][u] || visited[v]) {
                continue;
            }

            visited[v] = true;
            cnt += countBackward(v, graph, visited);
        }

        return cnt;
    }
}
