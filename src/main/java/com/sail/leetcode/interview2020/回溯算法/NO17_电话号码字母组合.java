package com.sail.leetcode.interview2020.回溯算法;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO17_电话号码字母组合 {



    List<String> result = new ArrayList<>();
    String[] str = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    /**
     * 使用字符串拼接递归效率太低了。一直使用数组递归即可
     * 思路就是
     * 对每一位数字进行递归，每一位数字使用for循环进行替换，替换完往下一层走
     * for循环结束以后往上层递归的时候，要将该层数组对应索引位置的字符还原
     * 不用去重，因为每个数字之间没有联系
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if(digits==null||digits.length()==0){
            return result;
        }
        char[] dc = digits.toCharArray();
        doLetterCombinations(dc,0);
        return result;
    }

    public void doLetterCombinations(char[] dc,int index){
        if(index == dc.length){
            return ;
        }
        char c = dc[index];
        String digitStr = str[c-'0'];

        for(int i=0;i<digitStr.length();i++){
            char digit = digitStr.charAt(i);

            dc[index] = digit;
            if(index+1 >= dc.length){

                result.add(new String(dc));
                continue;
            }else{
                doLetterCombinations(dc,index+1);
            }
        }
        /**
         * 强调: 回溯结束的时候要对数组进行还原
         */
        dc[index] = c;
    }
}
