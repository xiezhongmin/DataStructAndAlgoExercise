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
     */
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        // 备份左边数据
        int[] left = new int[m];
        for (int i = 0; i < m; i++) {
            left[i] = nums1[i];
        }

        // 扫描比较
        int p1 = 0, p2 = 0, cur = 0;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                nums1[cur++] = nums2[p2++];
            } else if (p2 == n) {
                nums1[cur++] = left[p1++];
            } else if (left[p1] < nums2[p2]) {
                nums1[cur++] = left[p1++];
            } else {
                nums1[cur++] = nums2[p2++];
            }
        }
    }

    /**
     * 利用归并排序解法 + 逆双向指针：
     * 思路：
     * 观察可知，nums1的后半部分是空的，可以直接覆盖而不会影响结果。
     * 因此可以指针设置为从后向前遍历，每次取两者之中的较大者放进 nums1 的最后面。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了 98.93% 的用户
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        // 扫描比较
        int p1 = m - 1, p2 = n - 1, cur = m + n - 1;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                nums1[cur--] = nums2[p2--];
            } else if (p2 == -1) {
                nums1[cur--] = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                nums1[cur--] = nums1[p1--];
            } else {
                nums1[cur--] = nums2[p2--];
            }
        }
    }

    public static void main(String[] args) {
        int[] array1 = {1, 3, 5, 8, 9, 0, 0, 0};
        int[] array2 = {2, 3, 4};
        merge2(array1, 5, array2, 3);
        for (int i = 0; i < array1.length; i++) {
            System.out.println(array1[i]);
        }
    }
}
