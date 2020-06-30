package com.sail.foroffer;

/**
 *写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 *
 *  
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 *
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO67_把字符串转成整数 {

//    public static void main(String[] args) {
//        System.out.println(strToInt("2147483648"));
//    }

    /**
     * 思路很简单就是先去除空格，过滤英文字母开头的，然后判断正负，然后将剩下的数转成long型
     * @param str
     * @return
     */
    public int strToInt(String str) {
        str = str.trim();
        if(str==null||str.length()==0){
            return 0;
        }
        char[] c = str.toCharArray();
        boolean isPositive = true;
        int l = 0;
        if(c[l]=='+'||c[l]=='-'||c[l]>='0'&&c[l]<='9'||c[l]>='a'&&c[l]<='z'){
            if(c[l]=='+'){
                isPositive=true;
                l++;
            }else if(c[l]=='-'){
                isPositive =false;
                l++;
            }
            if(l>=str.length()||str.charAt(l)<'0'||str.charAt(l)>'9'){
                return 0;
            }

            int result = 0;
            while(l<str.length()&&str.charAt(l)>='0'&&str.charAt(l)<='9'){
                /**
                 * 这里有没有溢出的判断，
                 * 如果是正数溢出以后就是负数了。
                 * 如果是负数溢出以后值就丢失了。
                 * 所以用tmp先预先计算一下，然后还原值
                 */
                int tmp = result*10+(str.charAt(l)-'0');
                if(result>0&&tmp<0||(tmp-(str.charAt(l)-'0'))/10!=result){
                    if (isPositive){
                        return Integer.MAX_VALUE;
                    }else{
                        return Integer.MIN_VALUE;
                    }
                }
                result = 10*result+(str.charAt(l)-'0');
                l++;
            }
            if(!isPositive){
                result =-result;
                return (int) result;
            }

            return (int) result;
        }
        return 0;
    }
}
