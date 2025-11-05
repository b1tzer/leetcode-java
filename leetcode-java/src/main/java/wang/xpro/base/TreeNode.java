package wang.xpro.base;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 二叉树节点
 *
 * @author b1tzer
 */
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {
    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}