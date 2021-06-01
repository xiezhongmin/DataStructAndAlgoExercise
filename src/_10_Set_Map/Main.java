package _10_Set_Map;

import _00_utils.TimeUtil;
import _00_utils.file.FileInfo;
import _00_utils.file.Files;
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
        FileInfo fileInfo = Files.read("../",
                new String[]{"java", "h", "m"});
        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量：" + words.length);
        System.out.println();

        Set<String> set = new TreeSet<>();
        TimeUtil.check("测试 TreeSet 性能", new TimeUtil.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < words.length; i++) {
                    set.add(words[i]);
                }
                for (int i = 0; i < words.length; i++) {
                    set.contains(words[i]);
                }
                for (int i = 0; i < words.length; i++) {
                    set.remove(words[i]);
                }
            }
        });

        System.out.println();
    }

    static void testMap() {
        FileInfo fileInfo = Files.read("../",
                new String[]{"java", "h", "m"});
        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量：" + words.length);
        System.out.println();

        Map<String, Integer> map = new TreeMap<>();
        TimeUtil.check("测试 TreeMap 性能", new TimeUtil.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < words.length; i++) {
                    Integer count = map.get(words[i]);
                    count = (count == null) ? 1 : (count + 1);
                    map.put(words[i], count);
                }
                for (int i = 0; i < words.length; i++) {
                    map.containsKey(words[i]);
                }
                for (int i = 0; i < words.length; i++) {
                    map.remove(words[i]);
                }
            }
        });
    }
}
