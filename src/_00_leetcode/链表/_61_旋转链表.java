package _00_leetcode.链表;


/**
 * https://leetcode-cn.com/problems/rotate-list/
 * 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数
 * 示例:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 */
public class _61_旋转链表 {

    /**
     * 官方的方法
     * 执行用时：0 ms
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight1(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        int size = 1;
        ListNode oldHead = head;
        while (oldHead.next != null) { oldHead = oldHead.next; ++size; }
        oldHead.next = head; // 闭环

        k %= size;
        k = size - k;
        while (k > 1) {
            head = head.next;
            k--;
        }
        ListNode newHead = head.next; // 保存新的头节点
        head.next = null; // 断开之前的
        return newHead;
    }

    /**
     * 自己写的
     * 遍历大法
     * 执行用时：1 ms (性能拉跨)
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight2(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;

        int size = 0;
        ListNode tmp = head;
        while (tmp != null) { tmp = tmp.next; size++; }
        if (k % size == 0) return head;
        int index = size - k % size;
        ListNode oldHead = head;
        ListNode newHead = null;
        ListNode prev = null;
        for (int i = 0; i < size; i++) {
            if (i == index - 1) { prev = head; }
            if (i == index) { newHead = head; }
            if (i == size -1) { head.next = oldHead; }
            head = head.next;
        }
        prev.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(10, null);
        ListNode node2 = new ListNode(9, node1);
        ListNode node3 = new ListNode(8, node2);
        ListNode node4 = new ListNode(7, node3);
        ListNode node5 = new ListNode(6, node4);
        ListNode node6 = new ListNode(5, node5);
        ListNode node7 = new ListNode(4, node6);
        ListNode node8 = new ListNode(3, node7);
        ListNode node9 = new ListNode(2, node8);
        ListNode node10 = new ListNode(1, node9);

        ListNode head = rotateRight2(node10, 2);
        ListNode.printList(head);
    }
}
