package _10_集合映射.Set;

import _10_集合映射.Map.Map;
import _10_集合映射.Map.TreeMap;

public class TreeSet<E> implements Set<E> {
    TreeMap<E, Object> map = new TreeMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element, null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(Visitor visitor) {
        map.traversal(new Map.Visitor() {
            @Override
            public boolean visit(Object key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
