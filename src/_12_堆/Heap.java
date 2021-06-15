package _12_堆;

/**
 * 找出前 K 个数据
 * 比如:
 * 从 100 万个整数中找出最大的 100 个整数
 * Top K 问题的解法之一：可以用数据结构“堆”来解决
 */

public interface Heap<E> {
    int size(); // 元素的数量

    boolean isEmpty(); // 是否为空

    void clear(); // 清空

    void add(E element); // 添加元素

    E get(); // 获取堆顶元素

    E remove(); // 删除堆顶元素

    E replace(E element); // 删除堆顶元素的同时插入一个新元素
}
