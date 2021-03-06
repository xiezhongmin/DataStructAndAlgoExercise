package _17_并查集;

public class QuickUnion extends UnionFind {
    /**
     * 构造方法
     * @param capacity 容量
     */
    public QuickUnion(int capacity) {
        super(capacity);
    }

    /**
     * 通过parent链条不断的向上找，直到找到根节点
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    /**
     * 将 v1的根节点 嫁接到 v2的根节点 上
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        parents[p1] = p2;
    }
}
