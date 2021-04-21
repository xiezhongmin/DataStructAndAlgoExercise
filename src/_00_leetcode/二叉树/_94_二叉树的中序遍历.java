package _00_leetcode.二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * 给你二叉树的根节点 root ，返回它节点值的 中序 遍历。
 * 示例：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 */
public class _94_二叉树的中序遍历 {
    /**
     * 递归
     * 耗时：0 ms
     */
    static List<Integer> list = new ArrayList<>();
    static public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null) return list;

        inorderTraversal1(root.left);
        list.add(root.val);
        inorderTraversal1(root.right);
        return list;
    }

    /**
     * 遍历
     * 耗时：1 ms
     */
    static public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        do {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty()) { break; }
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
        } while (true);

        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, null);
        TreeNode left = new TreeNode(2, null, null);
        TreeNode right = new TreeNode(3, null, null);
        root.left = left;
        root.right = right;

        System.out.println(inorderTraversal1(root));
        System.out.println(inorderTraversal2(root));
    }
}
