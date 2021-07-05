package _16_排序.Sort;

/**
 * 堆排序
 * ① 对序列进行原地建堆（heapify）
 * ② 重复执行以下操作，直到堆的元素数量为 1
 *  ✓ 交换堆顶元素与尾元素
 *  ✓ 堆的元素数量减 1
 *  ✓ 对 0 位置进行 1 次 siftDown（下滤）操作
 *
 * 时间复杂度
 * 最好、最坏、平均时间复杂度：O(nlogn)
 *
 * 空间复杂度
 * O(1)
 *
 * 属于不稳定排序
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    private int heapSize;

    @Override
    protected void sort() {
        heapSize = array.length;
        // 1.批量建堆
        heapify();
        // 循环以下操作：
        // 1. 交换堆顶与堆尾元素
        // 2.heapSize--
        // 3.堆顶元素下滤
        while (heapSize > 1) {
            swap(0, --heapSize);
            siftDown(0);
        }
    }

    /**
     * 批量建堆
     */
    private void heapify() {
        // 批量建堆，有两种方式：

        // 1.自上而下的上滤（类比添加， 效率相对较低）
        // for (int i = 1; i < size; i++) {
        //    siftUp(i);
        // }

        // 2.自下而上的下滤（类比删除，效率相对要高）
        // (size >> 1) - 1 = 叶子节点的前一个非叶子节点
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 让 index 位置的元素下滤
     *
     * @param index 索引
     */
    private void siftDown(int index) {
        // 思路:
        // 1.拿到index位置的元素element
        // 2.计算第一个叶子节点的索引, 这是while循环的条件 index < 第一个叶子节点的索引, 这是为了保证index位置是非叶子节点
        // 3.while循环(index < 第一个叶子节点的索引) 循环处理一下内容:
        //   1.拿到最大的子节点与element比较
        //   2.如果element大, 则不需要下滤 退出循环
        //   3.循环交换 index、childIndex位置的内容

        T element = array[index];
        // 第一个叶子节点的索引 == 非叶子节点的数量 == size/2
        // 必须保证index位置是非叶子节点
        while (index < (heapSize >> 1)) {
            // index的子节点有2种情况: 1.只有左子节点 2.同时有左右子节点

            // 默认为左子节点跟它进行比较
            int childIndex = (index << 1) + 1;
            T child = array[childIndex];
            // 右子节点的index
            int rightIndex = childIndex + 1;
            // 选出左右子节点最大的那个
            if (rightIndex < heapSize && cmp(array[rightIndex], child) > 0) {
                child = array[childIndex = rightIndex];
            }

            // 如果element大, 则不需要下滤 退出循环
            if (cmp(element, child) >= 0) break;

            // 将子节点存放到index位置
            array[index] = child;
            // 重新设置index
            index = childIndex;
        }
        // 优化后 只需要最终确定element的位置并赋值
        array[index] = element;
    }
}
