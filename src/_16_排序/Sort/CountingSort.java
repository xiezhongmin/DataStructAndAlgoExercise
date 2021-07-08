package _16_排序.Sort;

/**
 * 计数排序
 * 适合对一定范围内的整数进行排序
 *
 * 计数排序的核心思想
 * 统计每个整数在序列中出现的次数，进而推导出每个整数在有序序列中的索引
 *
 * 时间复杂度
 * 最好、最坏、平均：O(n + k)
 *
 * 空间复杂度
 * O(n + k)
 *
 * 属于稳定排序
 */
public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        if (array.length < 2) return;
        
        // 求出最值
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) { max = array[i]; }
            if (array[i] < min) { min = array[i]; }
        }

        // 开辟内存空间，存储次数
        int[] counts = new int[max - min + 1];
        // 统计每个整数出现的次数
        for (int i = 0; i < array.length; i++) {
            // array中的元素 k 在有序序列中的索引
            // counts[k – min]
            counts[array[i] - min]++;
        }
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 从后往前遍历元素，将它放到有序数组中的合适位置
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0 ; i--) {
            newArray[--counts[array[i] - min]] = array[i];
        }

        // 将有序数组赋值到array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }
}
