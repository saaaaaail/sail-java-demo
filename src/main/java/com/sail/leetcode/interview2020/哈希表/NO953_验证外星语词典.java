package com.sail.leetcode.interview2020.哈希表;

/**
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 *
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * 输出：true
 * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
 * 示例 2：
 *
 * 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * 输出：false
 * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
 * 示例 3：
 *
 * 输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * 输出：false
 * 解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/verifying-an-alien-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO953_验证外星语词典 {

    /**
     * []
     * ""
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(isAlienSorted(new String[]{"hello","leetcode"},"hlabcdefgijkmnopqrstuvwxyz"));
    }

    /**
     * 0ms 100%
     * 使用数组存一下新的字符的序号，然后自己编写字符串的比较方法
     */
    private static int[] orderNum = new int[26];
    public static boolean isAlienSorted(String[] words, String order) {

        for(int i=0;i<order.length();i++){
            char c = order.charAt(i);
            orderNum[c-'a']=i;
        }
        for(int i=0;i<words.length;i++){
            if(i+1>=words.length){
                break;
            }
            if(!compare(words[i],words[i+1])){
                return false;
            }
        }
        return true;
    }

    public static boolean compare(String s1,String s2){
        int l1=0;
        int l2=0;
        while(l1<s1.length()&&l2<s2.length()){
            char c1 = s1.charAt(l1++);
            char c2 = s2.charAt(l2++);
            if(orderNum[c1-'a']>orderNum[c2-'a']){
                return false;
            }else if(orderNum[c1-'a']<orderNum[c2-'a']){
                return true;
            }
        }
        if(l1<s1.length()){
            return false;
        }
        return true;
    }
}
