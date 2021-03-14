package _05_队列;

import _03_链表.LinkedList;

/**
 * 队列是一种特殊的线性表，只能在头尾两端进行操作
 * 队尾（rear）：只能从队尾添加元素，一般叫做 enQueue，入队
 * 队头（front）：只能从队头移除元素，一般叫做 deQueue，出队
 * 先进先出的原则，First In First Out，FIFO
 */
public class Queue<E> {
    private LinkedList<E> list = new LinkedList();

    public int size() { return list.size(); }

    public boolean isEmpty() { return list.isEmpty(); }

    public void clear() { list.clear(); }

    // 入队
    public void enQueue(E element) { list.add(element); }

    // 出队
    public E deQueue() { return list.remove(0); }

    // 获取队列的头元素
    public E front() { return list.get(0); }
}
