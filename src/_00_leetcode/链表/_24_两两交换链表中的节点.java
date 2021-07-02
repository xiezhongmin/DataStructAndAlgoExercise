package _00_leetcode.链表;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 */
public class _24_两两交换链表中的节点 {
    /**
     * 官方：递归解法
     * 参考：https://lyl0724.github.io/2020/01/25/1/
     * @param head
     * @return
     */
    public static ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) return head; // 终止条件：链表只剩一个节点或者没节点了，没得交换了。返回的是已经处理好的链表
        // [1,2,3,4]
        // 一共三个节点:head, next, swapPairs(next.next)
        // 下面的任务便是交换这3个节点中的前两个节点
        ListNode next = head.next;
        head.next = swapPairs1(next.next); // 将其余节点进行两两交换 (当前节点的next为交换后的头结点)
        next.next = head; // 本级递归操作
        return next; // 根据第二步：返回给上一级的是当前已经完成交换后，即处理好了的链表部分
    }

    /**
     * 官方：迭代
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode temp = dummyNode; // temp 表示当前到达的节点
        // [1,2,3,4]
        while (temp.next != null && temp.next.next != null) { // 只有当temp指向的结点之后有两个结点时才需要交换
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2; // 衔接
            node1.next = node2.next;
            node2.next = node1;
            temp = node1; // temp指向交换后的结点的第二个结点，即未交换结点的前一个结点
        }

        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4, null);
        ListNode node2 = new ListNode(3, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(1, node3);

        ListNode head = swapPairs2(node4);
        ListNode.printList(head);
    }
}
