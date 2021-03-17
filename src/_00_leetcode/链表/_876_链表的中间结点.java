package _00_leetcode.链表;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 */
public class _876_链表的中间结点 {
    /**
     * 自己思路：
     * 算出size  中间节点就是size/2
     * @param head
     * @return
     */
    public static ListNode middleNode1(ListNode head) {
        if (head == null || head.next == null) return head;
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size ++;
            curr = curr.next;
        }
        int index = size/2;
        for (int i = 0; i < index; i++) {
            head = head.next;
        }
        return head;
    }

    /**
     * 快慢指针（官方）
     * 思路：
     * 用两个指针 slow 与 fast 一起遍历链表。slow 一次走一步，fast 一次走两步。
     * 那么当 fast 到达链表的末尾时，slow 必然位于中间。
     * @param head
     * @return
     */
    public static ListNode middleNode2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head; // 慢指针
        ListNode fast = head; // 快指针
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(6, null);
        ListNode node2 = new ListNode(5, node1);
        ListNode node3 = new ListNode(4, node2);
        ListNode node4 = new ListNode(3, node3);
        ListNode node5 = new ListNode(2, node4);
        ListNode node6 = new ListNode(1, node5);
        ListNode head = middleNode2(node6);
        System.out.println(head.val);
    }
}
