package com.sail.leetcode.interview2020.字符串;

import java.util.LinkedList;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO402_移掉K位数字 {

    /**
     *  5ms 92.08%
     * 第二种方法 单调栈或者单调队列，只要求队列中的元素是从小到大的即可，然后一端进出。
     * 首先维持队列的从小到大单调性
     * 如果新来的元素比栈顶的大，直接入栈，判断下一个新来的
     * 新来的元素比栈顶的小，那就得把栈顶的出栈（出栈的元素就是要删除的数），直到找到k个元素
     * 如果字符串中所有字符都入栈了还没到k个，那么目前剩下的元素一定是单调增的，从后往往前依次标记最大的为剩下的元素
     */
    public String removeKdigits(String num, int k) {
        if(num==null||num.length()==0){
            return num;
        }
        if(num.length()==k){
            return "0";
        }

        char[] result = new char[num.length()-k];
        char[] nums = num.toCharArray();
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            if(stack.isEmpty()){
                stack.push(i);
            }else{
                while(nums[stack.peek()]>nums[i]){
                    int index = stack.pop();
                    nums[index]='#';
                    k--;
                    if(k==0||stack.isEmpty()){
                        break;
                    }
                }
                stack.push(i);
            }
            if(k==0){
                break;
            }
        }
        int r = nums.length-1;
        while(k>0){
            if(nums[r]!='#'){
                nums[r--]='#';
                k--;
            }
        }

        int l = 0;
        int zero = 0;
        boolean isHead = false;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!='#'){
                result[l++]=nums[i];
                if(!isHead){
                    if(nums[i]=='0'){
                        zero++;
                    }else{
                        isHead = true;
                    }
                }
            }
        }

        String resStr = String.valueOf(result).substring(zero);
        return resStr.equals("")?"0":resStr;
    }


    /**
     * 第一种方法 暴力法 可以过 98ms 5.46%
     * 这一题就是从前往后找 k个最大的数
     * 暴力法就是 要从前往后遍历，寻找第一个num[i]>num[i+1]的情况，这个i就是要删除的数
     * 找到一个i以后呢，要重新，从前往后遍历，再次寻找第一个num[i]>num[i+1]的情况，重复k遍
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits1(String num, int k) {
        if(num==null||num.length()==0){
            return num;
        }
        if(num.length()==k){
            return "0";
        }

        char[] result = new char[num.length()-k];
        char[] nums = num.toCharArray();

        while(k>0){
            for(int i=0;i<nums.length;i++){
                if(nums[i]=='#'){
                    continue;
                }
                int post = i+1;
                while(post<nums.length&&nums[post]=='#'){
                    post++;
                }

                if(post==nums.length||nums[i]>nums[post]){
                    nums[i]='#';
                    break;
                }
            }
            k--;
        }

        int l = 0;
        int zero = 0;
        boolean isHead = false;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!='#'){
                result[l++]=nums[i];
                if(!isHead){
                    if(nums[i]=='0'){
                        zero++;
                    }else{
                        isHead = true;
                    }
                }
            }
        }

        String resStr = String.valueOf(result).substring(zero);
        return resStr.equals("")?"0":resStr;
    }
}
