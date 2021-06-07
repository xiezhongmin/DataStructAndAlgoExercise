package _11_哈希表;

import _00_utils.Asserts;
import _00_utils.TimeUtil;
import _00_utils.file.FileInfo;
import _00_utils.file.Files;
import _10_集合映射.Map.Map;
import _10_集合映射.Map.TreeMap;
import _11_哈希表.Map.HashMap;

public class Main {
    public static void main(String[] args) {
        String filepath = "../";
        String[] extensions = new String[]{"java", "h", "m", "swift", "c"};
        FileInfo fileInfo = Files.read(filepath, extensions);
        String[] words = fileInfo.words();
        System.out.println("总行数：" + fileInfo.getLines());
        System.out.println("单词总数：" + words.length);
        System.out.println("-------------------------------------");

        testMap1(new HashMap<>(), words);
        testMap1(new TreeMap<>(), words);
        testMap2(new HashMap<>());
        testMap3(new HashMap<>());
        testMap4(new HashMap<>());
        testMap5(new HashMap<>());
    }

    static void testMap1(Map<Object, Integer> map, String[] words) {
        TimeUtil.check(map.getClass().getName() + " 测试性能", new TimeUtil.Task() {
            @Override
            public void execute() {
                for (String word : words) {
                    Integer count = map.get(word);
                    count = count == null ? 0 : count;
                    map.put(word, count + 1);
                }
                System.out.println(map.size()); // 17188

                int count = 0;
                for (String word : words) {
                    Integer i = map.get(word);
                    count += i == null ? 0 : i;
                    map.remove(word);
                }
                Asserts.test(count == words.length);
                Asserts.test(map.size() == 0);
            }
        });
    }

    static void testMap2(Map<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }

        Asserts.test(map.size() == 20);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == 10);
        Asserts.test(map.get(new Key(6)) == 11);
        Asserts.test(map.get(new Key(7)) == 12);
        Asserts.test(map.get(new Key(8)) == 8);
    }

    static void testMap3(Map<Object, Integer> map) {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        Asserts.test(map.size() == 5);
        Asserts.test(map.get(null) == 8);
        Asserts.test(map.get("jack") == 6);
        Asserts.test(map.get(10) == null);
        Asserts.test(map.get(new Object()) == null);
        Asserts.test(map.containsKey(10));
        Asserts.test(map.containsKey(null));
        Asserts.test(map.containsValue(null));
        Asserts.test(map.containsValue(1) == false);
    }

    static void testMap4(Map<Object, Integer> map) {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            Asserts.test(map.remove(new Key(i)) == i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        Asserts.test(map.size() == 19);
        Asserts.test(map.get(new Key(1)) == 6);
        Asserts.test(map.get(new Key(2)) == 7);
        Asserts.test(map.get(new Key(3)) == 8);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == null);
        Asserts.test(map.get(new Key(6)) == null);
        Asserts.test(map.get(new Key(7)) == null);
        Asserts.test(map.get(new Key(8)) == 8);
    }

    static void testMap5(Map<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        Asserts.test(map.get(new SubKey1(1)) == 5);
        Asserts.test(map.get(new SubKey2(1)) == 5);
        Asserts.test(map.size() == 20);
    }
}


// ---------------------------------------------------  测试模型  ---------------------------------------------------


class Key {
  protected int value;

  Key(int value) {
      this.value = value;
  }

    @Override
    public int hashCode() {
        return value / 10;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        return ((Key) obj).value == value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

}

class SubKey1 extends Key {
    public SubKey1(int value) {
        super(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null ||
                (obj.getClass() != SubKey1.class && obj.getClass() != SubKey2.class)) return false;
        return ((Key)obj).value == value;
    }
}

class SubKey2 extends Key {
    public SubKey2(int value) {
        super(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null ||
                (obj.getClass() != SubKey1.class && obj.getClass() != SubKey2.class)) return false;
        return ((Key)obj).value == value;
    }
}