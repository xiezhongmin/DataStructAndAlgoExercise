package _00_leetcode.链表;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle-lcci/
 * 环路检测
 * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点
 */
public class _面试题0208_环路检测 {
    /**
     * 借助容量
     * 执行用时：4 ms 性能拉跨
     * @param head
     * @return
     */
    public static ListNode detectCycle1(ListNode head) {
        if (head == null || head.next == null) return null;

        Set<ListNode> seen = new HashSet<ListNode>();
        while (head != null) {
            if (!seen.add(head)) return head;
            head = head.next;
        }
        return null;
    }

    /**
     * 快慢指针
     * 执行用时： 0ms
     * 思路：
     * 假设 Y 为链表起点到环起点的距离, X 为快慢指针相遇点到环起点的距离
     * 相遇时：慢指针走了        slow = Y + X,
     * 快指针是慢指针的两倍，因此，fast = Y + X + NR(N圈) = 2 x (Y + X)
     * 那么 Y = NR - X
     * 结论：在相遇点的慢指针，会在（NR - X）相遇点到环起点的距离遇上环起点
     * @param head
     * @return
     */
    public static ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head; // 慢指针
        ListNode fast = head; // 快指针
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // 相遇
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(-4, null);
        ListNode node2 = new ListNode(0, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(3, node3);
        node1.next = node3;
        ListNode head = detectCycle2(node4);
        System.out.println(head.val);
    }
}
