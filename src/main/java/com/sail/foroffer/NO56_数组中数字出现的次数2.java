package com.sail.foroffer;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *  
 *
 * 限制：
 *
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO56_数组中数字出现的次数2 {

    public static void main(String[] args) {
        int[] nums = new int[]{3,4,3,3};
        System.out.println(singleNumber(nums));
    }

    /**
     * 17ms 35.30%
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int[] bits = new int[32];

        for(int i=0;i<nums.length;i++){
            int l=1;
            int posi = 0;
            while(l!=0){
                if((nums[i]&l)!=0){
                    bits[posi]++;
                }
                l = l<<1;
                posi++;
            }
        }
        int result = 0;

        for(int i=0;i<bits.length;i++){
            bits[i] = bits[i]%3;
            result =result | (bits[i]<<i);
        }
        return result;

    }
}
