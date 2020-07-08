package com.sail.leetcode.interview2020.动态规划;

import java.util.Arrays;

/**
 * 判断一个字符串能否被数组里给定的字符串拼接而成
 */
public class 自定题_从字符串数组里选字符组合成目标字符串 {

    public static void main(String[] args) {
        String s = "applepenapple";

        String[] strs = new String[]{"pen"};
        System.out.println(connectByStr(s,strs));
    }
    /**
     * 这是一个0/1背包问题
     * d[k][i]表示字符串0~k个字符串就能拼接成i长度的目标字符串
     * d[k][i]=d[k-1][i] || d[k-1][i-str.length()]
     * 选与不选的策略保证了 字符数组每个字符只会被选到一次
     * @param s
     * @param
     * @return
     */
    public static boolean connectByStr(String s,String[] oldStrs){
        boolean[][] d = new boolean[oldStrs.length+1][s.length()+1];
        for(int i=0;i<d.length;i++){
            d[i][0]=true;
        }
        Arrays.fill(d[0], true);
        int l=0;
        String[]  strs = new String[oldStrs.length+1];
        System.arraycopy(oldStrs,0,strs,1,oldStrs.length);
        for(int j=1;j<=s.length();j++){//背包大小
            for(int i=1;i<strs.length;i++){//这个是填充数

                /**
                 * 这里上面两个循环在二维数组情况下，可以内外替换，没有影响，因为记录了i-1的状态，i的就不会有影响
                 * 下面这个是原始字符串s中 从0 到 j 长度的字符串 以j位置为末尾，向前面寻找与 strs[i]字符串相同的字符串去做匹配
                 * 找到相同的字符串了就能使用长度做状态转移到少一个字符的状态了
                 */
                for (int k=j-1;k>=0;k--){
                    if(j-k==strs[i].length()){
                        String tmpS = s.substring(k,j);
                        if(strs[i].equals(tmpS)){
                            d[i][j] = d[i][j] || d[i-1][j] || d[i-1][j-strs[i].length()];
                        }else{
                            d[i][j] = d[i][j] || d[i-1][j] ;
                        }
                    }
                }
            }
        }
        return d[strs.length-1][s.length()];
    }
}
