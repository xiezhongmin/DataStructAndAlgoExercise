package _00_leetcode.栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * 请你仅使用两个栈实现先入先出队列
 * 方法：
 * 将一个栈当作输入栈，一个栈当作输出栈，当输出栈为空时，输入栈全部压入输出栈，
 * 再从输出栈pop时顺序刚好相反，满足队列要求
 */
public class _232_用栈实现队列 {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    /** Initialize your data structure here. */
    public _232_用栈实现队列() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    private void checkOutStack() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) { outStack.push(inStack.pop()); }
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
