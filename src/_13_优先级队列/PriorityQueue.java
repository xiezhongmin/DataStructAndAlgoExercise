package _13_优先级队列;

import _12_堆.BinaryHeap;
import java.util.Comparator;

public class PriorityQueue<E> {
    private BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public int size() { // 元素数量
        return heap.size();
    }

    public boolean isEmpty() { // 是否为空
        return heap.isEmpty();
    }

    public void enQueue(E element) { // 入队
        heap.add(element);
    }

    public E deQueue() { // 出队
       return heap.remove();
    }

    public E front() { // 获取队头元素
        return heap.get();
    }

    public void clear() { // 清空
        heap.clear();
    }


}
