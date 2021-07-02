package _00_leetcode.链表;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 移除链表元素:
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
public class _203_移除链表元素 {

    /**
     * 思路：
     * 可以通过哨兵节点去解决它，哨兵节点广泛应用于树和链表中，如伪头、伪尾、标记等，它们是纯功能的，通常不保存任何数据，
     * 其主要目的是使链表标准化，如使链表永不为空、永不无头、简化插入和删除
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements1(ListNode head, int val) {
        ListNode sentinel = new ListNode(0); // 创建哨兵节点
        sentinel.next = head; // 设置哨兵节点为伪头节点

        ListNode prev = sentinel; // 创建前继节点为伪头节点
        while (prev.next != null) { // 判断当前结点的后继结点是否为null
            // 如果当前结点的后继结点的值与给定值val相等
            // 则需将其后继结点删除
            if (prev.next.val == val) prev.next = prev.next.next;
            // 否则，当前结点需要保留，因此prev向前移动一个位置
            else prev = prev.next;
        }

        return sentinel.next;
    }

    /**
     * 递归 (太难理解了)
     * @param head
     * @param val
     * @return
     */
    public static  ListNode removeElements2(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements2(head.next, val);
        if (head.val == val) return head.next;
        else return head;
    }

    /**
     * 自己的思路
     * 创建原始头保留头节点，创建前继节点处理要删除的节点
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements3(ListNode head, int val) {
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
        ListNode node6 = new ListNode(1, node5);
        ListNode node7 = new ListNode(6, node6);
        ListNode head = removeElements2(node7, 6);
        ListNode.printList(head);
    }
}
