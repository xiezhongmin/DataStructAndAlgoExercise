package _02_动态数组;

@SuppressWarnings("unchecked")
public class ArrayList <E> {
	private int size;
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	/**
	 * 构造方法 + capacity
	 * @param capacity
	 */
	public ArrayList(int capacity) {
		int newCapacity = Math.max(capacity, DEFAULT_CAPACITY);
		elements = (E[]) new Object[newCapacity];
	}

	// 构造方法
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
    // 元素数量 
	public int size() {
		return size;
	}
	
	// 是否为空
	public Boolean isEmpty() {
		return size == 0;
	}
	
	// 是否包含某个元素
	public Boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	
	// 设置index位置的元素
	public E set(int index, E element) {
		rangeCheck(index);
		
		E old = get(index);
		elements[index] = element;
		return old;
	}
	
	// 获取index位置的元素
	public E get(int index) {
		rangeCheck(index);

		return elements[index];
	}
	
	// 添加元素到最后面
	public void add(E element) {
		add(size, element);
	}
	
	// 在index位置插入一个元素
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
	public E remove(int index) {
		rangeCheck(index);
		
		E old = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		elements[--size] = null;
		
		return old;
	}
	
	// 将元素element从数组中移除
	public E remove(E element) {
		return remove(indexOf(element));
	}
	
	// 查看元素的索引
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(elements[i])) {
					return i;
				}
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	// 清除所有元素
	public void clear() {
		size = 0;
	}
	
	// 保证要有capacity的容量
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		// 空间足够不需要扩容
		if (oldCapacity >= capacity) return;
		
		// 新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		
		elements = newElements;
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}
	
	// 超出边界处理
	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index:" + index + ", size:" + size);
	}
	
	// 边界检查
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	// 添加元素边界检查
	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
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
