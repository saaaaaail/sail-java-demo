package com.sail.leetcode.interview2020.数组;

/**
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 *
 * 示例 1:
 *
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 * 示例 2:
 *
 * 输入: [1, 2, 1, 2]
 * 输出: False
 * 注意:
 *
 * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
 * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/24-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO674_24点游戏 {
    public static void main(String[] args) {
        NO674_24点游戏 T = new NO674_24点游戏();
        T.judgePoint24(new int[]{7,7,8,9});
    }

    /**
     * 思路就是列举了四个数字的全部运算式，枚举法
     * @param nums
     * @return
     */
    public boolean judgePoint24(int[] nums) {
        double a = nums[0];
        double b = nums[1];
        double c = nums[2];
        double d = nums[3];
        return judge(a,b,c,d);
    }

    public boolean judge(double a,double b, double c,double d){
        return
                //ab
                judge(a+b,c,d)||
                        judge(a*b,c,d)||
                        judge(a-b,c,d)||
                        judge(b-a,c,d)||
                        judge(a/b,c,d)||
                        judge(b/a,c,d)||
                        //ac
                        judge(a+c,b,d)||
                        judge(a*c,b,d)||
                        judge(a-c,b,d)||
                        judge(c-a,b,d)||
                        judge(a/c,b,d)||
                        judge(c/a,b,d)||
                        //ad
                        judge(a+d,b,c)||
                        judge(a*d,b,c)||
                        judge(a-d,b,c)||
                        judge(d-a,b,c)||
                        judge(a/d,b,c)||
                        judge(d/a,b,c)||
                        //bc
                        judge(b+c,a,d)||
                        judge(b*c,a,d)||
                        judge(b-c,a,d)||
                        judge(c-b,a,d)||
                        judge(b/c,a,d)||
                        judge(c/b,a,d)||
                        //bd
                        judge(b+d,a,c)||
                        judge(b*d,a,c)||
                        judge(b-d,a,c)||
                        judge(d-b,a,c)||
                        judge(b/d,a,c)||
                        judge(d/b,a,c)||
                        //cd
                        judge(c+d,a,b)||
                        judge(c*d,a,b)||
                        judge(c-d,a,b)||
                        judge(d-c,a,b)||
                        judge(c/d,a,b)||
                        judge(d/c,a,b);
    }

    public boolean judge(double a,double b, double c){
        return
                //ab
                judge(a+b,c)||
                        judge(a*b,c)||
                        judge(a-b,c)||
                        judge(b-a,c)||
                        judge(a/b,c)||
                        judge(b/a,c)||
                        //ac
                        judge(a+c,b)||
                        judge(a*c,b)||
                        judge(a-c,b)||
                        judge(c-a,b)||
                        judge(a/c,b)||
                        judge(c/a,b)||
                        //bc
                        judge(b+c,a)||
                        judge(b*c,a)||
                        judge(b-c,a)||
                        judge(c-b,a)||
                        judge(b/c,a)||
                        judge(c/b,a);
    }

    public boolean judge(double a,double b){
        return
                Math.abs(a + b - 24) < 0.001 ||
                        Math.abs(a - b - 24) < 0.001 ||
                        Math.abs(b - a - 24) < 0.001 ||
                        Math.abs(a * b - 24) < 0.001 ||
                        Math.abs(a / b - 24) < 0.001 ||
                        Math.abs(b / a - 24) < 0.001;
    }
}
