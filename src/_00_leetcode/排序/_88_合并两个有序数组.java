package _00_leetcode.排序;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 * 合并两个有序数组:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 */
public class _88_合并两个有序数组 {
    /**
     * 利用归并排序解法：
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了 98.93% 的用户
     *
     * @param nums1 有序数组1
     * @param m     数组1的元素个数
     * @param nums2 有序数组2
     * @param n     数组2的元素个数
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 备份左边数据
        int[] left = new int[m];
        for (int i = 0; i < m; i++) {
            left[i] = nums1[i];
        }

        // 扫描比较
        int li = 0, le = m;
        int ri = 0, re = n;
        int ai = 0;
        while (ri < re) {
            if (li < le && left[li] < nums2[ri]) {
                nums1[ai++] = left[li++];
            } else {
                nums1[ai++] = nums2[ri++];
            }
        }
        while (li < le) {
            nums1[ai++] = left[li++];
        }
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 0, 0, 0};
        int[] array2 = {2, 5, 6};
        merge(array1, 3, array2, 3);
        for (int i = 0; i < array1.length; i++) {
            System.out.println(array1[i]);
        }
    }
}
