package _12_堆;

import _00_utils.printer.BinaryTrees;

import java.util.Comparator;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        test1();
        System.out.println("----------------------------- 方法分割线 -----------------------------");
        test2();
        System.out.println("----------------------------- 方法分割线 -----------------------------");
        test3();
        System.out.println("----------------------------- 方法分割线 -----------------------------");
        test4();
    }

    static void test1() {
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

    static void test2() {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data);
        BinaryTrees.println(heap);
        data[0] = 10;
        data[1] = 20;
        BinaryTrees.println(heap);
    }

    static void test3() {
        // 从 n 个整数中，找出最大的前 k 个数（k 远远小于n ）
        // 如果使用排序算法进行全排序，需要 O(nlogn) 的时间复杂
        // 如果使用二叉堆来解决，可以使用 O(nlogk) 的时间复杂度来解决

        // 新建一个小顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // 找出最大的前k个数
        int k = 3;
        Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
                91, 19, 54, 47, 73, 62, 76, 63, 35, 18,
                90, 6, 65, 49, 3, 26, 61, 21, 48};

        // 扫描
        for (int i = 0; i < data.length; i++) {
            if (heap.size() < k) { // 前k个数添加到小顶堆
                heap.add(data[i]);
            } else if (data[i] > heap.get()) { // 如果是第k + 1个数，并且大于堆顶元素
                heap.replace(data[i]);
            }
        }

        BinaryTrees.println(heap);
    }

    static void test4() {
        // 使用堆排序将一个无序数组转换成一个升序数组

        Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
                91, 19, 54, 47, 73, 62, 76, 63, 35, 18,
                90, 6, 65, 49, 3, 26, 61, 21, 48};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data);

        Stack<Integer> stack = new Stack<>();

        while (!heap.isEmpty()) {
            stack.push(heap.remove());
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }
}
