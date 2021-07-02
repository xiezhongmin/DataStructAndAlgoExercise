package _00_leetcode.链表;

import org.w3c.dom.NodeList;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * 反转一个单链表。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */

public class _206_反转链表 {
    /**
     * 递归
     * 结题思路：
     * 反转链表，我们只需要反转头节点以后的结点
     * 如图：https://pic.leetcode-cn.com/1614827364-aJftrz-2.png
     * 这样就满足递归的3大特征：
     * 复杂度：O(N)
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head; // 递归终止条件
        // 递归 递的过程  5->4->3->2
        ListNode newNode = reverseList1(head.next);
        // 递归 归的过程  2->3->4->5
        head.next.next = head; // 翻转操作
        head.next = null; // 清除之前指向
        return newNode;
    }

    /**
     * 遍历
     * 思路：假设链表为 3→2→1→∅,  我们想要把它改成 1→2→3→∅
     * 在遍历链表时，将 当前节点的 next 指针改为指向 前一个节点。
     * 在更改引用之前，还需要存储后一个节点。最后返回新的头引用。
     * 复杂度：O(N)
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next; // 记录当前节点的下个节点
            head.next = prev; // 翻转操作
            prev = head; // 前节点前进一位
            head = next; // 当前节点前进一位
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, node1);
        ListNode node3 = new ListNode(3, node2);
        ListNode node4 = new ListNode(4, node3);
        ListNode node5 = new ListNode(5, node4);
        ListNode head = reverseList2(node5);
        ListNode.printList(head);
    }
}
