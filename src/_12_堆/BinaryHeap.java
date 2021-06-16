package _12_堆;

import _00_utils.printer.BinaryTreeInfo;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    BinaryHeap() {
        this(null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);

        // 1.确保容量
        // 2.将新元素添加到数组最后
        // 3.数组最后一个上滤处理
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();

        // 1.用最后一个节点覆盖根节点
        // 2.删除最后一个节点
        // 3.根节点下滤处理
        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);
        return root;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        // 1.判断size是否为0(数组为空)
        // 2.如果数组为空，直接添加
        // 3.如果数组不为空:
        //   - 1.取出第一个节点
        //   - 2.数组第一个节点位置替换为element
        //   - 3.下滤操作

        E top = null;
        if (size == 0) {
            elements[size++] = element;
        } else {
            top = elements[0];
            elements[0] = element;
            siftDown(0);
        };

        return top;
    }

    /**
     * 让 index 位置的元素上滤
     *
     * @param index 索引
     */
    private void siftUp(int index) {
        // 思路：
        // 1.拿到index位置的元素element
        // 2. while循环(index > 0) 有父元素的情况 循环处理一下内容:
        //   1.element与父节点比较
        //   2.如果父节点大, 则不需要上滤 退出循环
        //   3.循环交换 index、parentIndex位置的内容
        E element = elements[index];
        while (index > 0) { // 有父元素情况进入循环
            int parentIndex = (index - 1) >> 1; // 计算父节点的index
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) break; // 如果父节点大, 则不需要上滤 退出循环

            // 交换index、parentIndex位置的内容
            elements[index] = parent;
            // 重新赋值index
            index = parentIndex;
        }
        // 优化后 只需要最终确定element的位置并赋值
        elements[index] = element;
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

        E element = elements[index];
        // 第一个叶子节点的索引 == 非叶子节点的数量 == size/2
        // 必须保证index位置是非叶子节点
        while (index < (size >> 1)) {
            // index的子节点有2种情况: 1.只有左子节点 2.同时有左右子节点

            // 默认为左子节点跟它进行比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            // 右子节点的index
            int rightIndex = childIndex + 1;
            // 选出左右子节点最大的那个
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[rightIndex];
            }

            // 如果element大, 则不需要下滤 退出循环
            if (compare(element, child) >= 0) break;

            // 将子节点存放到index位置
            elements[index] = child;
            // 重新设置index
            index = childIndex;
        }
        // 优化后 只需要最终确定element的位置并赋值
        elements[index] = element;
    }

    // 保证要有capacity的容量
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        // 空间足够不需要扩容
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int) node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int) node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }
}
