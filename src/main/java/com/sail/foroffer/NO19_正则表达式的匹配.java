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
     * 第一种递归思路
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

    /**
     * 第二种递归思路
     */
    public boolean matchCore2(int ls,int lp,String match,String pattern){
        /**
         * 唯一匹配成功条件
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
         * lp+1位置有*字符
         */
        if(lp+1<pattern.length()&&pattern.charAt(lp+1)=='*'){
            /**
             * 如果s已经匹配到末尾，则直接跳过字符+*的组合
             */
            if(ls==match.length()){
                return matchCore(ls,lp+2,match,pattern);
            }

            /**
             * 如果lp的字符等于ls处的字符，则有两种情况
             * 1.一种是s后移一位，p位置不变，继续使用当前的pattern比较字符串
             * 2.另一种pattern后移两位跳过这个字符*结构，s位置不变
             * 如果lp的字符串不等与ls的字符，那就只有一种情况
             * 就只能跳过这个【字符*】的pattern，后移两位
             */
            return match.charAt(ls)==pattern.charAt(lp)||pattern.charAt(lp)=='.'?matchCore(ls,lp+2,match,pattern) || matchCore(ls+1,lp,match,pattern):matchCore(ls,lp+2,match,pattern);
        }

        /**
         * 这里加个校验，如果ls已经到了字符串的末尾，并且当前lp+1的位置也不是*，则可以认为匹配失败了
         */
        if(ls==match.length()&&lp!=pattern.length()){
            return false;
        }

        if(lp<pattern.length()&&ls<match.length()&&pattern.charAt(lp)==match.charAt(ls)||lp<pattern.length()&&pattern.charAt(lp)=='.'){
            return matchCore(ls+1,lp+1,match,pattern);
        }
        return false;
    }

    /**
     * 动态规划思路
     * 使用f[i][j]表示字符串前i个字符的串与模式串前j个字符的串是否匹配、
     * 1.当字符串match末尾字符等于模式串pattern末尾字符
     * f[i][j]的状态值就等于f[i-1][j-1]
     * 2.当模式串pattern末尾字符等于【.】,则f[i][j]=f[i-1][j-1]
     * 3.当模式串pattern末尾字符等于【*】,则分为俩情况
     *   3.1如果j-1的字符等于【.】或者i的字符等于j-1的字符则可以选择看或者不看
     *     3.1.1 看, 则f[i][j]=f[i-1][j]
     *     3.1.2 不看, 则f[i][j]=f[i][j-2]
     *   3.2 如果不满足3.1的情况则只能选择不看
     *     f[i][j]=f[i][j-2]
     * @return
     */
    public boolean matchCore3(String s,String p){
        int n = s.length();
        int m = p.length();
        boolean[][] f = new boolean[n+1][m+1];

        f[0][0]=true;
        for(int i=1;i<n+1;i++){
            f[i][0]=false;
        }
        /**
         * i从0开始是因为i=0的值f[0][j]仍然需要视情况计算
         */
        for(int i=0;i<n+1;i++){
            /**
             * j从1开始是因为j=0的值f[i][0]值都可以直接取到了
             */
            for(int j=1;j<m+1;j++){
                if(p.charAt(j-1)!='*'){
                    /**
                     * 要求i>=1才行，即match不能是空串，否则这种情况匹配就是false
                     */
                    if(i>=1&&(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.')){
                        f[i][j] |=f[i-1][j-1];
                    }
                }else if(j>=2){
                    if(i>=1&&(p.charAt(j-2)=='.'|| p.charAt(j-2)==s.charAt(i-1))){
                        /**
                         * 这里 也要求i>=1才行，否则空串没法比较match有没有重复字符与pattern*前面的字符相同
                         */
                        f[i][j] |=f[i-1][j]|f[i][j-2];
                    }else{
                        /**
                         * 这里的情况就是 只能不看【字符*】
                         */
                        f[i][j] |=f[i][j-2];
                    }
                }
            }

        }

        return f[n][m];
    }
}
