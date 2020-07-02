package com.sail.leetcode.interview2020.图论;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 *
 * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
 *
 * 示例 1:
 *
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 * 示例 2:
 *
 * 输入: "(a)())()"
 * 输出: ["(a)()()", "(a())()"]
 * 示例 3:
 *
 * 输入: ")("
 * 输出: [""]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO301_去除无效的括号 {


    /**
     * 第一种写法，DFS ，遍历所有情况去掉括号与不去括号的  35ms  50%
     * 同时呢记录 最长的有效字符串以及其长度，便于剪枝 当发现字符串的长度已经小于目前的最长字符串了以后就不再往后dfs了
     * 思路 就是两个'('、')'的选与不选，结束条件是记录'('数量的值lc 与 记录')'数量的值rc 相等，表示是有效的
     * 当前字符是'('，就是两种情况一种不选，就把这个位置的字符删掉，重新拼接字符串
     *                 还一种是选 就字符指针往后跳一个就可以了，同时记录'('数量的值+1
     *  当前字符是')'， 一种情况就是不选 把这个字符删掉，重新拼接字符串，当前字符指针也不需要变
     *                  还一种是只有当lc>rc的时候才能选这个')'字符，一旦前面右括号比左括号多，前面的字符串就已经是无效了，后面也没法弥补了
     *  当字符不是上面两个括号的时候字符直接往后跳，如果直接到字符串末尾的话，判断lc==rc不就可以了？
     */
    List<String> result = new ArrayList<>();
    //set去重用，max剪枝用
    Set<String> set = new HashSet<>();
    Integer  max = Integer.MIN_VALUE;
    public List<String> removeInvalidParentheses(String s) {
        /**
         * 后面补充剪枝 除了上面两个，还可以提前计算出无效的左右括号
         * 减完这个枝后 7ms 71.16%
         */
        int le = 0;
        int re = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '('){
                le++;
            }else if(c == ')'){
                if(le==0){
                    re++;
                }else{
                    le--;
                }
            }
        }
        doRemoveInvalidParentheses(s,0,0,0,le,re);
        return result;
    }

    public void doRemoveInvalidParentheses(String s,int start,int lc,int rc,int le,int re){
        if(s.length()<max||le<0||re<0){
            return ;
        }
        if(start==s.length()){
            if(lc==rc&&!set.contains(s)){
                if(s.length()>max){
                    result.clear();
                    result.add(s);
                    max = s.length();
                    set.add(s);
                }else if(s.length()==max){
                    result.add(s);
                    set.add(s);
                }
            }
            return;
        }

        if(start<s.length()){
            char cur = s.charAt(start);
            while(cur!='('&&cur!=')'){
                start++;
                if(start>=s.length()){
                    break;
                }
                cur = s.charAt(start);
            }
            if(start>=s.length()&&lc==rc){
                doRemoveInvalidParentheses(s,start,lc,rc,le,re);
            }

            if(cur=='('){
                doRemoveInvalidParentheses(s,start+1,lc+1,rc,le,re);
                String s1 = s.substring(0,start)+s.substring(start+1);
                doRemoveInvalidParentheses(s1,start,lc,rc,le-1,re);
            }

            if(cur==')'){
                if(lc>rc){
                    doRemoveInvalidParentheses(s,start+1,lc,rc+1,le,re);
                }
                String s1 = s.substring(0,start)+s.substring(start+1);
                doRemoveInvalidParentheses(s1,start,lc,rc,le,re-1);
            }
        }
    }
}
