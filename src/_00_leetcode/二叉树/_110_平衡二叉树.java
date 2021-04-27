package _00_leetcode.二叉树;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 */
public class _110_平衡二叉树 {
    static public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    static public int height(TreeNode node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode node3 = new TreeNode(2, null, null);
        TreeNode node4 = new TreeNode(3, null, null);
        TreeNode node5 = new TreeNode(3, null, null);
        TreeNode node6 = new TreeNode(4, null, null);
        TreeNode node7 = new TreeNode(4, null, null);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node4.right = node7;
        System.out.println("是否是平衡二叉树：" + isBalanced(node1));
    }
}
