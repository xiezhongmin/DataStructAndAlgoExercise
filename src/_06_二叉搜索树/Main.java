package _06_二叉搜索树;

import _00_utils.printer.BinaryTrees;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        testIntegerTree();
        testPersonTree();
        testTraversalTree();
    }

    static void testIntegerTree() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        System.out.println("-------------------- 此处是方法分割线 --------------------");
    }

    static void testPersonTree() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Person> bst1 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst1.add(new Person(data[i], "duke" + i));
        }

        BinaryTrees.println(bst1);

        System.out.println("-------------------- 此处是方法分割线 --------------------");

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p2.getAge() - p1.getAge();
            }
        });

        for (int i = 0; i < data.length; i++) {
            bst2.add(new Person(data[i], "duke" + i));
        }

        BinaryTrees.println(bst2);

        System.out.println("-------------------- 此处是方法分割线 --------------------");
    }

    static void testTraversalTree() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        System.out.println("-------------------- 此处是方法分割线 --------------------");

        bst.preorderTraversal();

        System.out.println("-------------------- 此处是方法分割线 --------------------");

        bst.inorderTraversal();

        System.out.println("-------------------- 此处是方法分割线 --------------------");

        bst.postorderTraversal();

        System.out.println("-------------------- 此处是方法分割线 --------------------");

        bst.levelOrderTraversal();

    }
}
