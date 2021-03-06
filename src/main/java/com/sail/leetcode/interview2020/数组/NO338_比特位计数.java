package com.sail.leetcode.interview2020.数组;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO338_比特位计数 {

    private int[] d;

    /**
     * 4ms 17.28%
     * 思路是搞一个数组暂存之前的计算结果，对于2的n次方，1的数量为1
     * 比如记录1里面1的个数是1
     * 2里面1的个数是1
     * 3里面1的个数是2
     * 4里面1的个数是1
     * 5里面1的个数就是4+1，替换一下4里面1的个数是1，1里面1的个数是1，所以5里面1的个数是2
     * 6 = 4+2 4数字里面1的个数为1，2的个数为1，所以6数字里面1的个数为2
     * 7 = 4+3 4数字里面1个数为1，3里面1的个数为2，所以7里面1的个数为3
     * 8是2的三次方，所以为1
     * 9 = 8+1 所以是1+1=2
     * 10 = 8+2 所以1+1=2
     * 11 = 8+3 所以1+2 = 3
     * 依次类推，，，，，每个数都寻找离他最近的2的次方加上一个额外的数
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        d = new int[num+1];
        d[0] = 0;
        int t = 1;
        for(int i=1;i<=num;i++){
            if(i==t){
                t*=2;
                d[i] = 1;
            }else{
                d[i] = d[t/2]+d[i-t/2];
            }
        }
        return d;
    }
}
