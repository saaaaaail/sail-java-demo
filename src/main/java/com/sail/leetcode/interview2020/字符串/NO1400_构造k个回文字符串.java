package com.sail.leetcode.interview2020.字符串;

/**
 * 给你一个字符串 s 和一个整数 k 。请你用 s 字符串中 所有字符 构造 k 个非空 回文串 。
 *
 * 如果你可以用 s 中所有字符构造 k 个回文字符串，那么请你返回 True ，否则返回 False 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "annabelle", k = 2
 * 输出：true
 * 解释：可以用 s 中所有字符构造 2 个回文字符串。
 * 一些可行的构造方案包括："anna" + "elble"，"anbna" + "elle"，"anellena" + "b"
 * 示例 2：
 *
 * 输入：s = "leetcode", k = 3
 * 输出：false
 * 解释：无法用 s 中所有字符构造 3 个回文串。
 * 示例 3：
 *
 * 输入：s = "true", k = 4
 * 输出：true
 * 解释：唯一可行的方案是让 s 中每个字符单独构成一个字符串。
 * 示例 4：
 *
 * 输入：s = "yzyzyzyzyzyzyzy", k = 2
 * 输出：true
 * 解释：你只需要将所有的 z 放在一个字符串中，所有的 y 放在另一个字符串中。那么两个字符串都是回文串。
 * 示例 5：
 *
 * 输入：s = "cr", k = 7
 * 输出：false
 * 解释：我们没有足够的字符去构造 7 个回文串。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s 中所有字符都是小写英文字母。
 * 1 <= k <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-k-palindrome-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO1400_构造k个回文字符串 {
    //
    //15
    public static void main(String[] args) {
        NO1400_构造k个回文字符串 t = new NO1400_构造k个回文字符串();
        boolean res = t.canConstruct("qlkzenwmmnpkopu", 15);
        System.out.println(res);
    }

    /**
     * 8ms 43.35%
     * 这一题其实与回文没有关系
     * 主要是统计字符的单双的个数，以满足拼成回文的要求即可
     * 首先是统计成对的字符数量pairs
     * 统计成单的字符数量odds
     * 首先如果odds比k大，那么无论pairs是多少都无法满足回文的要求了，所以odds必须<=k
     * 然后看pairs，如果pairs的数量 加上 odds比k还小则肯定拼不出k个
     * 所以pairs+odds>=k即可，但是这个漏掉了一种情况，
     * pairs也可以拆成单个字符，所以对pairs的要求也放缓为之前的1/2即可
     * 满足2*pairs+odds>=k的即可
     *
     * @param s
     * @param k
     * @return
     */
    public boolean canConstruct(String s, int k) {
        int[] cache = new int[26];
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            cache[c-'a']++;
        }
        int pairs = 0;
        int odds = 0;

        for(int i=0;i<cache.length;i++){
            if(cache[i]!=0){
                pairs+=cache[i]/2;
                odds+=cache[i]%2;
            }
        }

        if(2*pairs+odds>=k&&odds<=k){
            return true;
        }

        return false;
    }
}
