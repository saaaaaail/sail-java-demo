package com.sail.leetcode.interview2020.数组;

/**
 *给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 *
 * 假设你总是可以到达数组的最后一个位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO45_跳跃游戏2 {


    public static void main(String[] args) {
        NO45_跳跃游戏2 t = new NO45_跳跃游戏2();
        int jump = t.jump(new int[]{2, 1});
        System.out.println(jump);
    }

    /**
     * 2ms 94.87%
     * 另一种思路，每一轮更新下一轮起跳的范围
     * 以 2 3 1 1 4为例
     * 第一轮，起跳索引范围就是0~0，0处得值是2，那么得到下一轮起跳得范围是1~2 更新下一轮遍历范围 start=1，end=3
     * 第二轮，起跳索引范围是1~2，求下一轮最远范围是1+3=4，那么从3~4都可以到达，下了一轮起跳范围是3~4，更新下轮起跳范围start=3，end=5
     * 每轮结束得 时候，跳跃次数+1，然后如果下一轮end范围如果大于数组最后一个值了，说明下一轮绝对可以跳到了。就跳出循环结束
     */
    public int jump2(int[] nums) {
        int start = 0;
        int end = 1;
        int res = 0;
        while(end<nums.length){
            int maxRange=0;
            for(int i=start;i<end;i++){
                maxRange = Math.max(maxRange,nums[i]+i);

            }
            start = end;
            end = maxRange+1;
            res++;
        }
        return res;

    }

    /**
     * dfs优化到头了，剪枝，缓存数组，最后一个25000用例还是会超时，不要用dfs
     */
    private int[] d ;
    public int jump(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        d = new int[nums.length];
        return doJump(nums,0);
    }

    public int doJump(int[] nums,int index){
        if(d[index]!=0){
            return d[index];
        }
        if(index==nums.length-1){
            return 0;
        }
        int jump = nums[index];
        if(index + jump >= nums.length-1){
            d[index]=1;
            return 1;
        }
        int res = Integer.MAX_VALUE;
        for(int i=jump;i>=1;i--){
            if(index+i<nums.length){
                res = Math.min(res,doJump(nums,index+i));
            }
        }
        if(res==Integer.MAX_VALUE){
            d[index]=Integer.MAX_VALUE;
            return Integer.MAX_VALUE;
        }
        d[index]=res+1;
        return res+1;
    }
}
