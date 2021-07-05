package _16_排序.Sort;

/**
 * 插入排序
 * 插入排序非常类似于扑克牌的排序
 *
 * ① 在执行过程中，插入排序会将序列分为2部分
 *  ✓ 头部是已经排好序的，尾部是待排序的
 * ② 从头开始扫描每一个元素
 *  ✓ 每当扫描到一个元素，就将它插入到头部合适的位置，使得头部数据依然保持有序
 *
 * 时间复杂度
 * 最好 O(n) 最坏 O(n2) 平均 O(n2)
 *
 * 空间复杂度
 * O(1)
 *
 * 属于稳定排序
 */
public class InsertionSort1 <T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                swap(cur, cur - 1);
                cur--;
            }
        }
    }
}
