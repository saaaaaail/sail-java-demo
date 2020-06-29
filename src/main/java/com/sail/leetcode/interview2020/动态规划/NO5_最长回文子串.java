package com.sail.leetcode.interview2020.动态规划;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO5_最长回文子串 {

    public static void main(String[] args) {
            longestPalindrome2("aba");
    }

    /**
     * 马拉车算法 就是当计算出以i为中心店向右延申的超远（长度为p[i]，p[i]表示回文子串单边的长度）的回文子串的时候。
     * 那么从i到i+p[i]这个范围内的以某一点计算回文子串的时候，能利用到上面计算出来的子串的对称性，更快的计算当前点的回文子串长度
     *
     * 首先，字符串每个字符添加分割符，保证串始终是一个奇数长度的串，ps：这样一个串的子串的长度与原始串的子串是相同的。
     * 要记录那个回文子串向右延申最远的这一点，要用两个参数来记录，
     * center保存索引位置，记录的是这个回文子串中心的 索引
     * maxRight保存以center中心这一点为起点能延申到右边最远点的 长度，
     * 当地如果有一点i+p[i]比maxRight大了，就得更新center为i，maxRight 为i+p[i]
     * 刚开始呢？
     * 肯定是没有maxRight的，只能一个一个老实的走中心扩散的方法，顺便更新maxRight，center
     * i一直这么向右移动，可以保证center是在当前i的左边
     * 当i《 maxRight时，这时候 i的位置就    center < i < center+maxRight
     * 那么以center为中心的回文串包括了i的位置，那么我们就可以知道已经计算过的一点的的子串臂长，
     * 因为是以center对称，那么i关于center的对称点mirror位于center左边，p[mirror]（即以mirror为中心的回文子串的臂长）一定是计算过的，且mirror位置的字符等于i位置的字符，不然不回文了吖！
     * 首先计算mirror的索引由 （mirror+i）/2=center 则 mirror = 2*center-i
     * 已知了p[mirror]就要去判断p[mirror]与maxRight-i的大小
     *
     * 这俩参数有什么联系不？
     * maxRight-i的大小实际上就是（maxRight对称点减去mirror的大小） 的绝对值，对称的嘛！
     * p[mirror]这个臂展如果比上面这个值小，说明完全包括在了center的回文串中，i与mirror是完全对称的，p[mirror]=p[i]
     * p[mirror]如果比maxRight-i要大，通过mirror、center、i三个点之间对称关系的证明可以知道p[mirror]能大于maxRight-i
     * p[i]最多就相等maxRight了，
     * 因为当mirror最左边的回文串边界a超出了center+maxRight关于center的对称点，
     * 那么a关于mirror对称点b，b又关于center对称点c，c又关于i对称点d，这个d位置超出了center+maxRight，如果与a是相同的话
     * 这是不可能啊，maxRight就是center最右边界了，再往右说明不等了，说明a与d一定是不同的才行，
     * 因此当p[mirror]>maxRight最多最多 p[i] = maxRight-i;
     * 综上所述 p[i]=min(p[mirror],maxRight-i);
     */
    public String longestPalindrome3(String s) {

        String newS = addSplit(s);

        int center=0;
        int maxRight=0;

        int start =0;
        int maxLen=0;

        int[] p = new int[newS.length()];

        for(int i=0;i<newS.length();i++){
            if(i<maxRight){
                int mirror = 2*center-i;
                p[i]=Math.min(p[mirror],maxRight-i);
            }
            //这里+1是p[i]只表示单臂的长度，不包括中央的节点的长度
            int left = i-(1+p[i]);
            int right = i+(1+p[i]);
            //这里之所以还要加个中心扩散，是因为一开始没有maxRight，只能先扩散
            //当p[i]计算出来以后这里就进不去了
            while(left>=0&&right<newS.length()&&newS.charAt(left)==newS.charAt(right)){
                p[i]++;
                left--;
                right++;
            }
            if(i+p[i]>maxRight ){
                maxRight = i+p[i];
                center = i;
            }
            if(p[i]>maxLen){
                maxLen =p[i];
                //这里注意，根据变换后的字符串求原始字符串的起始位置
                start = (i-maxLen)/2;
            }
        }
        return s.substring(start,start+maxLen);
    }
    public String addSplit(String s){
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for(int i=0;i<s.length();i++){
            sb.append(s.charAt(i)).append("#");
        }
        return sb.toString();
    }

    /**
     * 中心扩散算法 33ms 76.85%
     * 也就是遍历字符串，以字符的每一位作为奇数回文串与偶数回文串的中心，记录最大值
     *
     */
    public static String longestPalindrome2(String s) {

        int width = 0;
        int start = 0;
        for(int i=0;i<s.length();i++){
            int even = spread(s,i,i+1);
            int odd = spread(s,i,i);
            if(even>width){
                width = even;
                start =i-(even-1)/2;
            }
            if(odd>width){
                width =odd;
                start = i-(odd)/2;
            }
        }
        return s.substring(start,start+width);
    }

    public static int spread(String s,int l,int r){
        int result = 0;
        while(l>=0&&r<=s.length()-1&&s.charAt(l)==s.charAt(r)){
            result=r-l+1;
            r++;
            l--;
        }
        return result;
    }


    /**
     * 动态规划  175ms 19.41&
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int l = 0;
        int r = 0;
        boolean[][] d = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            d[j][j] = true;
            for (int i = j - 1; i >= 0; i--) {
                d[i][j] = (s.charAt(i) == s.charAt(j)) && (i + 1 == j || d[i + 1][j - 1]);
                if (d[i][j] && j - i > r - l) {
                    l = i;
                    r = j;
                }
            }
        }
        return s.substring(l, r + 1);
    }
}
