package _06_二叉搜索树;

import _00_utils.printer.BinaryTrees;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        testIntegerTree();
        testPersonTree();
        testTraversalTree();
        testIsCompleteTree();
        testHeightTree();
        testRemoveElementTree();
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

        bst.preorder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });

        System.out.println();

        bst.inorder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });

        System.out.println();

        bst.postorder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });

        System.out.println();

        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });

        System.out.println();
    }

    static void testIsCompleteTree() {
        System.out.println("-------------------- 此处是方法分割线 --------------------");

        Integer data[] = new Integer[] {
                7, 4, 9, 2, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        System.out.println();
        System.out.println(bst.isComplete());

        System.out.println("-------------------- 此处是方法分割线 --------------------");
    }

    static void testHeightTree() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1, 15, 17, 9
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        System.out.println(bst.height());

        System.out.println("-------------------- 此处是方法分割线 --------------------");
    }

    static void testRemoveElementTree() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        bst.remove(4);

        BinaryTrees.println(bst);
    }
}
