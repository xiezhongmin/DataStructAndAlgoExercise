package _00_leetcode.链表;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 移除链表元素:
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
public class _203_移除链表元素 {
    public static ListNode removeElements1(ListNode head, int val) {
        // 创建虚拟节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) prev.next = prev.next.next;
            else prev = prev.next;
        }

        return dummyHead.next;
    }

    public static ListNode removeElements2(ListNode head, int val) {
        ListNode original = head;
        ListNode prev = null;
        while (head != null) {
            if (head.val == val) {
                if (prev == null) original = head.next; // 处理头节点相等
                else prev.next = prev.next.next; // 处理其它节点相等
            }
            prev = head;
            head = head.next;
        }
        return original;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(6, null);
        ListNode node2 = new ListNode(5, node1);
        ListNode node3 = new ListNode(4, node2);
        ListNode node4 = new ListNode(3, node3);
        ListNode node5 = new ListNode(2, node4);
        ListNode node6 = new ListNode(6, node5);
        ListNode head = removeElements2(node6, 6);
        head.printList(head);
    }
}
