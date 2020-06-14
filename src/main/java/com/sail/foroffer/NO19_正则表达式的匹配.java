package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 正则表达式的匹配
 * 请实现一个函数用来匹配包含'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，
 * 而'*'表示它前面的字符可以出现任意次(含0次)。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例：
 * 字符串"aaa"与模式"a.a"和"ab*ac*a"均匹配，
 * 但与"aa.a"和"ab*a"均不匹配
 * @author: sail
 * @create: 2019/06/03 15:56
 */

public class NO19_正则表达式的匹配 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine();
        String match = sc.nextLine();
        System.out.println(matchCore(0,0,pattern,match));
    }

    public static boolean matchCore(int pl,int ml,String pattern,String match){
        if (pl==pattern.length()&&ml==match.length()){
            return true;
        }
        if (pl!=pattern.length()&&ml==match.length()||pl==pattern.length()&&ml!=match.length()){
            return false;
        }
        if (pl+1<pattern.length()&&pattern.charAt(pl+1)=='*'){
            if (pattern.charAt(pl)=='.'){//".*"
                /**
                 * 注意".*"的匹配，要么
                 * 与match首字母匹配，要么
                 * 跨过".*"
                 * 两种情况相或
                 */
                char firstc = match.charAt(ml);
                int iml=ml;
                while (match.charAt(iml)==firstc){
                    iml++;//跳过所有重复的字符
                }
                return matchCore(pl+2,ml,pattern,match)
                        ||matchCore(pl+2,iml,pattern,match);
            }else {
                int iml=ml;
                while (pattern.charAt(pl)==match.charAt(iml)){
                    iml++;//跳过所有重复的字符
                }
                return matchCore(pl+2,iml,pattern,match)
                        ||matchCore(pl+2,ml,pattern,match);
            }

        }

        if (pattern.charAt(pl)==match.charAt(ml)||pattern.charAt(pl)=='.'){
            return matchCore(pl+1,ml+1,pattern,match);
        }
        return false;

    }

}
