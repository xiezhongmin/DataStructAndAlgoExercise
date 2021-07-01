package _16_排序;

/**
 * 二分查找法
 */
public class BinarySearch <T extends Comparable<T>> {
    /**
     * 查找 v 在有序数组 array 中的位置
     */
    public int indexOf(T[] array, T v) {
        if (array == null || array.length == 0) return -1;

        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v.compareTo(array[mid]) < 0) {
                end = mid;
            } else if (v.compareTo(array[mid]) > 0) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
