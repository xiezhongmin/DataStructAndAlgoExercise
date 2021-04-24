package _07_二叉搜索树重构;

import _00_utils.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        testRemoveElementTree();
    }

    static void testRemoveElementTree() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        bst.remove(4);

        BinaryTrees.println(bst);
    }
}
