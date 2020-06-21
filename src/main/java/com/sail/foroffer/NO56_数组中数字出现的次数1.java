package com.sail.foroffer;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 *
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *  
 *
 * 限制：
 *
 * 2 <= nums.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO56_数组中数字出现的次数1 {


    /**
     * 思路就是
     * 异或操作
     * 相同为0、不同为1、0与任何数异或都是数本身
     * 那么数组里的数所有异或一遍 就等于 两个不同的数异或的结果
     * 寻找得到的数二进制里为1的某一位i，这一位i说明，不同的两个数这一位i是不同，一个为1，一个为0
     * 那么对整个数组重新划分，i这一位为0的移到数组前面，为1的移到数组后面
     * 分别对两块区域异或，就能求出这两个数
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int tmp = 0;
        for(int i=0;i<nums.length;i++){
            tmp ^=nums[i];
        }
        int bitPosi = 1;
        while(((bitPosi & tmp) == 0)&&bitPosi!=0){
            bitPosi = bitPosi<<1;
        }
        int l=0;
        int r = nums.length-1;
        while(l<r){
            while((bitPosi & nums[l])==0 && l<r){
                l++;
            }
            while((bitPosi & nums[r])!=0 && l<r){
                r--;
            }
            if(l>=r){
                break;
            }
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
        }
        int part = 0;
        if((bitPosi & nums[l])==0){
            part = l+1;
        }else{
            part = l;
        }
        int[] result = new int[2];
        for(int i=0;i<part;i++){
            result[0] ^=nums[i];
        }
        for(int i=part;i<nums.length;i++){
            result[1] ^=nums[i];
        }
        return result;


    }
}
