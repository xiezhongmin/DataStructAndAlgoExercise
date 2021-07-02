package _00_leetcode.链表;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 */
public class _83_删除排序链表中的重复元素 {
    /**
     * 直接法（官方）
     * 思路：由于输入的列表已排序，因此我们可以通过将结点的值与它之后的结点进行比较来确定它是否为重复结点。
     * 如果它是重复的，我们更改当前结点的 next 指针就可以了
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) curr.next = curr.next.next;
            else curr = curr.next;
        }
        return head;
    }

    /**
     * 自己解法 (因为是排序链表所以不需要如此复杂)
     * 思路：
     * 利用 哨兵 + 哈希表来存储所有已经访问过的节点
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode sentinel = new ListNode(0); // 创建哨兵节点
        sentinel.next = head;
        ListNode prev = sentinel;
        Set<Integer> seen = new HashSet<>();
        while (prev.next != null) {
            if (!seen.add(prev.next.val)) prev.next = prev.next.next;
            else prev = prev.next;
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(5, null);
        ListNode node2 = new ListNode(5, node1);
        ListNode node3 = new ListNode(4, node2);
        ListNode node4 = new ListNode(3, node3);
        ListNode node5 = new ListNode(1, node4);
        ListNode node6 = new ListNode(1, node5);
        ListNode head = deleteDuplicates1(node6);
        ListNode.printList(head);
    }
}
