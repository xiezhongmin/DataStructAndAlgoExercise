package _13_优先级队列;

import _12_堆.BinaryHeap;
import java.util.Comparator;

public class PriorityQueue {
    private BinaryHeap heap;

    public PriorityQueue(Comparator comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        this(null);
    }

    public int size() { // 元素数量
        return heap.size();
    }

    public boolean isEmpty() { // 是否为空
        return heap.isEmpty();
    }

    public void enQueue(Operation op) { // 入队
        heap.add(op);
    }

    public Operation deQueue() { // 出队
        Operation op = (Operation) heap.remove();
        if (op != null) {
            op.start();
        }
        return op;
    }

    public Operation front() { // 获取队头元素
        return (Operation) heap.get();
    }

    public void clear() { // 清空
        heap.clear();
    }


}
