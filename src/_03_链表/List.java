package _03_链表;

public interface List <E> {
    static final int ELEMENT_NOT_FOUND = -1;
    
    // 元素数量 
	int size();
	
	// 是否为空
	Boolean isEmpty();
	
	// 是否包含某个元素
	Boolean contains(E element);
	
	// 设置index位置的元素
	E set(int index, E element);
	
	// 获取index位置的元素
	E get(int index);
	
	// 添加元素到最后面
	void add(E element);
	
	// 在index位置插入一个元素
	void add(int index, E element);
	
	// 删除index位置的元素
	E remove(int index);
	
	// 将元素element从数组中移除
	E remove(E element);
	
	// 查看元素的索引
	int indexOf(E element);
	
	// 清除所有元素
	void clear();
}
