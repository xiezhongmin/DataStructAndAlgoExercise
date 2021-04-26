package _08_AVL树;

import _00_utils.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        testRemoveElementTree();
    }

    static void testRemoveElementTree() {
        Integer data[] = new Integer[] {
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24
        };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }

        BinaryTrees.println(avl);
    }
}
