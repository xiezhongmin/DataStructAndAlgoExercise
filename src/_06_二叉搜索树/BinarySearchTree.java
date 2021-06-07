package _06_二叉搜索树;

import _00_utils.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

        // 是否是叶子节点
        boolean isLeaf() {
            return this.left == null && this.right == null;
        }
    }

    public static abstract class Visitor<E> {
        boolean stop;

        /**
         * @param element
         * @return 如果返回true，就代表停止遍历
         */
        public abstract boolean visit(E element);
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
        root = null;
        size = 0;
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

    // 二叉树的遍历：
    // 1.前序遍历（Preorder Traversal),   顺序：根节点、前序遍历左子树、前序遍历右子树
    // 2.中序遍历（Inorder Traversal),    顺序：中序遍历左子树、根节点、中序遍历右子, 二叉搜索树的中序遍历结果是升序或者降序的
    // 3.后序遍历（Postorder Traversal)   顺序：后序遍历左子树、后序遍历右子树、根节
    // 4.层序遍历（Level Order Traversal) 顺序：从上到下、从左到右依次访问每一个节点

    // ----------------------------------- 非递归 -----------------------------------

    // 1.前序遍历（Preorder Traversal)
    public void preorder() {
        if (root == null) return;

        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            System.out.print(node.element + " ");
            if (node.right != null) { stack.push(node.right); }
            if (node.left != null) { stack.push(node.left); }
        }
    }

    // 2.中序遍历（Inorder Traversal)
    public void inorder() {
        if (root == null) return;

        Stack<Node<E>> stack = new Stack<>();
        Node<E> node = root;

        do {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty()) break;
                node = stack.pop();
                System.out.print(node.element + " ");
                node = node.right;
            }
        } while (true);
    }

    // 3.后序遍历（Postorder Traversal)
    public void postorder() {
        if (root == null) return;

        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);

        Node<E> last = null;
        while (!stack.isEmpty()) {
            Node<E> node = stack.peek();
            if (node.isLeaf()
               || (last != null && (last == node.left || last == node.right))) {
                // 如果栈顶节点是叶子节点 或者 上一次访问的节点是栈顶节点的子节点
                // 弹出栈顶节点，进行访问
                node = stack.pop();
                System.out.print(node.element + " ");
            } else {
                // 否则
                // 将栈顶节点的right、left按顺序入栈
                if (node.right != null) { stack.push(node.right); }
                if (node.left != null) { stack.push(node.left); }
            }
            last = node;
        }
    }

    // ----------------------------------- 递归 -----------------------------------

    // 1.前序遍历（Preorder Traversal)
    public void preorder(Visitor<E> visitor) {
        if (visitor == null) return;
        preorder(root, visitor);
    }

    public void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    // 2.中序遍历（Inorder Traversal)
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) return;
        inorder(root, visitor);
    }

    public void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        inorder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    // 3.后序遍历（Postorder Traversal)
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) return;
        postorder(root, visitor);
    }

    public void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        postorder(node.left, visitor);
        postorder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    // 4.层序遍历（Level Order Traversal)
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;
        // 思路:
        // 1.将根节点入队
        // 2.循环一下操作直到队列为空：
        // 将队头节点 A 出队，进行访问
        // 将 A 的左子节点入队
        // 将 A 的右子节点入队
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;
            if (node.left != null) { queue.offer(node.left); }
            if (node.right != null) { queue.offer(node.right); }
        }
    }

    // 是否是完全二叉树
    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) return false;

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) { // node.left == null && node.right != null
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else { // node.right == null && (node.left != null || node.left == null) 剩下的必须是叶子节点
                leaf = true;
            }
        }

        return true;
    }

    // 递归法
//    public int height() {
//        return height(root);
//    }
//
//    private int height(Node<E> node) {
//        if (node == null) return 0;
//        return 1 + Math.max(height(node.left), height(node.right));
//    }

    // 迭代法
    public int height() {
        if (root == null) return 0;

        int height = 0; // 树的高度
        int levelSize = 1; // 存储着每一层的元素数量
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            if (node.left != null) { queue.offer(node.left); }
            if (node.right != null) { queue.offer(node.right); }
            if (levelSize == 0) { // 意味着即将要访问下一层
                levelSize = queue.size();
                height++;
            }
        }

        return height;
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

    // 二叉树的前驱节点
    private Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        // 如果 node 的 左子树有值
        // 则它的前驱节点就等于：它的左子树的最大的节点（最右的节点）
        if (node.left != null) {
            Node<E> p = node.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 进来这里即左子树为空
        // 则它的前驱节点等于：它的父节点满足（它的父节点有值并且是父节点的右子节点）
        while (node.parent != null && node != node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    // 后继节点
    private Node<E> successor(Node<E> node) {
        if (node == null) return null;

        // 如果 node 的 右子树有值
        // 则它的后驱节点就等于：它的右子树的最小的节点（最左的节点）
        if (node.right != null) {
            Node<E> p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 进来这里即右子树为空
        // 则它的后驱节点等于：它的父节点满足（它的父节点有值并且是父节点的左子节点）
        while (node.parent != null && node != node.parent.left) {
            node = node.parent;
        }

        return node.parent;
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
