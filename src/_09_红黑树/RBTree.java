package _09_红黑树;

import java.util.Comparator;

public class RBTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparable<E> comparator) {
        super((Comparable<E>) comparator);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String colorStr = color == RED ? "-" + "(R)" : "";
            String parentStr = parent == null ? "null" : parent.element.toString();
            return element + "(p:" + parentStr + ")" + colorStr;
        }
    }

    @Override
    protected void addAfter(Node<E> node) {
        Node<E> parent = node.parent;

        // 如果是根节点 -> 染黑色
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = red(parent.parent);

        if (isRed(uncle)) { // 叔父节点是红色【B树上溢】
            // 父节点、叔父节点染黑色
            black(parent);
            black(uncle);
            addAfter(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLift(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isRightChild()) { // RR
                black(parent);
            } else { // RL
                black(node);
                rotateRight(parent);
            }
            rotateLift(grand);
        }
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return null;
        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }
}
