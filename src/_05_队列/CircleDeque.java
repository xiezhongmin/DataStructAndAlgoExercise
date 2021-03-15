package _05_队列;

/**
 * 循环双端队列
 * 动态数组 + 队头下标
 */
public class CircleDeque<E> {
    private int front;
    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 构造方法 + capacity
     * @param capacity
     */
    public CircleDeque(int capacity) {
        int newCapacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[newCapacity];
    }

    // 构造方法
    public CircleDeque() { this(DEFAULT_CAPACITY); }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[circleIndex(i)] = null;
        }
        front = 0;
        size = 0;
    }

    // 从队尾入队
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);

        elements[circleIndex(size)] = element;
        size++;
    }

    // 从队头出队
    public E deQueueFront() {
        E element = elements[front];
        elements[front] = null;
        // front 前进一位
        front = circleIndex(1);
        size--;
        return element;
    }

    // 从队头入队
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);

        front = circleIndex(-1);
        elements[front] = element;
        size++;
    }

    // 从队尾出队
    public E deQueueRear() {
        int rearIndex = circleIndex(size - 1);
        E element = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        return element;
    }

    // 获取队列的头元素
    public E front() {
        return elements[front];
    }

    // 获取队列的尾元素
    public E rear() {
        return elements[circleIndex(size - 1)];
    }

    /**
     * 根据index获取循环队列的下标
     * @param index
     * @return
     */
    private int circleIndex(int index) {
        index += front;
        if (index < 0) return index + elements.length;
        // 为了避免使用乘*、除/、模%、浮点数运算，效率低 此处可以使用%运算符优化
        // 队列%运算符满足 n%m 等价于 n – (m > n ? 0 : m) 的前提条件：n < 2m
        return index - (index >= elements.length ? elements.length : 0); // (front + index) % elements.length;
    }

    /**
     * 保证容量(不足则扩容)
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        // 空间足够不需要扩容
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[circleIndex(i)];
        }

        elements = newElements;
        // 重置front
        front = 0;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capacity=").append(elements.length)
                .append(" size=").append(size)
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}
