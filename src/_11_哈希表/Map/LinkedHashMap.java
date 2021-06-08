package _11_哈希表.Map;

/**
 * 遍历时按照添加的顺序
 */
public class LinkedHashMap<K, V> extends HashMap<K, V> {
    LinkNode<K, V> first;
    LinkNode<K, V> last;

    private static class LinkNode<K, V> extends Node<K, V> {
        LinkNode<K, V> prev;
        LinkNode<K, V> next;

        LinkNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }

    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }

    @Override
    public void traversal(Visitor visitor) {
        if (visitor == null) return;

        LinkNode<K, V> node = first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) return;
            node = node.next;
        }
    }

    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        // 将链表串起来
        LinkNode<K, V> node = new LinkNode(key, value, parent);

        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }

        return node;
    }

    @Override
    protected void removeAfter(Node<K, V> willNode, Node<K, V> removeNode) {
        LinkNode<K, V> willLinkNode = (LinkNode<K, V>)willNode;
        LinkNode<K, V> removeLinkNode = (LinkNode<K, V>)removeNode;

        if (willLinkNode != removeLinkNode) { // 删除的是度为2的节点
            // 更换 node 与 前驱\后继节点 的连接位置

            // 交换 prev 位置
            LinkNode<K, V> tmp = willLinkNode.prev;
            willLinkNode.prev = removeLinkNode.prev;
            removeLinkNode.prev = tmp;

            // 更新 prev.next 的连接关系
            if (willLinkNode.prev == null) {
                first = willLinkNode;
            } else {
                willLinkNode.prev.next = willLinkNode;
            }
            if (removeLinkNode.prev == null) {
                first = removeLinkNode;
            } else {
                removeLinkNode.prev.next = removeLinkNode;
            }

            // 交换 next 位置
            tmp = willLinkNode.next;
            willLinkNode.next = removeLinkNode.next;
            removeLinkNode.next = tmp;

            // 更新 prev.next 的连接关系
            if (willLinkNode.next == null) {
                last = willLinkNode;
            } else {
                willLinkNode.next.prev = willLinkNode;
            }
            if (removeLinkNode.next == null) {
                last = removeLinkNode;
            } else {
                removeLinkNode.next.prev = removeLinkNode;
            }
        }

        // 清除删除节点的链表连接关系(prev/next)
        LinkNode<K, V> prev = removeLinkNode.prev;
        LinkNode<K, V> next = removeLinkNode.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }
    }
}
