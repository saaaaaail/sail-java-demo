package com.sail.leetcode.interview2020.堆与栈;

import java.util.LinkedList;

/**
 * 给你一个由 '('、')' 和小写字母组成的字符串 s。
 *
 * 你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
 *
 * 请返回任意一个合法字符串。
 *
 * 有效「括号字符串」应当符合以下 任意一条 要求：
 *
 * 空字符串或只包含小写字母的字符串
 * 可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
 * 可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
 *  
 *
 * 示例 1：
 *
 * 输入：s = "lee(t(c)o)de)"
 * 输出："lee(t(c)o)de"
 * 解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
 * 示例 2：
 *
 * 输入：s = "a)b(c)d"
 * 输出："ab(c)d"
 * 示例 3：
 *
 * 输入：s = "))(("
 * 输出：""
 * 解释：空字符串也是有效的
 * 示例 4：
 *
 * 输入：s = "(a(b(c)d)"
 * 输出："a(b(c)d)"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO1249_移除无效括号 {


    /**
     * 思路 用递归超时  鉴于结果只要一个  用stack也可以实现，47ms 16%
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        int lc = 0;
        int rc = 0;
        //计算无效的左右括号的数值
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c!='('&&c!=')'){continue;}
            if(c=='('){
                lc++;
            }else{
                if(lc>0){
                    lc--;
                }else{
                    rc++;
                }
            }
        }
        LinkedList<Integer> stack = new LinkedList<>();
        int idx = 0;
        int len = s.length();
        while(idx<len){
            char c = s.charAt(idx);
            if(c=='('){
                stack.push(idx);
                idx++;
            }else if(c==')'){
                if(!stack.isEmpty()){
                    stack.pop();
                    idx++;
                }else if(rc>0){
                    //这里清理无效的右括号
                    s = s.substring(0,idx)+s.substring(idx+1);
                    rc--;
                    len--;
                }
            }else{
                idx++;
            }
        }
        while(!stack.isEmpty()&&lc>0){
            int i = stack.pop();
            //清理无效的左括号
            s = s.substring(0,i)+s.substring(i+1);
            lc--;
        }
        return s;
    }
}
