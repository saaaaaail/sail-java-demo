package com.sail.leetcode.interview2020.动态规划;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO139_单词拆分 {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("apple");
        words.add("apple");
        words.add("pen");

        boolean applepenapple = wordBreak1("applepenapple", words);
        System.out.println(applepenapple);
    }

    /**
     * 思路就是背包问题，对字符字典里字符串的排序
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] d = new boolean[s.length()+1];

        d[0]=true;

        for(int j=1;j<=s.length();j++){
            for(int k=j-1;k>=0;k--){
                String tmp = s.substring(k,j);
                if(set.contains(tmp)){//将字符集放在最里层，那么就是对字符集单词种类的排序，排序意味着可以重复使用
                    d[j] = d[j] || d[j-tmp.length()];
                }
            }
        }
        return d[s.length()];
    }

    public static boolean wordBreak2(String s, List<String> wordDict){
        boolean[] pre = new boolean[s.length()+1];
        return doWordBreak2(s,0,wordDict,pre,new boolean[wordDict.size()]);
    }

    /**
     * dfs 1ms 99%
     * pre是为了暂存，0~i的匹配结果，如果这个值已经匹配为true了就没必要往后进行了
     * visited是为了限制单词使用一次还是无限次，本题是允许单词无限使用
     *
     * @param s
     * @param start
     * @param wordDict
     * @param pre
     * @param visited
     * @return
     */
    public static boolean doWordBreak2(String s,int start,List<String> wordDict,boolean[] pre,boolean[] visited){
        for(int i=0;i<wordDict.size();i++){
//            if(visited[i]){
//                continue;
//            }
//            visited[i]=true;
            String word = wordDict.get(i);
            int nextStart = start+word.length();
            if(nextStart>s.length()||pre[nextStart]){
                continue;
            }
            /**
             * 使用indexOf来判断从start开始有没有这个word，没有返回-1，有返回起始值
             * 学到了没，不用切分字符串了噢
             */
            if(s.indexOf(word,start)==start){
                pre[nextStart]=true;
                if(nextStart==s.length()||doWordBreak2(s,nextStart,wordDict,pre,visited)){
                    return true;
                }
            }
//            visited[i] = false;
        }
        return false;
    }


}
