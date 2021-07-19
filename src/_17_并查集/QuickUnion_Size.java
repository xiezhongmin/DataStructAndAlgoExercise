package _17_并查集;

/**
 * 在Union的过程中，可能会出现树不平衡的情况，甚至退化成链表
 * Quick Union - 基于size的优化: 元素少的树 嫁接到 元素多的树
 */
public class QuickUnion_Size extends QuickUnion {
    private int[] sizes;

    /**
     * 构造方法
     * @param capacity 容量
     */
    public QuickUnion_Size(int capacity) {
        super(capacity);

        sizes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            sizes[i] = 1;
        }
    }

    /**
     * 基于size的优化
     * 将 元素少的树的根节点 嫁接到 元素多的树的根节点 上
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        if (sizes[p1] < sizes[p2]) {
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }
}
