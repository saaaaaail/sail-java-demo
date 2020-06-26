package com.sail.leetcode.interview2020.经典习题;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO42_接雨水 {

    public static void main(String[] args) {
        int[] nums = new int[]{4,2,3};
        System.out.println(trap(nums));
    }

    /**
     * 1ms 99.98%
     * 思路就是 找到最高点+滑动窗口
     * 滑动窗口从左向中间最高点滑动，如果i比左边界l要小就加上height[l]-height[i]的面积，如果i大于等于左边界了就把左边界更新到i继续找面积
     * 滑动窗口从右向中间最高点滑动，如果i比右边界r要小就加上height[r]-height[i]的面积，如果i大于等于有边界r了就把有边界更新到i的位置
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int l=0;
        int r=height.length-1;
        int h=0;
        int maxh = Integer.MAX_VALUE;
        //寻找最高点
        for(int i=0;i<height.length;i++){
            if(height[i]>maxh){
                maxh = height[i];
                h=i;
            }
        }
        int result = 0;
        //从左开始遍历
        for(int i=0;i<=h;i++){
            if(height[l]>height[i]){
                int sub = height[l]-height[i];
                result += sub;
            }else if(i!=l){
                l=i;
            }
        }
        //从右开始遍历
        for(int i=height.length-1;i>=h;i--){
            if(height[r]>height[i]){
                int sub = height[r]-height[i];
                result += sub;
            }else if(r!=i){
                r=i;
            }
        }
        return result;


    }
}
