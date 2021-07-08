package _16_排序;

import _00_utils.Asserts;
import _00_utils.Integers;
import _16_排序.Sort.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] array = Integers.random(50000, 45000, 50000);
        testSorts(array,
              //  new BubbleSort2(),
              //  new BubbleSort3(),
              //  new SelectionSort(),
                new HeapSort(),
              //  new InsertionSort1(),
              //  new InsertionSort2(),
                new InsertionSort3(),
                new MergeSort(),
                new QuickSort(),
                new ShellSort(),
                new CountingSort());
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }

        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
