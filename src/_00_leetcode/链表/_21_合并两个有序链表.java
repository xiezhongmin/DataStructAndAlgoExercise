package _00_leetcode.链表;

/**
 *  https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *  合并两个有序链表:
 *  将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *  示例：
 *  输入：l1 = [1,2,4], l2 = [1,3,4]
 *  输出：[1,1,2,3,4,4]
 */
public class _21_合并两个有序链表 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // TUDO待完成

        return l1;
    }

    public static void main(String[] args) {
        ListNode node6 = new ListNode(4, null);
        ListNode node5 = new ListNode(3, node6);
        ListNode node4 = new ListNode(1, node5);

        ListNode node3 = new ListNode(4, null);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode head = mergeTwoLists(node1, node4);
        head.printList(head);
    }
}
