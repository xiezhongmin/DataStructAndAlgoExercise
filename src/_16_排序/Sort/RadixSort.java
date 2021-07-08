package _16_排序.Sort;

/**
 * 基数排序
 * 基数排序非常适合用于整数排序（尤其是非负整数）
 *
 * 执行流程：
 * 依次对个位数、十位数、百位数、千位数、万位数...进行排序（从低位到高位)
 *
 * 时间复杂度
 * 最好、最坏、平均：O(d * (n + k)) ，d 是最大值的位数，k 是进制
 *
 * 空间复杂度
 * O(n + k)，k 是进制
 *
 * 属于稳定排序
 */
public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        if (array.length < 2) return;

        // 找到最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) { max = array[i]; }
        }

        // 个位数: array[i] / 1 % 10 = 3
        // 十位数：array[i] / 10 % 10 = 9
        // 百位数：array[i] / 100 % 10 = 5
        // 千位数：array[i] / 1000 % 10 = ...
        for (int divider = 1; divider <= max ; divider *= 10) {
            countingSort(divider);
        }
    }

    private void countingSort(int divider) {
        // 开辟内存空间，存储次数
        int[] counts = new int[10];
        // 统计每个整数出现的次数
        for (int i = 0; i < array.length; i++) {
            // array中的元素 k 在有序序列中的索引
            // counts[k – min]
            counts[array[i] / divider % 10]++;
        }
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 从后往前遍历元素，将它放到有序数组中的合适位置
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0 ; i--) {
            newArray[--counts[array[i] / divider % 10]] = array[i];
        }

        // 将有序数组赋值到array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }
}
