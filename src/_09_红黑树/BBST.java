package _09_红黑树;

import java.util.Comparator;

public class BBST<E> extends BST<E> {
    public BBST() {
        this(null);
    }

    public BBST(Comparable<E> comparator) {
        super((Comparator<E>) comparator);
    }

    /**
     * 统一旋转处理
     */
    protected void rotate(Node<E> r, // 原根节点
                        Node<E> b, Node<E> c,
                        Node<E> d, // 将成为的根节点
                        Node<E> e, Node<E> f) {
        // 1.更新d的父节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        // 2.处理：b-c
        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        // 3.处理：e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }

        // 4.处理：b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }

    /**
     * LL右旋
     */
    protected void rotateRight(Node<E> grand) {
        // 旋转
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * RR 左旋
     */
    protected void rotateLift(Node<E> grand) {
        // 旋转
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 更新父节点
        // 让 parent成为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()){
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        // 更新child 的父节点
        if (child != null) {
            child.parent = grand;
        }
        // 更新 grand 的父节点
        grand.parent = parent;
    }
}
