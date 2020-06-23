package com.sail.foroffer;

/**
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 *  
 *
 * 示例:
 *
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *  
 *
 * 提示：
 *
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO66_构建乘积数组 {

    /**
     * 2ms 78.56%
     * 左乘积数组、右乘积数组
     * 然后把他们对应位置一乘就ok了
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {

        int[] leftPlus = new int[a.length];
        int[] rightPlus = new int[a.length];
        int plus = 1;
        for(int i=0;i<a.length;i++){
            leftPlus[i] = plus;
            plus *= a[i];
        }
        plus=1;
        for(int i=a.length-1;i>=0;i--){
            rightPlus[i] = plus;
            plus *= a[i];
        }

        int[] result = new int[a.length];
        for(int i=0;i<result.length;i++){
            result[i]=leftPlus[i]*rightPlus[i];
        }
        return result;
    }
}
