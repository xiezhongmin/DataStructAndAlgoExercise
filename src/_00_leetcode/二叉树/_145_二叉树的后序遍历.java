package _00_leetcode.二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * 给你二叉树的根节点 root ，返回它节点值的 后序 遍历。
 * 示例：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 */
public class _145_二叉树的后序遍历 {
    /**
     * 递归
     * 耗时：0 ms
     */
    static List<Integer> list = new ArrayList<>();
    static public List<Integer> postorderTraversal1(TreeNode root) {
        if (root == null) return list;

        postorderTraversal1(root.left);
        postorderTraversal1(root.right);
        list.add(root.val);
        return list;
    }

    /**
     * 遍历
     * 耗时：1 ms
     */
    static public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode lastNode = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if ((node.right == null && node.left == null)
                || (lastNode != null && (node.left == lastNode || node.right == lastNode))) {
                // 如果栈顶节点是叶子节点 或者 栈顶节点是上次节点的父节点
                // 弹出栈顶节点，进行访问
                node = stack.pop();
                list.add(node.val);
            } else {
                // 否则按顺序入栈
                if (node.right != null) { stack.push(node.right); }
                if (node.left != null) { stack.push(node.left); }
            }
            lastNode = node;
        }

        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, null);
        TreeNode left = new TreeNode(2, null, null);
        TreeNode right = new TreeNode(3, null, null);
        root.left = left;
        root.right = right;

        System.out.println(postorderTraversal1(root));
        System.out.println(postorderTraversal2(root));
    }
}
