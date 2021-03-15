package _00_leetcode.队列;

import _04_栈.Stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * 用队列实现栈
 * 请仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通队列的全部四种操作（push、top、pop 和 empty）
 */
public class _225_用队列实现栈<E> {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public _225_用队列实现栈() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /**
     * 思路：
     * queue2与queue1互换，用queue2添加最新元素 + queue2之前元素，这样保证倒叙添加
     * 流程：
     * push(1)
     * queue2 = 1 queue1 = 0 -> queue2 = 0 queue1 = 1
     * push(2)
     * queue2 = 2 queue1 = 1 -> queue2 = 2、1 queue1 = 0 -> queue2 = 0 queue1 = 2、1
     * push(3)
     * queue2 = 3 queue1 = 2、1 -> queue2 = 3、2、1 queue1 = 0 -> queue2 = 0 queue1 = 3、2、1
     * @param x
     */
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue tmp = queue2;
        queue2 = queue1;
        queue1 = tmp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public static void main(String[] args) {
        _225_用队列实现栈<Integer> stack = new _225_用队列实现栈<Integer>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
