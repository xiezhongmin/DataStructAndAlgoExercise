package _00_leetcode.链表;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 给定一个链表，判断链表中是否有环
 */
public class _141_环形链表 {
    /**
     * 结题思路：
     * 最容易想到的方法是遍历所有节点，每次遍历到一个节点时，判断该节点此前是否被访问过。
     * 具体地，我们可以使用哈希表来存储所有已经访问过的节点。
     * 每次我们到达一个节点，如果该节点已经存在于哈希表中，则说明该链表是环形链表，否则就将该节点加入哈希表中。
     * 重复这一过程，直到我们遍历完整个链表即可。
     * 复杂度：O(N)
     * @param head
     * @return
     */
    public static boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) return false;

        Set<ListNode> seen = new HashSet<ListNode>();
        while (head != null) {
            if (!seen.add(head)) return true;
            head = head.next;
        }

        return false;
    }

    /**
     * 结题思路：
     * 我们定义两个指针，一快一满。慢指针每次只移动一步，而快指针每次移动两步。
     * 初始时，慢指针在位置 head，而快指针在位置 head.next。
     * 这样一来，如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。
     * 否则快指针将到达链表尾部，该链表不为环形链表。
     * 复杂度：O(N)
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode flow = head; // 慢指针
        ListNode fast = head.next; // 快指针
        while (fast != null && fast.next != null) {
            flow = flow.next; // 慢指针前进一步
            fast = fast.next.next; // 快指针前进两步
            if (fast == flow) return true; // 相遇了，有环
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, node1);
        ListNode node3 = new ListNode(3, node2);
        ListNode node4 = new ListNode(4, node3);
        ListNode node5 = new ListNode(5, node4);
        node1.next = node5;
        boolean hasCycle = hasCycle2(node5);
        System.out.println(hasCycle);
    }
}
