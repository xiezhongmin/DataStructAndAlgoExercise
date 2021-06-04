package _11_哈希表.Map;

import _00_utils.printer.BinaryTreeInfo;
import _00_utils.printer.BinaryTrees;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 1.哈希冲突：
 *  1.2个不同的key 通过哈希函数生成相同的索引(index)
 *
 * 2.哈希函数：
 *  1. 先生成 key 的哈希值（必须是整数）
 *  2. 再让 key 的哈希值跟数组的大小进行相关运算, 生成一个索引值(index)
 *
 * hashCode() 与 equals 的关联：
 *  1.equals相等(表示同一个可以), 与之对应的hashCode()必定相等，当然也肯定会哈希冲突
 *  2.hashCode()相等, 不一定equals相等
 *  3.hashCode()相等, 必定哈希冲突
 *  4.哈希冲突，不一定hashCode()相等, 也不一定equals相等
 */

public class HashMap<K, V> implements Map<K, V> {
    private int size;
    private Node<K, V> table[];
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private static final int DEFAULT_CAPACITY = 1 << 4;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        table = new Node[capacity];
    }

    private static class Node<K, V> {
        K key;
        V value;
        int hash;
        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;
        boolean color = RED;

        Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.hash = key == null ? 0 : key.hashCode();
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

        Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }

        @Override
        public String toString() {
            return " k:" + key + " v:" + value + " ";
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) return;

        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }

        size = 0;
    }

    @Override
    public V put(K key, V value) {
        // 允许空值
        // keyNotNullCheck(key);

        int index = index(key);
        Node<K, V> root = table[index];
        if (root == null) { // 首次添加
            root = new Node(key, value, null);
            table[index] = root;
            size++;
            putAfter(root);
            return null;
        }

        // 后续添加
        // 思路步骤：
        // 1.找到父节点 parent
        // 2.创建新节点 node
        // 3.parent.left = node 或者 parent.right = node

        int cmp = 0;
        Node<K, V> node = root;
        Node<K, V> parent = root;
        K k1 = key;
        int h1 = k1 == null ? 0 : k1.hashCode();
        Node<K, V> result = null;
        boolean searched = false;
        do {
            parent = node; // 保存父节点
            K k2 = node.key;
            int h2 = k2 == null ? 0 : k2.hashCode();

            // 思路：
            // 1.先比较哈希值大小
            // 2.如果哈希值相等，判断equals是否相等
            // 3. 哈希值相等, equals不等，先看看是否本身实现Comparable比较接口
            // 4. 哈希值相等, equals不等, 不具备Comparable比较接口/或者比较结果为0，就只能扫描了
            // 5. 扫描结果存在 则返回扫描结果， 不存在, 则比较内存地址大小

            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
            } else if (searched) { // 已经扫描了
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else { // searched == false; 还没有扫描，然后再根据内存地址大小决定左右
                if (node.right != null && (result = node(node.right, k1)) != null
                        || node.left != null && (result = node(node.left, k1)) != null) { // 扫描
                    node = result;
                    cmp = 0;
                } else { // 已经扫描过，未找到, 则比较内存地址大小
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

            if (cmp > 0) { // 如果比父节点大，继续往右边找
                node = node.right;
            } else if (cmp < 0) { // 如果比父节点小，继续往左边找
                node = node.left;
            } else { // 相等
                node.key = key; // 覆盖
                V oldValve = node.value; // 保存旧值
                node.value = value; // 覆盖
                return oldValve;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        Node<K, V> newNode = new Node(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode; // 大的放右边
        } else {
            parent.left = newNode; // 小的放左边
        }

        size++;

        putAfter(newNode);

        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size == 0) return false;

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            Node<K, V> root = table[i];
            if (root == null) continue;

            queue.offer(root);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (Objects.equals(node.value, value)) return true;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return false;
    }

    @Override
    public void traversal(Visitor visitor) {
        if (size == 0 || visitor == null) return;

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            Node<K, V> root = table[i];
            if (root == null) continue;

            queue.offer(root);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) return;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    public void print() {
        if (size == 0) return;

        for (int i = 0; i < table.length; i++) {
            final Node<K, V> root = table[i];
            System.out.println("【index = " + i + "】");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K, V>) node).left;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K, V>) node).right;
                }

                @Override
                public Object string(Object node) {
                    return node;
                }
            });
            System.out.println("---------------------------------------------------");
        }
    }

    private V remove(Node<K, V> node) {
        if (node == null) return null;

        V oldValve = node.value;

        size--;

        // 1.先处理删除度为2的节点
        if (node.hasTwoChildren()) {
            // 2.找到它的前驱节点或者后继结点
            Node<K, V> p = successor(node);
            // 3.它的前驱节点或者后继结点的值覆盖当前节点的值
            node.key = p.key;
            node.value = p.value;
            // 4.保存前驱节点或者后继结点，等待后面删除
            node = p;
        }

        // 5.来到这里即删除度为0或者度1的节点
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);

        if (replacement != null) { // 度为1的节点
            replacement.parent = node.parent;

            if (node.parent == null) { // 是 root 节点
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }

            // 删除之后的处理
            removeAfter(node, replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;

            // 如果删除是根节点是不需要修复的
            // 删除之后的处理
//          removeAfter(node, null);
        } else { // node是叶子节点并且不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }

            // 删除之后的处理
            removeAfter(node, null);
        }

        return oldValve;
    }

    /**
     * 根据key生成对应的索引（在桶数组中的位置）
     *
     * @param key key
     * @return 索引
     */
    private int index(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        return (hash ^ (hash >>> 16)) & (table.length - 1);
    }

    /**
     * 根据node生成对应的索引（在桶数组中的位置）
     *
     * @param node node
     * @return 索引
     */
    private int index(Node<K, V> node) {
        return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
    }

    // ---------------------------------------   二叉树相关   ---------------------------------------

    // 二叉树的前驱节点
    private Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) return null;

        // 如果 node 的 左子树有值
        // 则它的前驱节点就等于：它的左子树的最大的节点（最右的节点）
        if (node.left != null) {
            Node<K, V> p = node.left;
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
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) return null;

        // 如果 node 的 右子树有值
        // 则它的后驱节点就等于：它的右子树的最小的节点（最左的节点）
        if (node.right != null) {
            Node<K, V> p = node.right;
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

    private Node<K, V> node(K key) {
        Node<K, V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    private Node<K, V> node(Node<K, V> node, K key) {
        // 思路:
        // 1.先比较哈希值大小
        // 2.哈希值相等则比较，equals是否相等
        // 3.哈希值相等, equals不等，先看看是否本身实现Comparable比较接口
        // 4.以上都不满足，只能扫描了

        int cmp = 0;
        K k1 = key;
        int h1 = k1 == null ? 0 : k1.hashCode();
        Node<K, V> result = null;
        while (node != null) {
            K k2 = node.key;
            int h2 = k2 == null ? 0 : k2.hashCode();

            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right, k1)) != null) { // 扫描右边
                return result;
            } else { // 扫描左边
                node = node.left;
            }
        }

        return null;
    }

    protected void keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }


    // ---------------------------------------   红黑树相关   ---------------------------------------

    // 添加后的修复红黑树性质
    private void putAfter(Node<K, V> node) {
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

        Node<K, V> parent = node.parent;

        // 如果是根节点 -> 染黑色
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父节点
        Node<K, V> uncle = parent.sibling();
        // 祖父节点
        Node<K, V> grand = red(parent.parent);

        if (isRed(uncle)) { // 叔父节点是红色【B树上溢】
            // 父节点、叔父节点染黑色
            black(parent);
            black(uncle);
            putAfter(grand);
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

    // 删除后的修复红黑树性质
    private void removeAfter(Node<K, V> node, Node<K, V> replacement) {
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
        Node<K, V> parent = node.parent;
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
        Node<K, V> sibling = left ? parent.right : parent.left;
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

    /**
     * LL右旋
     */
    protected void rotateRight(Node<K, V> grand) {
        // 旋转
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * RR 左旋
     */
    protected void rotateLeft(Node<K, V> grand) {
        // 旋转
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        // 更新父节点
        // 让 parent成为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            table[index(grand)] = parent;
        }
        // 更新child 的父节点
        if (child != null) {
            child.parent = grand;
        }
        // 更新 grand 的父节点
        grand.parent = parent;
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return null;
        node.color = color;
        return node;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }
}
