package com.sail.foroffer;

import java.util.LinkedList;

/**
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 *
 * 输入: n = 10, m = 17
 * 输出: 2
 *  
 *
 * 限制：
 *
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO62_圆圈中最后剩下的数字 {
    public static void main(String[] args) {
        System.out.println(lastRemaining1(5,3));
    }

    /**
     * 7ms 99.83%
     * 约瑟夫环问题的数学解法
     * 是通过倒推来解决的
     * 首先摆一下，0 1 2 3 4 5 每次删除第3位的正常的删除顺序（后续多的代表循环）
     * 0 1 |2| 3 4 0 1 2 3 4 先删除第三位2，然后把后一位3移到列表首位
     * 3 4 |0| 1 3 4 0 1     然后删除这个列表的第三位0，把后一位1移动到首位
     * 1 3 |4| 1 3 4         然后删除这个列表的第三位4，把后一位1 移动到首位
     * 1 3 |1| 3             然后删除这个列表的第三位1，3移动到首位
     * 3
     * 可以看的每次删除了第 m 位，就把 m + 1 位移动到首位，最后剩下了3 因此把3这个数字称为安全数字
     * 那么倒推试试看
     * 在最后列表只剩下一个数的时候， 3 的索引位置为 0
     * 在列表剩下两个数的时候， 3 的索引位置为 1， 怎么算的  将最后列表的3后移3位，万一超过了列表元素2个，就取一下模 得到 1
     * 在列表剩下三个数的时候，3 的安全索引位置为 把上一轮求到的 1 后移三位，万一超过了此时的列表元素3个，取一下模 得到 1
     * 在列表剩下四个数的时候，3 的安全索引位置为 把上一轮求得 1后移三位，万一超过了此时得列表元素4个，此时索列表0123引最大是 3，取一下模4等于 0
     * 在列表剩下五个数得时候，3 得安全索引位置为 把上一轮求得得 0后移三位，并取一下模5，得到 3，即一开始那个安全得数就是3
     *
     * 因此我们也得到了倒退的公式就是 此次循环的安全索引索引位置后移m位，取一下当前列表数量的模，就得到了上一轮循环的安全索引位置
     *  (idx+m)%n
     *  最后我们知道 0 肯定是最后的安全索引位置，反推即可
     */
    public int lastRemaining(int n, int m) {
        //最后结果的索引位置肯定是0，但是呢，现在只剩下一个数字了，要求刚开始还有n个数字的时候这个数的索引位置
        int ans = 0;
        for(int i=2;i<=n;i++){
            ans = (ans+m)%i;
        }
        return ans;

    }



    /**
     * 超时！！！
     * 构建链表一个一个减，使用LinkedList会超时，使用ArrayList勉强通过
     * @param n
     * @param m
     * @return
     */
    public static int lastRemaining1(int n, int m) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<n;i++){
            list.addLast(i);
        }
        int pick = 0;
        while(list.size()>1){
            pick = (pick+m-1)%n;
            list.remove(pick);
            n--;;
        }
        return list.get(0);

    }
}
