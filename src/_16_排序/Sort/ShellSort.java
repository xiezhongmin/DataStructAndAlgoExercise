package _16_排序.Sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 希尔排序
 * 希尔排序是把序列看作是一个矩阵，分成m列，逐列进行排序
 */
public class ShellSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        List<Integer> stepSequence = sedgewickStepSequence();
        for (int step : stepSequence) {
            sort(step);
        }
    }

    /**
     * 分成step列进行排序
     */
    private void sort(int step) {
        for (int col = 0; col < step; col++) { // 逐列进行排序
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                while (cur > col && cmp(cur, cur - step) < 0) {
                    swap(cur, cur - step);
                    cur -= step;
                }
            }
        }
    }

    /**
     * 希尔本人给出的步长序列 最坏情况时间复杂度是O(n2)
     */
    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    /**
     * 目前已知的最好的步长序列，最坏情况时间复杂度是O(n4/3)
     */
    private List<Integer> sedgewickStepSequence() {
        List<Integer> stepSequence = new LinkedList<>();
        int k = 0, step = 0;
        while (true) {
            if (k % 2 == 0) {
                int pow = (int) Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            } else {
                int pow1 = (int) Math.pow(2, (k - 1) >> 1);
                int pow2 = (int) Math.pow(2, (k + 1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            if (step >= array.length) break;
            stepSequence.add(0, step);
            k++;
        }
        return stepSequence;
    }

}
