package _08_AVL树;

import java.util.Comparator;

public class AVLTree<E> extends BST<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparable<E> comparator) {
        super((Comparator<E>) comparator);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1; // 默认值是1，因为新添加的node高度必然是1

        AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 平衡因子
         */
        int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) this.left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) this.right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新高度
         */
        void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) this.left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) this.right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        /**
         * 返回高度比较高的子节点
         */
        Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) this.left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) this.right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            // 如果相等 返回父元素同方向的元素
            return isLeftChild() ? left : right;
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode(element, parent);
    }

    /**
     * 添加之后的修复
     */
    @Override
    protected void addAfter(Node<E> node) {
        // 1.循环往上找到失衡的父元素
        // 2.判断平横因子，是否失去平衡
        while ((node = node.parent) != null) {
            if (isBalanced(node)) { // 平衡
                updateHeight(node);
            } else { // 失衡
                // 3.修复平衡
                rebalanced(node);
                // 只需修复一次，整棵树都会平衡
                break;
            }
        }
    }

    /**
     * 是否是平衡
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 更新高度
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    /**
     * LL右旋
     */
    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;

        // 1.旋转
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        // 2.更新父节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()){
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        grand.parent = parent;
        if (child != null) {
            child.parent = grand;
        }

        // 3.跟新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * RR 左旋
     */
    private void rotateLift(Node<E> grand) {
        Node<E> parent = grand.right;

        // 1.旋转
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        // 2.更新父节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()){
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        grand.parent = parent;
        if (child != null) {
            child.parent = grand;
        }

        // 3.更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    private void rebalanced(Node<E> grand) {
        // 1.判断是哪种失衡 （LL、 RR、 LR、 RL ）
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL 右旋
                rotateRight(grand);
            } else { // LR 左旋 右旋
                rotateLift(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isRightChild()) { // RR 左旋
                rotateLift(grand);
            } else { // RL 右旋 左旋
                rotateRight(parent);
                rotateLift(grand);
            }
        }
    }

    public Object string(Object node) {
        String parentStr = ((Node<E>) node).parent == null ? "null" : ((Node<E>) node).parent.element.toString();
        return ((Node<E>) node).element + "(p:" + parentStr + ")" + "-" + "(h:" + ((AVLNode<E>) node).height + ")";
    }
}
