package _03_链表;

public class CircleLinkedList<E> extends AbstractList<E> {
	private Node<E> first;
	private Node<E> last;

	private static class Node<E> {
		E element;
		Node<E> prev;
		Node<E> next;

		public Node(Node<E>prev, E element, Node<E> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		}

		@Override
		public String toString() {
			return "{" +
					"prev=" + (prev != null ? prev.element : "null") +
					", element=" + element +
					", next=" + (next != null ? next.element : "null") +
					'}';
		}
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		// 获取前个节点
		Node<E> node = node(index);
		E old = node.element;
		node.element = element;
		return old;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return node(index).element;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		rangeCheckForAdd(index);

		if (index == size) { // 往后面添加元素
			Node<E> oldLast = last;
			last = new Node<E>(oldLast, element, first);
			if (oldLast == null) { // 没有一个元素
				first = last;
			} else {
				oldLast.next = last;
			}
			first.prev = last;
		} else {
			Node<E> next = node(index);
			Node<E> prev = next.prev;
			Node<E> node = new Node<E>(prev, element, next);
			next.prev = node;
			prev.next = node;

			if (next == first) { // index == 0
				first = node;
			}
		}

		size++;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		rangeCheck(index);

		Node<E> node = first;
		if (size == 1) { // 只有一个元素
			first = null;
			last = null;
		} else {
			node = node(index);
			Node<E> prev = node.prev;
			Node<E> next = node.next;
			prev.next = next;
			next.prev = prev;

			if (node == first) { // index == 0
				first = next;
			}

			if (node == last) { // index == size -1
				last = prev;
			}
		}

		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		// TODO Auto-generated method stub
		Node<E> node = first;
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (get(i) == null) {
					return i;
				}
				node = node.next;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(get(i))) {
					return i;
				}
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		first = null;
		last = null;
		size = 0;
	}
	
	// 获取index位置对应的节点对象
	private Node<E> node(int index) {
		rangeCheck(index);

		if (index < (size >> 1)) {
			Node<E> node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		} else {
			Node<E> node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.prev;
			}
			return node;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(node);
			
			node = node.next;
		}
		string.append("]");
		return string.toString();
	}
	
}
