package com.sail.leetcode.interview2020.堆与栈;

import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO20_有效的括号 {

    public boolean isValid(String s) {
        LinkedList<Character> stack= new LinkedList<>();
        char[] c = s.toCharArray();
        for(int i=0;i<c.length;i++){
            if(c[i]=='('||c[i]=='['||c[i]=='{'){
                stack.push(c[i]);
            }else{
                if(!stack.isEmpty()&&(c[i]==')'&&stack.peek()=='('||c[i]==']'&&stack.peek()=='['||c[i]=='}'&&stack.peek()=='{')){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
