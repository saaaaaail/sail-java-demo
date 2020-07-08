package com.sail.leetcode.interview2020.动态规划;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO72_编辑距离 {

    /**
     *      思路dp 9ms 20%
     *     d[i][j]表示word1的0~i位转换到word2的0~j位最小要的步数
     *     有三种修改方式分别是，替换、新增、删除
     *     关于替换word1(0~i-1)到word2(0~j-1)需要k步，word1(0~i)到word2(0~j)需要多少步?如果word1(i)与word2(j)不一样，替换一下为k+1步，如果word1(i)与word2(j)一样，仅需要k步即可)
     *     关于新增word1(0~i)到word2(0~j-1)需要k步，word1(0~i)到word2(0~j)需要多少步?少了word2(j)这个字符，新增即可，所以总共需要k+1步
     *     关于删除word1(0~i-1)到word2(0~j)需要k步，word1(0~i)到word2(0~j)需要多少步?多了word1(i)这个字符，删掉即可，总共需要k+1步
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] d = new int[word1.length()+1][word2.length()+1];
        /**
         * 初始化 从0~0需要0步
         */
        d[0][0]=0;
        /**
         * 初始化 从i到0需要删减i步
         */
        for(int i=1;i<=word1.length();i++){
            d[i][0]=i;
        }
        /**
         * 初始化 从0到j需要新增j步
         */
        for(int j=0;j<=word2.length();j++){
            d[0][j]=j;
        }
        for(int i=1;i<=word1.length();i++){
            for(int j=1;j<=word2.length();j++){
                d[i][j] = Integer.MAX_VALUE;
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    d[i][j] = Math.min(d[i][j],d[i-1][j-1]);
                }else{
                    d[i][j] = Math.min(d[i][j],d[i-1][j-1]+1);
                }
                d[i][j] = Math.min(d[i][j],d[i-1][j]+1);
                d[i][j] = Math.min(d[i][j],d[i][j-1]+1);
            }
        }
        return d[word1.length()][word2.length()];
    }
}
