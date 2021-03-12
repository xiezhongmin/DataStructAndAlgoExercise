package _03_链表;

public class SingleCircleLinkedList<E> extends AbstractList<E> {
	private Node<E> first;
	
	private static class Node<E> {
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}

		@Override
		public String toString() {
			return "{" +
					"element=" + element +
					", next=" + next.element +
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

		// 环形单列表：add方法 只需要在添加头节点时，让尾节点指向头节点
		if (index == 0) {
			// 注意：此处有个坑， 如果提前赋值first节点， 那么 node(size - 1) 拿到的就不是最后一个节点了
			Node<E> oldFirst = first;
			Node<E> newFirst = new Node<E>(element, first);
			Node<E> last = size == 0 ? newFirst : node(size - 1);
			last.next = first;
			first = newFirst;
		} else {
			// 获取前一个节点
			Node<E> prev = node(index - 1);
			prev.next = new Node<E>(element, prev.next);
		}
		size++;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		rangeCheck(index);

		// 环形单列表：remove方法 只需要在移除头节点时，让尾节点指向头节点的下一个节点
		Node<E> node = first;
		if (index == 0) {
			// 注意：要先拿出尾节点
			if (size == 1) { // 需要处理边界值 size == 1 的情况
				first = null;
			} else {
				Node<E> last = node(size - 1);
				first = first.next;
				last.next = first;
			}
		} else {
			// 获取前一个节点
			Node<E> prev = node(index - 1);
			node = prev.next;
			prev.next = node.next;
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
		size = 0;
	}
	
	// 获取index位置对应的节点对象
	private Node<E> node(int index) {
		rangeCheck(index);
		
		Node<E> node = first;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
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
