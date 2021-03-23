package _06_二叉搜索树;

import _00_utils.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
    private Node<E> root;
    private Comparator<E> comparator;

    private static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    // 默认构造方法
    public BinarySearchTree() {
        this(null);
    }

    // 构造方法 + 比较器
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);

        if (root == null) { // 第一次添加
            root = new Node<E>(element, null);
        } else { // 后续添加
            // 思路步骤：
            // 1.找到父节点 parent
            // 2.创建新节点 node
            // 3.parent.left = node 或者 parent.right = node

            int cmp = 0;
            Node<E> node = root;
            Node<E> parent = root;
            while (node != null) {
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
            }

            if (cmp > 0) parent.right = new Node<E>(element, parent); // 大的放右边
            else parent.left = new Node<E>(element, parent); // 小的放左边
        }
        size++;
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
    }

    // 二叉树的遍历：
    // 1.前序遍历（Preorder Traversal),   顺序：根节点、前序遍历左子树、前序遍历右子树
    // 2.中序遍历（Inorder Traversal),    顺序：中序遍历左子树、根节点、中序遍历右子, 二叉搜索树的中序遍历结果是升序或者降序的
    // 3.后序遍历（Postorder Traversal)   顺序：后序遍历左子树、后序遍历右子树、根节
    // 4.层序遍历（Level Order Traversal) 顺序：从上到下、从左到右依次访问每一个节点

    // 1.前序遍历（Preorder Traversal)
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    public void preorderTraversal(Node<E> node) {
        if (node == null) return;

        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    // 2.中序遍历（Inorder Traversal)
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    public void inorderTraversal(Node<E> node) {
        if (node == null) return;

        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    // 3.后序遍历（Postorder Traversal)
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    public void postorderTraversal(Node<E> node) {
        if (node == null) return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    // 4.层序遍历（Level Order Traversal)
    public void levelOrderTraversal() {
        if (size == 0) return;

        // 思路
        // 1.将根节点入队
        // 2.循环一下操作直到队列为空：
        // 将队头节点 A 出队，进行访问
        // 将 A 的左子节点入队
        // 将 A 的右子节点入队
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    private int compare(E e1, E e2) {
        if (comparator != null) { // 外界传入一个 Comparator 自定义比较方案
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2); // 如果没有传入Comparator，强制认定元素实现了 Comparable 接口
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
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        String parentStr = ((Node<E>)node).parent == null ? "null" : ((Node<E>)node).parent.element.toString();
        return  ((Node<E>)node).element + "(p:" + parentStr + ")";
    }
}
