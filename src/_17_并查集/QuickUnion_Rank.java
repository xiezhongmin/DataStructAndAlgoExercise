package _17_并查集;

/**
 * 基于size的优化，也可能会存在树不平衡的问题
 * Quick Union - 基于rank的优化: 高度矮的树 嫁接到 高度高的树
 */
public class QuickUnion_Rank extends QuickUnion {
    private int[] ranks;

    /**
     * 构造方法
     * @param capacity 容量
     */
    public QuickUnion_Rank(int capacity) {
        super(capacity);

        ranks = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            ranks[i] = 1;
        }
    }

    /**
     * 基于rank的优化
     * 将 高度矮的树 嫁接到 高度高的树 上
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        if (ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
            ranks[p2] += 1;
        }
    }
}
