package _17_并查集;

/**
 * 虽然有了基于rank的优化，树会相对平衡一点
 * 但是随着Union次数的增多，树的高度依然会越来越高
 * 导致find操作变慢，尤其是底层节点（因为find是不断向上找到根节点）
 *
 * Quick Union - 基于rank的优化 - 路径压缩(Path Compression)
 */
public class QuickUnion_Rank_PathCompression extends QuickUnion_Rank {
    /**
     * 构造方法
     * @param capacity 容量
     */
    public QuickUnion_Rank_PathCompression(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        if (parents[v] != v) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
