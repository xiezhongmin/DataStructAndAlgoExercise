package _00_leetcode.链表;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * 合并两个有序链表:
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 */
public class _21_合并两个有序链表 {
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyHead = new ListNode(-1);
        ListNode prev = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return dummyHead.next;
    }


    public static void main(String[] args) {
        // 输入：l1 = [1,2,4], l2 = [1,3,4]
        // 输出：[1,1,2,3,4,4]
        ListNode node6 = new ListNode(4, null);
        ListNode node5 = new ListNode(7, null);
        ListNode node4 = new ListNode(5, node5);

        ListNode node3 = new ListNode(4, null);
        ListNode node2 = new ListNode(3, null);
        ListNode node1 = new ListNode(-9, node2);

        ListNode head = mergeTwoLists2(node1, node4);
        head.printList(head);
    }
}
