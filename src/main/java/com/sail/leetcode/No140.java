package com.sail.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: JavaDemo
 * @description: 单词拆分 2
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 *
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 *
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 *
 * @author: sail
 * @create: 2019/05/05 13:37
 */

public class No140 {
    boolean[] dn ;
    int maxLen;
    public static void main(String[] args) {
        No140 no140 = new No140();
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        System.out.println(no140.wordBreak(s,wordDict));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        dn = new boolean[s.length()+1];
        dn[0]=true;
        maxLen=0;
        for (int i=0;i<wordDict.size();i++){
            set.add(wordDict.get(i));
            if (maxLen<wordDict.get(i).length()){
                maxLen=wordDict.get(i).length();
            }
        }
        //set ok
        wordCut(s,0,set,list,res);
        return res;
    }

    public void wordCut(String s,int pos,Set<String> dict,List<String> list,List<String> res){
        if (pos==s.length()){
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<list.size();i++){
                sb.append(list.get(i));
                if(i!=list.size()-1){
                    sb.append(" ");
                }
            }
            res.add(sb.toString());
            return;

        }
        for (int i=pos+1;i<=s.length()&&i-pos<=maxLen;i++){
            String tmp = s.substring(pos,i);
            if (dict.contains(tmp)){
                dn[i]=dn[pos]&&dict.contains(tmp);
                list.add(tmp);
                wordCut(s,i,dict,list,res);
                list.remove(list.size()-1);
            }else {
                dn[i]=dn[pos]&&dict.contains(tmp);
            }

        }
    }
}
