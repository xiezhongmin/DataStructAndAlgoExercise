package _00_leetcode.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * 翻转二叉树
 * 示例:
 *
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 */
public class _226_翻转二叉树 {
    /**
     * 利用后续遍历
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;

        invertTree1(root.left);
        invertTree1(root.right);

        // 交换
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        return root;
    }

    /**
     * 利用层续遍历
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // 交换
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) { queue.offer(node.left); }
            if (node.right != null) { queue.offer(node.right); }
        }

        return root;
    }

}
