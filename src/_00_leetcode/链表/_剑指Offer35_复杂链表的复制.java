package _00_leetcode.链表;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/
 * 复杂链表的复制
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，
 * 每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 * 示例 :
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 */
public class _剑指Offer35_复杂链表的复制 {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            return "[" +
                    val + ", " + (random == null ? "null" : random.val) +
                    ", @" + System.identityHashCode(this) +
                    ']';
        }

        void printList(Node head) {
            if (head == null) {
                System.out.println("head is null");
                return;
            }

            StringBuilder string = new StringBuilder();
            string.append("[");
            while (head != null) {
                if (head.next != null) string.append(head + ", ");
                else string.append(head);
                head = head.next;
            }
            string.append("]");
            System.out.println(string.toString());
        }
    }

    /**
     * 官方方法
     * 思路：
     * 1.利用HashMap，存储关系 (原始节点 : 新节点)
     * 2.赋值
     *
     * @param head
     * @return
     */
    public static Node copyRandomList1(Node head) {
        if (head == null) return null;

        // 1.存储关系 (原始节点 : 新节点)
        Node node = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        // 2.赋值
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }


    /**
     * 官方方法
     * 思路：
     * 1.在原链表每个节点后面加拷贝一个
     * 2.为当前链表的每一个新new节点的random属性赋值
     * 3.将当前链表，按照一个间隔一个的顺序拆分开
     *
     * @param head
     * @return
     */
    public static Node copyRandomList2(Node head) {
        if (head == null) return null;

        // 1.在原链表每个节点后面加拷贝一个
        Node currentNode = head;
        while (currentNode != null) {
            Node cloneNode = new Node(currentNode.val);
            cloneNode.next = currentNode.next;
            currentNode.next = cloneNode;
            currentNode = cloneNode.next;
        }

        // 2.为当前链表的每一个新new节点的random属性赋值
        currentNode = head;
        while (currentNode != null) {
            if (currentNode.random != null) currentNode.next.random = currentNode.random.next;
            currentNode = currentNode.next.next;
        }

        // 3.将当前链表，按照一个间隔一个的顺序拆分开
        currentNode = head;
        Node cloneHead = head.next;
        Node cloneNode = head.next;
        while (currentNode != null) {
            // 原链表的拆分
            currentNode.next = currentNode.next.next;
            currentNode = currentNode.next;

            // 克隆的拆分
            if (cloneNode.next != null) {
                cloneNode.next = cloneNode.next.next;
                cloneNode = cloneNode.next;
            }
        }

        return cloneHead;
    }

    public static void main(String[] args) {
        // [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        node1.printList(node1);

        Node head = copyRandomList1(node1);
        head.printList(head);
    }
}
