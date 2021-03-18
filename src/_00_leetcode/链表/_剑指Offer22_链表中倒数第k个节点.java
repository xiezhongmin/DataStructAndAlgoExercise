package _00_leetcode.链表;

/**
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 * 链表中倒数第k个节点
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 */
public class _剑指Offer22_链表中倒数第k个节点 {
    /**
     * 快慢指针 + 虚拟头节点
     * @param head
     * @param k
     * @return
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode slow = dummyHead;
        ListNode fast = dummyHead;

        while (k-- > 0) { // 快指针先走k步
            fast = fast.next;
        }

        while (fast != null && fast.next != null) { // 快指针到底，则慢指针就是前一个节点
            fast = fast.next;
            slow = slow.next;
        }

        return slow.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(5, null);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(3, node2);
        ListNode node4 = new ListNode(2, node3);
        ListNode node5 = new ListNode(1, node4);

        ListNode head = getKthFromEnd(node5, 2);
        head.printList(head);
    }
}
