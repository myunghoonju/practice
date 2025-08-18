package practice.algorithm.prog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q42892 {

    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        Arrays.sort(nodes, (a, b) -> b.y - a.y);

        Node tree = tree(nodes);

        List<Integer> preVisited = new ArrayList<>();
        List<Integer> postVisited = new ArrayList<>();
        pre(tree, preVisited);
        post(tree, postVisited);

        return new int[][]{
                             preVisited.stream().mapToInt(Integer::intValue).toArray(),
                             postVisited.stream().mapToInt(Integer::intValue).toArray()};
    }

    private Node tree(Node[] nodes) {
        Node root = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            save(root, nodes[i]);
        }

        return root;
    }

    private void save(Node root, Node node) {
        if (node.x < root.x) {
            if (root.left == null) {
                root.left = node;
            } else {
                save(root.left, node);
            }

            return;
        }

        if (root.right == null) {
            root.right = node;
        } else {
            save(root.right, node);
        }
    }

    private void pre(Node root, List<Integer> visited) {
        if (root == null) {
            return;
        }

        visited.add(root.val);
        pre(root.left, visited);
        pre(root.right, visited);
    }

    private void post(Node root, List<Integer> visited) {
        if (root == null) {
            return;
        }

        post(root.left, visited);
        post(root.right, visited);
        visited.add(root.val);
    }

    private static class Node {

        private final int val;

        private final int x;

        private final int y;

        private Node left;

        private Node right;

        public Node(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }
}
