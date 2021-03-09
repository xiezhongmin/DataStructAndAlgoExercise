package _03_链表;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 测试链表增删改查
		List<Integer> list1 = new LinkedList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		list1.add(5);
        list1.add(0, 10);
        list1.add(3, 33);
        list1.remove(0);
		System.out.println(list1 + "\n");

		// 测试ArrayList扩容与缩容
		List<Integer> list2 = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			list2.add(i);
		}
		for (int i = 0; i < 50; i++) {
			list2.remove(0);
		}
		System.out.println(list2);
	}

}
