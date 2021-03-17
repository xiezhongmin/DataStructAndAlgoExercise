package _00_leetcode.链表;

/**
 * https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/
 * 返回倒数第k个节点
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 */
public class _面试题0202_返回倒数第k个节点 {
    // 遍历大法
    public static int kthToLast1(ListNode head, int k) {
        int size = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            size++;
        }

        for (int i = 0; i < size - k ; i++) {
            head = head.next;
        }
        return head.val;
    }

    // 递归解法
    static int count = 0;
    public static int kthToLast2(ListNode head, int k) {
        if (head == null) return -1;
        int val = kthToLast2(head.next, k);
        count++;
        if (count == k) { return head.val; }
        return val;
    }

    // 快慢指针
    public static int kthToLast3(ListNode head, int k) {
        if (head.next == null) return head.val;
        ListNode slow = head; // 慢指针
        ListNode fast = head; // 快指针
        // 快指针先走k步
        while (k-- > 1) { fast = fast.next; }
        // 快指针 与 慢指针同时走， 当快指针到达终点时 慢指针的值就是返回的值
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.val;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(5, null);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(3, node2);
        ListNode node4 = new ListNode(2, node3);
        ListNode node5 = new ListNode(1, node4);
        int val = kthToLast3(node5, 2);
        System.out.println(val);
    }
}
