package com.sail.leetcode.interview2020.字符串;

/**
 * 将非负整数转换为其对应的英文表示。可以保证给定输入小于 231 - 1 。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: "One Hundred Twenty Three"
 * 示例 2:
 *
 * 输入: 12345
 * 输出: "Twelve Thousand Three Hundred Forty Five"
 * 示例 3:
 *
 * 输入: 1234567
 * 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4:
 *
 * 输入: 1234567891
 * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-english-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO273_整数转换为英文表示_hard {
    public static void main(String[] args) {
        String[][] nums=  new String[][]{{"sdfsf","sfsdf"},{"sdfsdf","sdfsdf"}};
        System.out.println(Integer.MAX_VALUE);
    }


    /**
     * 思路就是要知道Billion是10的9次方，Million是10的6次方,Thousand是10的3次方,
     * 相隔的三次方中间呢就是 百位的命名方法 例  One Hundred Twenty Three
     * 对于整型最大值就只有十亿数量级，所以不用考虑Billion上面的量级，
     * 当然即便有，就循环命名吧，Billion还大的按照整个方法重新递归一下
     */
    String[] units = new String[]{"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
    String[] tensLow = new String[]{"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    String[] tensHigh = new String[]{"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    public String numberToWords(int num) {
        if(num==0){
            return "Zero";
        }
        StringBuilder ans = new StringBuilder();
        int part1 = num%1000;
        num = num/1000;
        int part2 = num%1000;
        num = num/1000;
        int part3 = num%1000;
        num = num/1000;
        int part4 = num;
        if(part4!=0){
            ans.append(" ").append(numberToWords(part4)).append(" Billion");
        }
        if(part3!=0){
            ans.append(" ").append(buildStr(part3," Million"));
        }
        if(part2!=0){
            ans.append(" ").append(buildStr(part2," Thousand"));
        }
        if(part1!=0){
            ans.append(" ").append(buildStr(part1,""));
        }
        return ans.toString().trim();

    }

    public String buildStr(int part,String link){
        StringBuilder sb = new StringBuilder();
        int part1 = part%100;
        part = part/100;
        if(part!=0){
            sb.append(units[part]).append(" Hundred");
        }
        if(part1!=0){
            sb.append(" ");
            if(part1<10){
                sb.append(units[part1]);
            }else if(part1<20){
                sb.append(tensLow[part1-10]);
            }else{
                int part2 = part1%10;;
                part1 = part1/10;
                sb.append(tensHigh[part1]);
                if(part2!=0){
                    sb.append(" ").append(units[part2]);
                }
            }
        }
        sb.append(link);
        return sb.toString().trim();
    }
}
