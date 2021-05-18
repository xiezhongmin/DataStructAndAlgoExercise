package _09_红黑树;

import java.util.Comparator;

public class AVLTree<E> extends BBST<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparable<E> comparator) {
        super((Comparable<E>) comparator);
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

        @Override
        public String toString() {
            String parentStr = parent == null ? "null" : parent.element.toString();
            return element + "(p:" + parentStr + ")" + "-" + "(h:" + height + ")";
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

    @Override
    protected void removeAfter(Node<E> node, Node<E> replacement) {
        // 1.循环往上找到失衡的父元素
        // 2.判断平横因子，是否失去平衡
        while ((node = node.parent) != null) {
            if (isBalanced(node)) { // 平衡
                updateHeight(node);
            } else { // 失衡
                // 3.修复平衡
                rebalanced2(node);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);

        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        super.rotate(r, b, c, d, e, f);

        // 更新高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
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
     * 修复平衡：方法一（通过判断左旋、右旋）
     * @param grand 旋转的节点
     */
    private void rebalanced(Node<E> grand) {
        // 1.判断是哪种失衡 （LL、 RR、 LR、 RL ）
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL 右旋
                rotateRight(grand);
            } else { // LR 左旋 右旋
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isRightChild()) { // RR 左旋
                rotateLeft(grand);
            } else { // RL 右旋 左旋
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    /**
     * 修复平衡：方法二（统一旋转）
     * @param grand 旋转的节点
     */
    private void rebalanced2(Node<E> grand) {
        // 1.判断是哪种失衡 （LL、 RR、 LR、 RL ）
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL 右旋
                rotate(grand, node, node.right, parent,parent.right, grand);
            } else { // LR 左旋 右旋
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else { // R
            if (node.isRightChild()) { // RR 左旋
                rotate(grand, grand, parent.left, parent, node.left, node);
            } else { // RL 右旋 左旋
                rotate(grand, grand, node.left, node, node.right, parent);
            }
        }
    }
}
