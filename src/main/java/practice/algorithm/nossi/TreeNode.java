package practice.algorithm.nossi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class TreeNode {

    int val;

    TreeNode left;

    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val,
                    TreeNode left,
                    TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
