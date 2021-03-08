package _03_链表;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		List<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
        list.add(0, 10);
        list.add(3, 33);
        list.remove(0);
		System.out.println(list);
	}

}
