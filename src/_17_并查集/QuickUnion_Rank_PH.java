package _17_并查集;

/**
 * 路径压缩使路径上的所有节点都指向根节点，所以实现成本稍高
 *
 * Quick Union - 基于rank的优化 - 路径减半(Path Halving) : 使路径上每隔一个节点就指向其祖父节点
 */
public class QuickUnion_Rank_PH extends QuickUnion_Rank {
    /**
     * 构造方法
     *
     * @param capacity 容量
     */
    public QuickUnion_Rank_PH(int capacity) {
        super(capacity);
    }

    /**
     * 路径减半: 路径上每隔一个节点就指向其祖父节点
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}