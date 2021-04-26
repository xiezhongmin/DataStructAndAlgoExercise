package _00_leetcode.二叉树;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 * 二叉树最大宽度
 *
 * 示例 1:
 * 输入:
 *
 *        1
 *      /   \
 *     3     2
 *    / \     \
 *   5   3     9
 *
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5, 3, null 9)。
 */
public class _662_二叉树最大宽度 {
    static class AnnotatedNode {
        TreeNode node;
        int pos = 0, depth = 0;

        AnnotatedNode(TreeNode node, int d, int p) {
            this.node = node;
            this.depth = d;
            this.pos = p;
        }
    }

    /**
     * 方法一：宽度优先搜索
     * 宽度优先搜索：顺序遍历每个节点的过程中，我们记录节点的 position 信息，
     * 对于每一个深度，第一个遇到的节点是最左边的节点，最后一个到达的节点是最右边的节点,
     * (最右边的节点 减 最左边的节点 + 1) 每次取出最大值就是我们要的答案
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了 99.73% 的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了83.84% 的用户
     */
    static public int widthOfBinaryTree1(TreeNode root) {
        Queue<AnnotatedNode> queue = new LinkedList<>();
        queue.offer(new AnnotatedNode(root, 0, 0));
        int ans = 0, curDepth = 0, left = 0;
        while (!queue.isEmpty()) {
            AnnotatedNode a = queue.poll();

            if (a.node.left != null) {
                queue.offer(new AnnotatedNode(a.node.left, a.depth + 1, a.pos * 2));
            }

            if (a.node.right != null) {
                queue.offer(new AnnotatedNode(a.node.right, a.depth + 1, a.pos * 2 + 1));
            }

            if (curDepth != a.depth) {
                curDepth = a.depth;
                left = a.pos;
            }

            ans = Math.max(ans, a.pos - left + 1);
        }

        return ans;
    }

    /**
     * 方法一：深度优先搜索
     * 深度优先搜索： 按照深度优先的顺序，我们记录每个节点的 position 。
     * 对于每一个深度，第一个到达的位置会被记录在 left[depth] 中。
     * 然后对于每一个节点，它对应这一层的可能宽度是 pos - left[depth] + 1 。
     * 我们将每一层这些可能的宽度取一个最大值就是答案。
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了 15.79% 的用户
     * 内存消耗：38MB, 在所有 Java 提交中击败了 45.20% 的用户
     */
    static int ans;
    static HashMap<Integer, Integer> left;
    static public int widthOfBinaryTree2(TreeNode root) {
        ans = 0;
        left = new HashMap();
        dfs(root, 0, 0);
        return ans;
    }

    static void dfs(TreeNode node, int depth, int pos) {
        if (node == null) return;
        // 当前深度没有存储过位置，则存储 (相当于每次存储最左边的位置)
        left.computeIfAbsent(depth, x -> pos);
        // 宽度取一个最大值
        ans = Math.max(ans, pos - left.get(depth) + 1);
        dfs(node.left, depth + 1, pos * 2);
        dfs(node.right, depth + 1, pos * 2 + 1);
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
        System.out.println(widthOfBinaryTree2(node1));
    }
}
