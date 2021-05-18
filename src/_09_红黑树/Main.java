package _09_红黑树;

import _00_utils.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        testAddElementTree();
        System.out.println("----------------------------- 方法分割线 -----------------------------");
        testRemoveElementTree();
    }

    static void testAddElementTree() {
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        BST<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

        BinaryTrees.println(rb);
    }

    static void testRemoveElementTree() {
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        BST<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

        BinaryTrees.println(rb);

        for (int i = 0; i < data.length; i++) {
            rb.remove(data[i]);
            System.out.println("---------------------------------------");
            System.out.println("【" + data[i] + "】");
            BinaryTrees.println(rb);
        }
    }
}
