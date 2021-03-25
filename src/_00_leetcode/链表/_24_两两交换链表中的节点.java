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
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        // 思路：
        // 如果链表中至少有两个节点，则在两两交换链表中的节点之后:
        // 原始链表的头节点变成新的链表的第二个节点，原始链表的第二个节点变成新的链表的头节点。
        // 链表中的其余节点的两两交换可以递归地实现。在对链表中的其余节点递归地两两交换之后，
        // 更新节点之间的指针关系，即可完成整个链表的两两交换。
        ListNode newHead = head.next; // newHead 表示新的链表的头节点 是原链表的下一个节点
        head.next = swapPairs(newHead.next); // 表示将其余节点进行两两交换 (其余新链表的头节点是原链表的下一个)
        //newHead.next = head; // 交换后的 新的头节点 为 head 的下一个节点
        return newHead; // 最后返回 新的链表的头节点 newHead。
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4, null);
        ListNode node2 = new ListNode(3, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(1, node3);

        ListNode head = swapPairs(node4);
        head.printList(head);
    }
}
