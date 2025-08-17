package practice.algorithm.prog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q49190 {

    // counter-clockwise
    private static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    private static class Vertex {

        private final int x;

        private final int y;

        private final String id;

        private final Set<String> connected;

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
            this.id = id(x, y);
            this.connected = new HashSet<>();
        }

        public static String id(int x, int y) {
            return String.format("(%d, %d)", x, y);
        }
    }

    public int solution(int[] arrows) {
        int cnt = 0;
        Map<String, Vertex> map = new HashMap<>();
        Vertex v = new Vertex(0, 0);
        map.put(v.id, v);

        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) {
                int x = v.x + dx[arrow];
                int y = v.y + dy[arrow];
                String id = Vertex.id(x, y);

                if (!map.containsKey(id)) {
                    map.put(id, new Vertex(x, y));
                } else if (!v.connected.contains(id)) {
                    cnt++;
                }

                Vertex u = map.get(id);
                v.connected.add(u.id);
                u.connected.add(v.id);
                v = map.get(id);
            }
        }

        return cnt;
    }
}
