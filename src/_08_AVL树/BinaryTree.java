package _08_AVL树;

import _00_utils.printer.BinaryTreeInfo;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;
    protected static class Node<E> {
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
            return left == null && right == null;
        }

        boolean hasTwoChildren() {
            return left != null && right != null;
        }

        boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        boolean isRightChild() {
            return parent != null && this == parent.right;
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

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        root = null;
        size = 0;
    }

    // 二叉树的遍历：
    // 1.前序遍历（Preorder Traversal),   顺序：根节点、前序遍历左子树、前序遍历右子树
    // 2.中序遍历（Inorder Traversal),    顺序：中序遍历左子树、根节点、中序遍历右子, 二叉搜索树的中序遍历结果是升序或者降序的
    // 3.后序遍历（Postorder Traversal)   顺序：后序遍历左子树、后序遍历右子树、根节
    // 4.层序遍历（Level Order Traversal) 顺序：从上到下、从左到右依次访问每一个节点

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

    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // 二叉树的前驱节点
    protected Node<E> predecessor(Node<E> node) {
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
    protected Node<E> successor(Node<E> node) {
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
