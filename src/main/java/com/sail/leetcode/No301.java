package com.sail.leetcode;

import java.util.*;

/**
 * @program: No301
 * @description: 301. 删除无效的括号
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 *
 * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
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
 * @author: sail
 * @create: 2019/5/2 15:34
 */

public class No301 {

    private static String start;
    private static Set<String> set;//去除删除括号后的重复结果
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        start = sc.nextLine();
        set = new HashSet<>();
        No301 no301 = new No301();
        System.out.println(no301.removeInvalidParentheses(start));

    }
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();

        removeRight(res,s);
        return  res;
    }
    public void removeRight(List<String> res,String s){
        LinkedList st = new LinkedList();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)=='('){
                st.push(s.charAt(i));
            }else if (s.charAt(i)==')'){
                if (st.isEmpty()){
                    /**
                     * 发现存在多余的括号，因此从0到i处删去其中一个右括号即可
                     * 因此有几个括号就有几种删除方法，但是会有重复
                     */
                    for (int j=i;j>=0;j--){
                        if (s.charAt(j)==')'){
                            String stmp = s.substring(0,j)+s.substring(j+1);//删除j
                            System.out.println(stmp);
                            if (!set.contains(stmp)){//去重
                                set.add(stmp);
                                removeRight(res,stmp);
                            }
                        }
                    }
                    return;

                }else{
                    st.pop();
                }
            }
        }
        if (st.isEmpty()){
            res.add(s);
        }
        /**
         * 对于没有添加到结果集的s，说明栈不空
         * 要删除多余的左括号，因此在此处对每个s处理
         */
        removeLeft(res,s);

    }

    public void removeLeft(List<String> res,String s){
        LinkedList st = new LinkedList();
        for (int i=s.length()-1;i>=0;i--){
            if (s.charAt(i)==')'){
                st.push(s.charAt(i));
            }else if (s.charAt(i)=='('){
                if (st.isEmpty()){
                    /**
                     * 发现存在多余的括号，因此从0到i处删去其中一个右括号即可
                     * 因此有几个括号就有几种删除方法，但是会有重复
                     */
                    for (int j=i;j<s.length();j++){
                        if (s.charAt(j)=='('){
                            String stmp = s.substring(0,j)+s.substring(j+1);//删除j
                            if (!set.contains(stmp)){//去重
                                set.add(stmp);
                                removeLeft(res,stmp);
                            }
                        }

                    }
                    return;

                }else{
                    st.pop();
                }
            }
        }
        if (st.isEmpty()){
            if (!res.contains(s)){
                res.add(s);
            }
        }
    }
}
