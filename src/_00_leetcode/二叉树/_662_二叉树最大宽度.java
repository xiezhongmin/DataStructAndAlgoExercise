package _00_leetcode.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 * 二叉树最大宽度
 *
 * 示例 1:
 * 输入:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 */
public class _662_二叉树最大宽度 {
    static public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelCount = 1;
        System.out.println(levelCount);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelCount--;
            System.out.println("node= " + node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelCount == 0) {
                levelCount = queue.size();
                System.out.println(levelCount);
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode subNode3 = new TreeNode(3, null, null);
        TreeNode node9 = new TreeNode(9, null, null);

        node1.left = node3;
        node1.right = node2;
        node3.left = node5;
        node3.right = subNode3;
        node2.right = node9;
        System.out.println(widthOfBinaryTree(node1));
    }
}
