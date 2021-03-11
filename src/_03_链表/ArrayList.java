package _03_链表;

@SuppressWarnings("unchecked")
public class ArrayList <E> extends AbstractList<E> {
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;

	// 构造方法
	public ArrayList(int capacity) {
		capacity = Math.max(capacity, DEFAULT_CAPACITY);
		elements = (E[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	// 设置index位置的元素
	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		
		E old = get(index);
		elements[index] = element;
		return old;
	}
	
	// 获取index位置的元素
	@Override
	public E get(int index) {
		rangeCheck(index);

		return elements[index];
	}
	
	// 在index位置插入一个元素
	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		// 保证容量
		ensureCapacity(size + 1);
		
		for (int i = size - 1; i >= index; i--) {
			elements[i + 1] = elements[i];
		}
		
		elements[index] = element;
		size++;
	}
	
	// 删除index位置的元素
	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		E old = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		elements[--size] = null;
		// 缩容
		trim();
		return old;
	}
	
	// 查看元素的索引
	@Override
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (get(i) == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(get(i))) {
					return i;
				}
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	// 清除所有元素
	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;

		if (elements != null && elements.length > DEFAULT_CAPACITY) {
			elements = (E[]) new Object[DEFAULT_CAPACITY];
		}
	}
	
	// 保证要有capacity的容量
	public void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) { // 空间足够不需要扩容
			return;
		}
		
		// 新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		
		elements = newElements;
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}

	// 缩容
	private void trim() {
		int oldCapacity = elements.length;
		// 注意：扩容倍数 * 缩容倍数 = 1 会导致复杂度震荡
		int newCapacity = oldCapacity >> 1; // 新容量为旧容量的1/2
		// 剩余空间不多或者小于默认容量不需要缩容
		if (size >= newCapacity || oldCapacity <= DEFAULT_CAPACITY) return;

		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}

		elements = newElements;
		System.out.println(oldCapacity + "缩容为" + newCapacity);
	}
	
	@Override
	public String toString() {
		// size=3, [99, 88, 77]
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(elements[i]);
		}
		string.append("]");
		return string.toString();
	}
	
}
