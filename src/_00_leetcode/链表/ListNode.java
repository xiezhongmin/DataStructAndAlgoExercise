package _00_leetcode.链表;

/**
 * 公共代码：ListNode
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() { }

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static ListNode createListNode(int[] array) {
        if (array == null || array.length == 0) return null;

        ListNode sentinel = new ListNode(0); // 创建哨兵节点
        sentinel.next = new ListNode(array[0]); // 设置哨兵节点为伪头节点
        ListNode prev = sentinel; // 创建前继节点为伪头节点
        for (int i = 0; i < array.length; i++) {
            prev.next = new ListNode(array[i]);
            prev = prev.next;
        }

        return sentinel.next;
    }

    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("head is null");
            return;
        }

        StringBuilder string = new StringBuilder();
        string.append("[");
        while (head != null) {
            if (head.next != null) string.append(head.val + ",");
            else string.append(head.val);
            head = head.next;
        }
        string.append("]");
        System.out.println(string.toString());
    }
}
