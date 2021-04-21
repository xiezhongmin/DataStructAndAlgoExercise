package _00_leetcode.二叉树;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * 示例：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 */
public class _144_二叉树的前序遍历 {
    /**
     * 递归
     * 耗时：0 ms
     */
    static List<Integer> list = new ArrayList<>();

    static public List<Integer> preorderTraversal1(TreeNode root) {
        if (root == null) return list;

        list.add(root.val);
        preorderTraversal1(root.left);
        preorderTraversal1(root.right);
        return list;
    }

    /**
     * 遍历
     * 耗时：1 ms
     */
    static public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, null);
        TreeNode left = new TreeNode(2, null, null);
        TreeNode right = new TreeNode(3, null, null);
        root.left = left;
        root.right = right;

        System.out.println(preorderTraversal2(root));
    }
}
