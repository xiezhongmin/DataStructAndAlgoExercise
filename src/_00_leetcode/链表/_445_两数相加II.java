package _00_leetcode.链表;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/add-two-numbers-ii/
 * 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。
 * 将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class _445_两数相加II {
    /**
     * 官方解法：
     * 利用两个栈，把所有数字压入栈中，再依次取出相加。计算过程中需要注意进位的情况。
     * 复杂度：O(max(m,n))，其中 m 与 n 分别为两个链表的长度。我们需要遍历每个链表。
     * 执行用时：5 ms
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode node = null;
        int ten = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || ten != 0) {
            int val1 = stack1.isEmpty() ? 0 : stack1.pop();
            int val2 = stack2.isEmpty() ? 0 : stack2.pop();
            int val = val1 + val2 + ten;
            ten = val / 10;
            val %= 10;
            ListNode newNode = new ListNode(val);
            newNode.next = node;
            node = newNode;
        }

        return node;
    }

    /**
     * 递归解法
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        return l2;
    }

    public static void main(String[] args) {
        // 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
        // 输出：7 -> 8 -> 0 -> 7
        ListNode node1 = new ListNode(3, null);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(7, node3);

        ListNode node5 =  new ListNode(4, null);
        ListNode node6  = new ListNode(6, node5);
        ListNode node7 = new ListNode(5, node6);

        ListNode head = addTwoNumbers1(node4, node7);
        head.printList(head);
    }
}
