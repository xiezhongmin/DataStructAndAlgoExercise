package _16_排序.Sort;

/**
 * 冒泡排序优化1：
 * 已经是有序的数组就退出扫描
 *
 * 时间复杂度
 * 最好 O(n) 最坏 O(n2) 平均 O(n2)
 *
 * 空间复杂度
 * O(1)
 *
 * 属于稳定排序
 */
public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        // 优化一: 已经是有序的数组就退出扫描
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }
}
