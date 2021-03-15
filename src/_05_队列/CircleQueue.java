package _05_队列;

import java.util.Arrays;

/**
 * 循环队列
 * 其实队列底层也可以使用动态数组实现，并且各项接口也可以优化到 O(1) 的时间复杂度
 * 动态数组 + 队头下标
 */
public class CircleQueue<E> {
    private int front;
    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 构造方法 + capacity
     * @param capacity
     */
    public CircleQueue(int capacity) {
        int newCapacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[newCapacity];
    }

    // 构造方法
    public CircleQueue() { this(DEFAULT_CAPACITY); }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[circleIndex(i)] = null;
        }
        front = 0;
        size = 0;
    }

    // 入队
    public void enQueue(E element) {
        ensureCapacity(size + 1);

        elements[circleIndex(size)] = element;
        size++;
    }

    // 出队
    public E deQueue() {
        E element = elements[front];
        elements[front] = null;
        // front 前进一位
        front = circleIndex(1);
        size--;
        return element;
    }

    // 获取队头
    public E front() {
        return elements[front];
    }

    /**
     * 根据index获取循环队列的下标
     * @param index
     * @return
     */
    private int circleIndex(int index) {
        return (front + index) % elements.length;
    }

    /**
     * 保证容量(不足则扩容)
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        // 空间足够不需要扩容
        if (oldCapacity >= capacity) {  return; }

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[circleIndex(i)];
        }

        elements = newElements;
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
