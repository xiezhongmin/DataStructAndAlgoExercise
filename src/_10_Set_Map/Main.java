package _10_Set_Map;

import _10_Set_Map.Map.Map;
import _10_Set_Map.Map.TreeMap;
import _10_Set_Map.Set.Set;
import _10_Set_Map.Set.TreeSet;

public class Main {
    public static void main(String[] args) {
        testSet();
        System.out.println("----------------------------- 方法分割线 -----------------------------");
        testMap();
    }

    static void testSet() {
        Set<Integer> set = new TreeSet<>();
        set.add(2);
        set.add(3);
        set.add(2);
        set.add(5);
        set.add(3);
        set.add(6);
        set.add(5);

        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println("element: " + element);
                return false;
            }
        });
    }

    static void testMap() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("duke", 20);
        map.put("123", 20);
        map.put("456", 20);
        map.put("789", 20);
        map.put("duke", 25);
        map.remove("123");

        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println("key: " + key + " value: " + value);
                return false;
            }
        });
    }
}
