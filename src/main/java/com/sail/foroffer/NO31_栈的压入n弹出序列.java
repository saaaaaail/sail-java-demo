package com.sail.foroffer;

import java.util.LinkedList;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *  
 *
 * 提示：
 *
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO31_栈的压入n弹出序列 {

    /**
     * 思路采用一个栈来解决
     * 遍历弹出序列，在压入序列或者栈顶寻找当前弹出序列位置的字符，
     * 如果压入序列找完了，栈顶元素也不是，说明得不到这个弹出序列返回false
     * 使用popl表示弹出序列的索引位置
     * 首先什么情况下会对压入序列入栈：
     *      栈为空。即遍历到弹出序列的第一个元素的时候，只能到压入序列里面去找，对压入序列入栈找到弹出序列的第一个元素，popl后移，pushl也后移
     *      栈不为空，但是栈顶元素不等于弹出序列的当前元素，就只能去压入序列里面找，进入入栈操作，如果找不到，pushl走完了，返回false
     * 当然如果栈不为空，但是栈顶元素就是弹出序列的当前元素，那么出栈即可，popl也后移一位
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        LinkedList<Integer> stack = new LinkedList();
        int popl=0;
        int pushl=0;
        while(popl<len){
            int poppedNum = popped[popl];
            int peek;

            if(stack.isEmpty()||((peek=stack.peek())!=poppedNum)){
                if(pushl<len){
                    while(pushed[pushl]!=poppedNum){
                        stack.push(pushed[pushl++]);
                        if(pushl>=len){return false;}
                    }
                    popl++;
                    pushl++;
                }else{
                    return false;
                }
            }else{

                stack.pop();
                popl++;

            }
        }
        return true;
    }
}
