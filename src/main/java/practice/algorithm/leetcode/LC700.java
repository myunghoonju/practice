package practice.algorithm.leetcode;

import practice.algorithm.leetcode.LC1372.TreeNode;

public class LC700 {

    private TreeNode anode;

    public TreeNode searchBST(TreeNode root, int val) {
        search(root, val);
        return anode;
    }

    private void search(TreeNode node, int val) {
        if (node == null || node.val == val) {
            anode = node;
            return;
        }

        if (node.val > val) {
            search(node.left, val);
        }

        if (node.val < val) {
            search(node.right, val);
        }
    }


    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null || val == root.val) {
            return root;
        }

        return val < root.val ?
               searchBST(root.left, val) : searchBST(root.right, val);
    }

}
