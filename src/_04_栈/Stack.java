package _04_栈;

import _03_链表.ArrayList;

/**
 * 栈是一种特殊的线性表，只能在一端进行操作
 * 往栈中添加元素的操作，一般叫做 push，入栈
 * 从栈中移除元素的操作，一般叫做 pop，出栈（只能移除栈顶元素，也叫做：弹出栈顶元素）
 * 后进先出的原则，Last In First Out，LIFO
 */
public class Stack<E> {
    private ArrayList<E> list = new ArrayList<E>();

    public int size() { return list.size(); }

    public boolean isEmpty() { return list.isEmpty(); }

    public void push(E element) { list.add(element); }

    public E pop() { return list.remove(list.size() - 1); }

    public E top() { return list.get(list.size() - 1); }

    public void clear() { list.clear(); }
}
