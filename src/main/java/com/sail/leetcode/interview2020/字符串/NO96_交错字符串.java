package com.sail.leetcode.interview2020.字符串;

/**
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO96_交错字符串 {

    /**
     * dp 10ms 19.75%
     * d[i][j]表示s1 0~i 与s2 0~j 的数能组合成s3的i+j的数
     * d[i][j] = d[i-1][j] ifs1第i个数等于s3的i+j的数
     * d[i][j] = d[i][j-1] ifs2第j个数等于s3的i+j的数
     * 然后边界条件是从0开始循环，s1与s3有相等的字符的d[i][0]=true，不等的话直接跳出
     * 从0开始循环，s2与s3相等的字符的d[0][j]=true，直到两个字符不等，跳出
     * 还有限制条件，对于s1的长度加上s2的长度不等于s3的长度的话，直接返回false
     *
     */
    public boolean isInterleaveDp(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        boolean[][] d = new boolean[s1.length()+1][s2.length()+1];
        d[0][0]=true;
        for(int i=1;i<=s1.length();i++){
            if(s1.charAt(i-1)==s3.charAt(i-1)){
                d[i][0] = true;
            }else{
                break;
            }
        }
        for(int j=1;j<=s2.length();j++){
            if(s2.charAt(j-1)==s3.charAt(j-1)){
                d[0][j] = true;
            }else{
                break;
            }
        }
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                if(s1.charAt(i-1)==s3.charAt(i+j-1)){
                    d[i][j] = d[i][j] || d[i-1][j];
                }
                if(s2.charAt(j-1)==s3.charAt(i+j-1)){
                    d[i][j] = d[i][j] || d[i][j-1];
                }
            }
        }
        return d[s1.length()][s2.length()];
    }

    /**
     * dfs 妥妥的超时了  优化一下，
     * 加上记忆化搜索可以过 4ms 73.95%
     * 记忆什么东西呢？
     * 记录成功的值吗？不行的，只有当遍历完了才能知道成功的值
     * 因此需要记录的是失败的值，对于已经计算过的l1、l2、l3如果对其递归的结果是失败的就可以暂存起来
     * 可以直接使用三维数组存亦可以使用二维存
     *
     * dfs思路就是每次从s1或者s2选择一个字符，并从s3选择一个字符
     * 如果有相等的就向后推进，都不相等直接返回false
     * 直到三个字符串都刚好走完
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
//    private boolean[][][] d ;
    private boolean[][] d ;
    public boolean isInterleave(String s1, String s2, String s3) {
//        d = new boolean[s1.length()][s2.length()][s3.length()];
        /**
         * 使用二维暂存哪个l1与l2的位置之和的递归都是失败的，不用暂存l3是因为l3=l1+l2
         */
        d = new boolean[s1.length()][s2.length()];
        return doIsInterleave(s1,0,s2,0,s3,0);
    }

    public boolean doIsInterleave(String s1,int l1,String s2,int l2,String s3,int l3){
        if(s1.length()==l1&&s2.length()==l2&&s3.length()==l3){
            return true;
        }
        //三维数组暂存失败方案，直接返回
//        if(l1<s1.length()&&l2<s2.length()&&l3<s3.length()&&d[l1][l2][l3]){
//            return false;
//        }
        //二维暂存
        if(l1<s1.length()&&l2<s2.length()&&d[l1][l2]){
            return false;
        }
        boolean result = false;
        if(l1<s1.length()&&l3<s3.length()&&s1.charAt(l1)==s3.charAt(l3)){
            result = result || doIsInterleave(s1,l1+1,s2,l2,s3,l3+1);
        }
        if(result){
            return result;
        }
        if(l2<s2.length()&&l3<s3.length()&&s2.charAt(l2)==s3.charAt(l3)){
            result = result || doIsInterleave(s1,l1,s2,l2+1,s3,l3+1);
        }

        //三维数组暂存失败的情况
//        if(l1<s1.length()&&l2<s2.length()&&l3<s3.length()){
//            d[l1][l2][l3] = !result;
//        }
        //二维暂存
        if(l1<s1.length()&&l2<s2.length()){
            d[l1][l2] = !result;
        }

        return result;
    }
}
