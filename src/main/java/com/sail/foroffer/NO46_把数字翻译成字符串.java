package com.sail.foroffer;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *  
 *
 * 提示：
 *
 * 0 <= num < 231
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO46_把数字翻译成字符串 {


    /**
     * 动规解法
     * d[i]表示走到i有几种走法，当然有一步走过来与两步走过来d[i]=d[i-1]+d[i-2]
     */
    public int translateNumDp(int num) {
        String numStr = String.valueOf(num);
        if(numStr.length()==1){
            return 1;
        }
        int[] d = new int[numStr.length()];
        d[0]=1;
        if(numStr.length()>=2&&numStr.charAt(0)=='0'||numStr.length()>=2&&Integer.parseInt(numStr.substring(0,2))>25){
            d[1]=1;
        }else{
            d[1]=2;
        }
        for(int i=2;i<numStr.length();i++){
            if(numStr.charAt(i-1)=='0'||Integer.parseInt(numStr.substring(i-1,i+1))>25){
                d[i]=d[i-1];
            }else{
                d[i]=d[i-1]+d[i-2];
            }
        }
        return d[numStr.length()-1];
    }

    /**
     * 递归解法 每次往后跳，可以跳一步，也可以跳两步
     * 要注意碰到0只能跳一步，例如506只能表示为fag 而不能把0省略成表示为fg，跳两步就会表示为fg
     * @param num
     * @return
     */
    public int translateNum(int num) {
        return translate(String.valueOf(num),0);
    }

    public int translate(String num,int l){

        if(l==num.length()){
            return 1;
        }

        if(l+1==num.length()||Integer.parseInt(num.substring(l,l+1))==0||l+2<=num.length()&&Integer.parseInt(num.substring(l,l+2))>25 ){
            return translate(num,l+1);
        }else{
            return translate(num,l+1)+ translate(num,l+2);
        }
    }
}
