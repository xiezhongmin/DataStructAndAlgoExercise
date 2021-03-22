package _06_二叉搜索树;

import _00_utils.printer.BinaryTreeInfo;

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
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node root;
    private Comparator comparator;
    
    private class Node<E> {
        E element;
        Node parent;
        Node left;
        Node right;

        Node(E element, Node parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    // 默认构造方法
    public BinarySearchTree() {
        this(null);
    }

    // 构造方法 + 比较器
    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);

        if (root == null) { // 第一次添加
            root = new Node(element, null);
        } else { // 后续添加
            // 思路步骤：
            // 1.找到父节点 parent
            // 2.创建新节点 node
            // 3.parent.left = node 或者 parent.right = node

            int cmp = 0;
            Node node = root;
            Node parent = root;
            while (node != null) {
                cmp = compare(element, (E)node.element); // 保存比较结果
                parent = node; // 保存父节点

                if (cmp > 0) { // 如果比父节点大，继续往右边找
                    node = node.right;
                } else if (cmp < 0) { // 如果比父节点小，继续往左边找
                    node = node.left;
                } else { // 相等
                    node.element = element; // 覆盖
                    return;
                }
            }

            if (cmp > 0) parent.right = new Node(element, parent); // 大的放右边
            else parent.left = new Node(element, parent); // 小的放左边
        }
        size++;
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node)node).right;
    }

    @Override
    public Object string(Object node) {
        String parentStr = ((Node)node).parent == null ? "null" : ((Node)node).parent.element.toString();
        return  ((Node)node).element + "(p:" + parentStr + ")";
    }
}
