package _09_红黑树;

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
        // 添加思路：
        // 1.添加:(红黑树与4阶B树等价 元素个数满足 1 <= x <= 3) 必定是添加到叶子节点中, 为了尽快满足红黑树性质，新添加的元素默认为红色
        // 2.如果添加的是根节点，直接染黑即可
        // 3.添加总共分为12种情况： 在这些叶子节点中添加：（红）<- 黑 -> (红)   黑 -> (红)   (红) <- 黑   黑
        // -> 1.有 4 种情况满足红黑树的性质 parent 为黑色, 不需要处理
        // -> 2.其它 8 种情况不满足红黑树性质 parent 为红色：
        //    -> 1.uncle(叔父节点) 不是红色，这4种情况没有上溢，需要修复红黑树性质：(旋转 + 染色)
        //       -> 1.LL/RR情况：(单旋操作):
        //          -> 1. 染色： parent（父节点）染成 BLACK，grand（祖父节点） 染成 RED
        //          -> 2. 旋转： 对grand（祖父节点） LL右旋 / RR 左旋
        //       -> 2.LR/RL情况：(双旋):
        //          -> 1. 染色： 自己染成黑色， grand（祖父节点）染成红色
        //          -> 2. 双旋操作：
        //             ->1. LR情况：parent（父节点）左旋转， grand（叔父节点）右旋转
        //             ->2. RL情况：parent（父节点）右旋转， grand（叔父节点）左旋转
        //    -> 2.uncle(叔父节点) 是红色，这4种属于B树节点【上溢】情况: （祖父节点需要向上合并）
        //       -> 1. parent、 uncle 染成黑色
        //       -> 2. grand 染红色向上合并 当做是新添加的节点进行处理
        //       -> 3. 若上溢持续到根节点，只需将根节点染成 BLACK

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
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isRightChild()) { // RR
                black(parent);
            } else { // RL
                black(node);
                rotateRight(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void removeAfter(Node<E> node, Node<E> replacement) {
        // 删除思路：
        // 1.度为2的节点删除不需要处理
        // 2.红色节点删除不需要处理
        // 3.根节点删除不需要处理
        // 4.删除黑色节点分两种情况：
        //  -> 1.拥有一个红色子节点的黑色节点：
        //     -> 1.将替代的子节点染成 BLACK 即可保持红黑树性质
        //  -> 2.黑色叶子节点：(叶子节点被删除后，会导致B树节点下溢)：
        //       -> 1.兄弟节点是黑色：
        //          -> 1.兄弟节点至少有一个红色子节点：
        //             -> 1.旋转操作
        //             -> 2.旋转之后的中心节点继承 parent 的颜色
        //             -> 3.旋转之后的左右子节点染为黑色
        //          -> 2.兄弟节点没有红色子节点：
        //             -> 1.兄弟节点染成红色
        //             -> 2.父节点染成黑色
        //             -> 3.如果 parent 是黑色(会导致 parent 也下溢)
        //                -> 1.这时只需要把 parent 当做被删除的节点处理即可
        //       -> 2.兄弟节点是红色：
        //          -> 1.兄弟节点染成黑色，父节点染成红色
        //          -> 2.旋转处理
        //          -> 3.于是又回到兄弟节点是黑色的处理方式

        // 如果删除的节点是红色
        if (isRed(node)) return;

        // 删除的是根节点
        Node<E> parent = node.parent;
        if (parent == null) return;

        // 用以取代node的子节点是红色
        if (isRed(replacement)) {
            black(replacement);
            return;
        }

        // 删除的是黑色叶子节点 (叶子节点被删除后，会导致B树节点下溢)
        // 1.先判断黑色叶子节点是左边还是右边情况
        // 2.判断兄弟节点的颜色
        // 3.具体情况处理

        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) { // 要删除的黑色叶子节点是左边情况
            // 判断兄弟节点的颜色
            if (isRed(sibling)) { // 如果兄弟节点是红色
                black(sibling); // 兄弟节点染成黑色
                red(parent); // 父节点染成红色
                rotateLeft(parent); // 对父节点左旋操作
                sibling = parent.right; // 更新兄弟节点
            }

            // 进入兄弟节点是黑色情况处理
            // 判断兄弟节点是否有红色子节点
            if (isBlack(sibling.left) && isBlack(sibling.right)) { // 无红色子节点 (父节点要向下跟兄弟节点合并)
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) { // 如果 parent 是黑色(会导致 parent 也下溢)
                    removeAfter(parent, null); // 这时只需要把 parent 当做被删除的节点处理即可
                }
            } else { // 有红色子节点
                if (isBlack(sibling.right)) {
                    // 兄弟节点的右边是黑色，兄弟要先右旋转（因为是RL的情况，后面都是RR）
                    rotateRight(sibling); // 对父节点进行右旋操作
                    sibling = parent.right; // 更新兄弟节点
                }

                color(sibling, colorOf(parent)); // 旋转之后的中心节点继承 parent 的颜色
                black(sibling.right); // 旋转之后的左右子节点染为黑色
                black(parent); // 旋转之后的左右子节点染为黑色
                rotateLeft(parent); // 对父节点左旋操作
            }
        } else { // 要删除的黑色叶子节点是右边情况
            // 判断兄弟节点的颜色
            if (isRed(sibling)) { // 如果兄弟节点是红色
                black(sibling); // 兄弟节点染成黑色
                red(parent); // 父节点染成红色
                rotateRight(parent); // 对父节点右旋操作
                sibling = parent.left; // 更新兄弟节点
            }

            // 进入兄弟节点是黑色情况处理
            // 判断兄弟节点是否有红色子节点
            if (isBlack(sibling.left) && isBlack(sibling.right)) { // 无红色子节点 (父节点要向下跟兄弟节点合并)
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) { // 如果 parent 是黑色(会导致 parent 也下溢)
                    removeAfter(parent, null); // 这时只需要把 parent 当做被删除的节点处理即可
                }
            } else { // 有红色子节点
                if (isBlack(sibling.left)) {
                    // 兄弟节点的左边是黑色，兄弟要先左旋转（因为是LR的情况，后面都是LL）
                    rotateLeft(sibling); // 对父节点进行左旋操作
                    sibling = parent.left; // 更新兄弟节点
                }

                color(sibling, colorOf(parent)); // 旋转之后的中心节点继承 parent 的颜色
                black(sibling.left); // 旋转之后的左右子节点染为黑色
                black(parent); // 旋转之后的左右子节点染为黑色
                rotateRight(parent); // 对父节点右旋操作
            }
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
