package com.sail.foroffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO58_翻转单词顺序1 {
    public static void main(String[] args) {

        String s= "a good  example";
        reverseWords(s);
    }

    /**
     * 2ms 88.51%
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        String[] split = s.split(" ");
        List<String> filter = new ArrayList<>();
        for(int i=0;i<split.length;i++){
            if(split[i]!=null&&!"".equals(split[i])){
                filter.add(split[i]);
            }
        }
        split = filter.toArray(new String[0]);
        int l = 0;
        int r = split.length-1;
        while(l<r){

            String tmp = split[l].trim();
            split[l]=split[r].trim();
            split[r]=tmp;
            l++;
            r--;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<split.length;i++){
            sb.append(split[i]).append(" ");
        }

        return sb.toString().trim();
    }
}
