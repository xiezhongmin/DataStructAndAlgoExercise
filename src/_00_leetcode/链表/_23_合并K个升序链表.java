package _00_leetcode.链表;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * 合并K个升序链表
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5], [1,3,4], [2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 */
public class _23_合并K个升序链表 {

    /**
     * 思路：顺序合并
     * 执行用时：121 ms, 在所有 Java 提交中击败了 24.90% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了 86.64% 的用户
     */
    public static ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode left = lists[0];
        for (int i = 1; i < lists.length; i++) {
            left = mergeTwoLists(left, lists[i]);
        }
        return left;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

    /**
     * 思路：利用归并合并思路
     * 执行用时： 2ms, 在所有 Java 提交中击败了 85.93% 的用户
     * 内存消耗： 40.2MB, 在所有 Java 提交中击败了 53.94% 的用户
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists, 0, lists.length);
    }

    public static ListNode merge(ListNode[] lists, int begin, int end) {
        if (end == begin) return null;
        if (end - begin == 1) return lists[begin];

        int mid = (begin + end) >> 1;
        return mergeTwoLists(merge(lists, begin, mid), merge(lists, mid, end));
    }

    public static void main(String[] args) {
        ListNode listNode1 = ListNode.createListNode(new int[]{1, 4, 5});
        ListNode listNode2 = ListNode.createListNode(new int[]{1, 3, 4});
        ListNode listNode3 = ListNode.createListNode(new int[]{2, 6});

        ListNode mergeNode = mergeKLists2(new ListNode[]{listNode1, listNode2, listNode3});
        ListNode.printList(mergeNode);
    }
}
