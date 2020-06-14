package com.sail.leetcode;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 字符串重新排列
 * 给定一个原始字符串，统计字符串中各字符出现的次数，并按照ASCII码递增的顺序依次输出。
 *
 * 例：原始字符串为eeefgghh
 *
 * 统计字符串中各字符的出现次数：
 *
 *         e : 3
 *
 *         f  : 1
 *
 *         g : 2
 *
 *         h : 2
 *
 * 重排字符串输出为：efgheghe
 *
 * 说明：给定的原字符串只包含数字和字母，大写字母和小写字母存在区别。
 *
 * 样例输入输出：
 *
 * eeefgghh
 *
 * efgheghe
 *
 * @author: sail
 * @create: 2019/05/07 20:39
 */

public class ReRankString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        ReRankString re = new ReRankString();
        System.out.println(re.reRankStr(str));
    }

    public String reRankStr(String str){
        int[] nums = new int[26];

        for (int i=0;i<str.length();i++){
            char c = str.charAt(i);
            nums[c-'a']++;
        }
        boolean done=true;
        StringBuilder sb = new StringBuilder();
        while (done){
            done=false;
            for (int i=0;i<nums.length;i++){
                if (nums[i]!=0){
                    sb.append((char)(i+'a'));
                    nums[i]--;
                    done=true;
                }
            }
        }
        return sb.toString();


    }
}
