package _03_链表;

public abstract class AbstractList<E> implements List<E> {
	protected int size;
	
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
	
	// 添加元素到最后面
	public void add(E element) {
		add(size, element);
	}
	
	// 将元素element从数组中移除
	public E remove(E element) {
		return remove(indexOf(element));
	}
	
	// 超出边界处理
	public void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index:" + index + ", size:" + size);
	}
	
	// 边界检查
	public void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	// 添加元素边界检查
	public void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
}
