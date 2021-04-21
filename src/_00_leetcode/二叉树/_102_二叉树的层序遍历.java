package _00_leetcode.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层序遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class _102_二叉树的层序遍历 {

    /**
     * 遍历
     * 执行用时：1 ms, 在所有 Java 提交中击败了 94.61%
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了 40.10% 的用户
     *
     */
    static public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1; // 存储着每一层的元素数量

        List<Integer> subList = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            subList.add(node.val);
            levelSize--;

            if (node.left != null) { queue.offer(node.left); }
            if (node.right != null) { queue.offer(node.right); }

            if (levelSize == 0) { // 即将访问下一层
                levelSize = queue.size();
                list.add(subList);
                subList = new ArrayList<>();
            }
        }

        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, null);
        TreeNode left = new TreeNode(2, null, null);
        TreeNode right = new TreeNode(3, null, null);
        TreeNode subLeft = new TreeNode(4, null, null);
        TreeNode subRight = new TreeNode(5, null, null);
        right.left = subLeft;
        right.right = subRight;
        root.left = left;
        root.right = right;
        System.out.println(levelOrder(root));
    }
}
