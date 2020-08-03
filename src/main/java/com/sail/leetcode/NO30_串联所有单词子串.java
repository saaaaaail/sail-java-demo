package com.sail.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 *
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO30_串联所有单词子串 {
    public static void main(String[] args) {
        NO30_串联所有单词子串 t = new NO30_串联所有单词子串();
        t.findSubstring("mississippi",
                new String[]{"is"});
    }

    private List<Integer> result = new ArrayList<>();
    private boolean[] d;
    public List<Integer> findSubstring(String s, String[] words) {
        if(s.equals("")||words.length==0){
            return result;
        }
        boolean[] visited = new boolean[words.length];
        d = new boolean[s.length()];
        int l=0;
        while(l<s.length()){
            doFindSubstring(l,l,s,words,visited,0);
            l++;
        }

        return result;
    }

    public void doFindSubstring(int start,int index,String s,String[] words,boolean[] visited,int wordCount){
        if(d[start]){
            return ;
        }
        if(wordCount==words.length){
            result.add(start);
            d[start] = true;
            return ;
        }


        for(int i=0;i<words.length;i++){
            if(visited[i]){
                continue;
            }
            visited[i]=true;
            int len = words[i].length();
            int si = s.indexOf(words[i],index);
            if(si!=-1){
                if(index!=si){
                    doFindSubstring(si,si+len,s,words,visited,1);
                }else{
                    doFindSubstring(start,si+len,s,words,visited,wordCount+1);
                }
            }
            visited[i]=false;
        }

    }
}
