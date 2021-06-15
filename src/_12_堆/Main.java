package _12_堆;

import _00_utils.printer.BinaryTreeInfo;
import _00_utils.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(11);
        heap.add(33);
        heap.add(55);
        heap.add(22);
        heap.add(15);
        heap.add(8);
        heap.add(77);
        BinaryTrees.println(heap);
    }
}
