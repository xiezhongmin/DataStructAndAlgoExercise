package _05_队列;

public class Main {
    public static void main(String[] args) {
        testQueue();
        testDeque();
    }

    static void testQueue() {
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }

        System.out.println("-------------------- 此处是方法分割线 --------------------");
    }

    static void testDeque() {
		Deque<Integer> queue = new Deque<>();
		queue.enQueueFront(11);
		queue.enQueueFront(22);
		queue.enQueueRear(33);
		queue.enQueueRear(44);

		/* 尾  44  33   11  22 头 */

		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueRear());
		}

        System.out.println("-------------------- 此处是方法分割线 --------------------");
    }
}

