package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 替换空格
 * 请实现一个函数，把字符串中的每个空格替换成"%20"。
 * 例：输入“we are happy.”，
 * 输出“we%20are%20happy.”
 * @author: sail
 * @create: 2019/05/30 11:13
 */

public class NO5_替换空格 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strs = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<strs.length;i++){
            sb.append(strs[i]);
            if (i!=strs.length-1){
                sb.append("%20");
            }
        }
        System.out.println(sb.toString());
    }
}
