package _16_排序.Sort;

/**
 * 归并排序
 * ① 不断地将当前序列平均分割成2个子序列
 *   ✓ 直到不能再分割（序列中只剩1个元素）
 * ② 不断地将2个子序列合并成一个有序序列
 *   ✓ 直到最终只剩下1个有序序列
 *
 * 时间复杂度
 * 由于归并排序总是平均分割子序列，所以最好、最坏、平均都是 O(nlogn)
 *
 * 空间复杂度：
 * O(n)
 *
 * 属于稳定排序
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /**
     * 对 [begin, end) 范围的数据进行归并排序
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;

        int mid = (begin + end) >> 1;
        // 拆解
        sort(begin, mid);
        sort(mid, end);
        // 合并
        merge(begin, mid, end);
    }

    /**
     * 将 [begin, mid) 和 [mid, end) 范围的序列合并成一个有序序列
     */
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;

        // 备份左边的数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        // 如果左边还没有结束
        while (li < le) {
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) { // 右边比较小
                array[ai++] = array[ri++];
            } else { // 左边小于或等于右边
                array[ai++] = leftArray[li++];
            }
        }
    }
}
