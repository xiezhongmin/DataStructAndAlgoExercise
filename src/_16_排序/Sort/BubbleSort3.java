package _16_排序.Sort;

/**
 * 冒泡排序优化2：记录尾部已排好序的index位置, 扫描时忽略尾部已排好序的部分
 * @param <T>
 */
public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        // 优化二: 记录尾部已排好序的index位置, 扫描时忽略尾部已排好序的部分
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }
}
