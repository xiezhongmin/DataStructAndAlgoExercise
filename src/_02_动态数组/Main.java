package _02_动态数组;

/*
 * 什么是数据结构?
 * 数据结构是计算机存储、组织数据的方式
 * 
 * 什么是线性表？
 * 线性表是具有 n 个相同类型元素的有限序列（ n ≥ 0 ）
 * 常见的线性表有：
 * 1.数组（是一种顺序存储的线性表，所有元素的内存地址是连续的）
 * 2.链表
 * 3.栈
 * 4.队列
 * 5.哈希表（散列表）
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);

		list.add(1, 10);
		list.add(3, 33);
		list.remove(0);
		list.remove(Integer.valueOf(2));
		
		System.out.println(list);
	}

}
