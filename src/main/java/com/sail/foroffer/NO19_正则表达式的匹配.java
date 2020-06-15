package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 正则表达式的匹配
 * 请实现一个函数用来匹配包含'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，
 * 而'*'表示它前面的字符可以出现任意次(含0次)。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例：
 * 字符串"aaa"与模式"a.a"和"ab*ac*a"均匹配，
 * 但与"aa.a"和"ab*a"均不匹配
 * @author: sail
 * @create: 2019/06/03 15:56
 */

public class NO19_正则表达式的匹配 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine();
        String match = sc.nextLine();
        System.out.println(matchCore(0,0,match,pattern));
    }

    /**
     * 递归思路
     * @param ls
     * @param lp
     * @param match
     * @param pattern
     * @return
     */
    public static boolean matchCore(int ls,int lp,String match,String pattern){
        /**
         * 匹配到ls与lp都到达末尾认为匹配成功
         */
        if(ls==match.length()&&lp==pattern.length()){
            return true;
        }
        /**
         * 字符串未到达末尾，模式串已到达末尾，直接认为匹配失败
         */
        if(ls!=match.length()&&lp==pattern.length()){
            return false;
        }
        /**
         * 考虑下一位字符是 * 的情况，这里面包含字符串到达末尾，模式串未到达末尾的情况
         * ps: 【.*】匹配任意字符串
         */
        if(lp+1<pattern.length()&&pattern.charAt(lp+1)=='*'){
            if(pattern.charAt(lp)=='.'){
                /**
                 * 如果模式当前位为. 则允许匹配任意字符串
                 * 考虑字符串从起始ls位置到字符串末尾的匹配依次与pattern匹配的结果
                 * match(ls)||match(ls+1)||match(ls+2)||......||match(match.length())
                 * 只要其中有一个能匹配成功即可
                 */
                boolean result = false;
                for(int i=ls;i<=match.length();i++){
                    result = result || matchCore(i,lp+2,match,pattern);
                }
                return result;
            }else{
                /**
                 * 从字符串ls位置开始寻找连续的与模式串lp位置相同的字符，位置记录到tls中
                 * 然后依次从连续相同的字符ls到tls选一个位置往后匹配，只要一个成功匹配即可
                 * match(ls)||match(ls+1)||match(ls+2)||......||match(tls)
                 * 这里面包含了ls一开始的字符就与模式串不同的情况，这样的ls==tls
                 *
                 */
                int tls = ls;
                char firstC = pattern.charAt(lp);
                boolean result = false;
                while(tls<match.length()&&firstC==match.charAt(tls)){
                    tls++;
                }
                for(int i=ls;i<=tls;i++){
                    result = result || matchCore(i,lp+2,match,pattern);
                }
                return result ;
            }
        }
        /**
         * 对于字符串与模式串相同或模式串为.的情况就同时后移一位
         */
        if(lp<pattern.length()&&ls<match.length()&&pattern.charAt(lp)==match.charAt(ls)||lp<pattern.length()&&pattern.charAt(lp)=='.'){
            return matchCore(ls+1,lp+1,match,pattern);
        }
        return false;
    }
}
