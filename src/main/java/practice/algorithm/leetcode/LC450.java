package practice.algorithm.leetcode;

import practice.algorithm.leetcode.LC1372.TreeNode;

public class LC450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.right != null) {
                root.val = successor(root, key);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = predecessor(root, key);
                root.left = deleteNode(root.left, root.val);
            }
        }

        return root;
    }


    private int successor(TreeNode root, int val) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }

        return root.val;
    }

    private int predecessor(TreeNode root, int val) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }

        return root.val;
    }
}
