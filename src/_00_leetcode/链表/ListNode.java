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

    void printList(ListNode head) {
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
