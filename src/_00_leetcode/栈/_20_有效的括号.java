package _00_leetcode.栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 */
public class _20_有效的括号 {
    /**
     * 方法： 字符串的API（包含与替换）
     * 思路：
     * 使用字符串的API 判断是否包含"()"、"{}"、"[]" 若包含替换为空字符串
     * 性能拉跨不推荐，字符串的查找及替换(尤其不可变字符串)很消耗性能
     * @param s
     * @return
     */
    public static boolean isValid1(String s) {
        while (s.contains("()") ||
                s.contains("{}") ||
                s.contains("[]")) {
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }
        return s.isEmpty();
    }

    /**
     * 方法：利用栈
     * 思路：
     * 遇见左字符，将左字符入栈
     * 遇见右字符：
     * 1.如果栈是空的，说明括号无效
     * 2.如果栈不为空，将栈顶字符出栈，与右字符之匹配：
     * 如果左右字符不匹配，说明括号无效，如果左右字符匹配，继续扫描下一个字符
     * 所有字符扫描完毕后，栈为空，说明括号有效，否则无效
     * @param s
     * @return
     */
    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') { // 左括号
                stack.push(c); // 入栈
            } else { // 右括号
                if (stack.isEmpty()) return false; // 如果栈为空，即没有左括号 不匹配
                if (c == '}' && stack.pop() != '{') return false;
                if (c == ']' && stack.pop() != '[') return false;
                if (c == ')' && stack.pop() != '(') return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "{[()]}";
        System.out.println(isValid2(s));
    }
}
