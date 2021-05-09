package _09_红黑树;

import _00_utils.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        testAddElementTree();
        System.out.println("----------------------------- 方法分割线 -----------------------------");
        // testRemoveElementTree();
    }

    static void testAddElementTree() {
        Integer data[] = new Integer[] {
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24
        };

        BST<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

        BinaryTrees.println(rb);
    }

    static void testRemoveElementTree() {
        Integer data[] = new Integer[] {
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24
        };

        BST<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

        rb.remove(24);
        rb.remove(23);
        rb.remove(22);
        rb.remove(19);
        rb.remove(20);
        rb.remove(18);
        
        BinaryTrees.println(rb);
    }
}
