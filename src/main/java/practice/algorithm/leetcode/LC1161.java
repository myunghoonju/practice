package practice.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import practice.algorithm.leetcode.LC1372.TreeNode;

public class LC1161 {

    public int maxLevelSum(TreeNode root) {
        int answer = 0;
        int maxSum = Integer.MIN_VALUE;

        List<Integer> sumAtLevel = new ArrayList<>();
        traverse(root, 0, sumAtLevel);

        for (int i = 0; i < sumAtLevel.size(); i++) {
            if (maxSum < sumAtLevel.get(i)) {
                maxSum = sumAtLevel.get(i);
                answer = i + 1;
            }
        }

        return answer;
    }

    private void traverse(TreeNode node, int level, List<Integer> sumAtLevel) {
        if (node == null) {
            return;
        }

        if (sumAtLevel.size() == level) {
            sumAtLevel.add(node.val);
        } else {
            sumAtLevel.set(level, sumAtLevel.get(level) + node.val);
        }

        traverse(node.left, level + 1, sumAtLevel);
        traverse(node.right, level + 1, sumAtLevel);
    }

    public int maxLevelSum2(TreeNode root) {
        int answer = 0;
        int level = 0;
        int maxSum = Integer.MIN_VALUE;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            level++;
            int sumAtLevel = 0;
            for (int i = queue.size(); i > 0; --i) {
                TreeNode node = queue.poll();
                sumAtLevel += node.val;

                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (sumAtLevel > maxSum) {
                maxSum = sumAtLevel;
                answer = level;
            }
        }

        return answer;
    }
}
