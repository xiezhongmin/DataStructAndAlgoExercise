package _17_并查集;

/**
 * 路径压缩使路径上的所有节点都指向根节点，所以实现成本稍高
 *
 * Quick Union - 基于rank的优化 - 路径分裂(Path Spliting) : 使路径上的每个节点都指向其祖父节点
 */
public class QuickUnion_Rank_PS extends QuickUnion_Rank {
    /**
     * 构造方法
     * @param capacity 容量
     */
    public QuickUnion_Rank_PS(int capacity) {
        super(capacity);
    }

    /**
     * 路径分裂: 路径上的每个节点都指向其祖父节点
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }
        return v;
    }
}