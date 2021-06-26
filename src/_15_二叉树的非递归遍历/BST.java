package _15_二叉树的非递归遍历;

import java.util.Comparator;

/**
 * 二叉搜索树是二叉树的一种，是应用非常广泛的一种二叉树，英文简称为BST
 * 特征：
 * 1.任意一个节点的值都大于其左子树所有节点的值
 * 2.任意一个节点的值都小于其右子树所有节点的值
 * 3.它的左右子树也是一棵二叉搜索树
 * 优势:
 * 二叉搜索树可以大大提高搜索数据的效率
 * 必要条件:
 * 二叉搜索树存储的元素必须具备可比较性:
 * 1.比如int、double 等
 * 2.如果是自定义类型，需要指定比较方式
 * 3.不允许为null
 */
public class BST<E> extends BinaryTree<E> {
    private Comparator<E> comparator;

    // 默认构造方法
    public BST() {
        this(null);
    }

    // 构造方法 + 比较器
    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        elementNotNullCheck(element);

        if (root == null) { // 首次添加
            root = new Node<E>(element, null);
            size++;
            return;
        }

        // 后续添加
        // 思路步骤：
        // 1.找到父节点 parent
        // 2.创建新节点 node
        // 3.parent.left = node 或者 parent.right = node
        int cmp = 0;
        Node<E> node = root;
        Node<E> parent = root;
        do {
            cmp = compare(element, node.element); // 保存比较结果
            parent = node; // 保存父节点

            if (cmp > 0) { // 如果比父节点大，继续往右边找
                node = node.right;
            } else if (cmp < 0) { // 如果比父节点小，继续往左边找
                node = node.left;
            } else { // 相等
                node.element = element; // 覆盖
                return;
            }
        } while (node != null);

        if (cmp > 0) {
            parent.right = new Node<E>(element, parent); // 大的放右边
        } else {
            parent.left = new Node<E>(element, parent); // 小的放左边
        }

        size++;
    }

    public void remove(E element) {
        remove(node(element));
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    private Node<E> node(E element) {
        Node<E> node = root;

        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return null;
    }

    private void remove(Node<E> node) {
        if (node == null) return;

        size--;

        // 1.先处理删除度为2的节点
        if (node.left != null && node.right != null) {
            // 2.找到它的前驱节点或者后继结点
            Node<E> s = successor(node);
            // 3.它的前驱节点或者后继结点的值覆盖当前节点的值
            node.element = s.element;
            // 4.保存前驱节点或者后继结点，等待后面删除
            node = s;
        }

        // 5.来到这里即删除度为0或者度1的节点
        Node<E> replacement = node.left != null? node.left : node.right;

        if (replacement != null) { // 度为1的节点
            replacement.parent = node.parent;

            if (node.parent == null) { // 是 root 节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;
        } else { // node是叶子节点并且不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    private int compare(E e1, E e2) {
        if (comparator != null) { // 外界传入一个 Comparator 自定义比较方案
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2); // 如果没有传入Comparator，强制认定元素实现了 Comparable 接口
    }

    protected void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
