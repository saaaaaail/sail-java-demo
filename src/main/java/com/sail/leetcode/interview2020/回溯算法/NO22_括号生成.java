package com.sail.leetcode.interview2020.回溯算法;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO22_括号生成 {

    /**
     *  1ms 100%
     * 分别记录左右括号，记录索引位置
     * 索引到达末尾，并且左右括号数目相同的，就是满足结果的
     */
    private List<String> list = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        char[] c = new char[2*n];
        doGenerateParenthesis(0,0,c,0);
        return list;
    }

    public void doGenerateParenthesis(int lnum,int rnum,char[] c,int index){
        if(index==c.length){
            if(lnum==rnum){
                list.add(String.valueOf(c));
            }
            return ;
        }
        if(lnum>rnum){
            c[index]=')';
            doGenerateParenthesis(lnum,rnum+1,c,index+1);
        }
        c[index]='(';
        doGenerateParenthesis(lnum+1,rnum,c,index+1);
    }
}
