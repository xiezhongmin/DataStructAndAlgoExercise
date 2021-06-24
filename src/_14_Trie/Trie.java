package _14_Trie;

import _11_哈希表.Map.HashMap;

/**
 * ◼ Trie 也叫做字典树、前缀树（Prefix Tree）、单词查找树
 * ◼ Trie 搜索字符串的效率主要跟字符串的长度有关
 */
public class Trie <V> {
    private int size;
    private Node<V> root;

    private static class Node<V> {
        Node<V> parent;
        Character character;
        HashMap<Character, Node<V>> children;
        V value;
        boolean word; // 是否为单词的结尾（是否为一个完整的单词）

        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public V get(String key) {
        Node<V> node = node(key);
        return node != null && node.word? node.value : null;
    }

    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.word;
    }

    public V add(String key, V value) {
        keyCheck(key);

        // 创建根节点
        if (root == null) {
            root = new Node<>(null);
        }

        // 扫描
        int len = key.length();
        Node<V> node = root;
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            boolean emptyChildren = node.children == null;
            Node<V> childNode = emptyChildren? null : node.children.get(c);
            if (childNode == null) {
                childNode = new Node<>(node);
                childNode.character = c;
                node.children = emptyChildren ? new HashMap<>() : node.children;
                node.children.put(c, childNode);
            }
            node = childNode;
        }

        // 已经存在这个单词
        if (node.word) {
            V oldValue = node.value;;
            node.value = value;
            return oldValue;
        }

        // 新增一个单词
        node.word = true;
        node.value = value;
        size++;
        return null;
    }

    public V remove(String key) {
        // 找到最后一个节点
        Node<V> node = node(key);

        // 如果不是单词结尾，不用作任何处理
        if (node == null || !node.word) {
            return null;
        }

        size --;

        V oldValue = node.value;

        // 如果还有子节点 -> 清除 value 值, 并设置 word = false
        if (node.children != null && !node.children.isEmpty()) {
            node.value = null;
            node.word = false;
            return oldValue;
        }

        // 没有子节点 -> 逐个删除子节点
        Node<V> parent = null;
        while ((parent = node.parent) != null) {
            parent.children.remove(node.character);
            // 如果 没有子节点 或者 父节点本身是单词结尾 就退出循环
            if (parent.word || !parent.children.isEmpty()) break;
            node = parent;
        }

        return oldValue;
    }

    public boolean startsWith(String prefix) {
        return node(prefix) != null;
    }

    private Node<V> node(String key) {
        keyCheck(key);

        int len = key.length();
        Node<V> node = root;
        for (int i = 0; i < len; i++) {
            if (node == null || node.children == null || node.children.isEmpty()) return null;
            char c = key.charAt(i);
            node = node.children.get(c);
        }

        return node;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not be empty");
        }
    }
}
