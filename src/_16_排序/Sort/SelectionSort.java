package _16_排序.Sort;

/**
 * 选择排序
 * ① 从序列中找出最大的那个元素，然后与最末尾的元素交换位置
 *  ✓ 执行完一轮后，最末尾的那个元素就是最大的元素
 * ② 忽略①中曾经找到的最大元素，重复执行步骤①
 *
 * 选择排序的交换次数要远远少于冒泡排序，平均性能优于冒泡排序
 *
 * 时间复杂度
 * 最好、最坏、平均 O(n2)
 *
 * 空间复杂度
 * O(1)
 *
 * 属于不稳定排序
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int max = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(max, begin) <= 0) {
                    max = begin;
                }
            }
            swap(max, end);
        }
    }
}
