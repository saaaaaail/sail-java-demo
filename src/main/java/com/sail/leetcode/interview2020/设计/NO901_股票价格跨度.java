package com.sail.leetcode.interview2020.设计;

import java.util.ArrayList;
import java.util.List;

/**
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 *
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 *
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 *
 *  
 *
 * 示例：
 *
 * 输入：["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * 输出：[null,1,1,1,2,1,4,6]
 * 解释：
 * 首先，初始化 S = StockSpanner()，然后：
 * S.next(100) 被调用并返回 1，
 * S.next(80) 被调用并返回 1，
 * S.next(60) 被调用并返回 1，
 * S.next(70) 被调用并返回 2，
 * S.next(60) 被调用并返回 1，
 * S.next(75) 被调用并返回 4，
 * S.next(85) 被调用并返回 6。
 *
 * 注意 (例如) S.next(75) 返回 4，因为截至今天的最后 4 个价格
 * (包括今天的价格 75) 小于或等于今天的价格。
 *  
 *
 * 提示：
 *
 * 调用 StockSpanner.next(int price) 时，将有 1 <= price <= 10^5。
 * 每个测试用例最多可以调用  10000 次 StockSpanner.next。
 * 在所有测试用例中，最多调用 150000 次 StockSpanner.next。
 * 此问题的总时间限制减少了 50%。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/online-stock-span
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO901_股票价格跨度 {


    /**
     * 33ms 94.65%
     * 这里使用了跳表的思想，跳表里面存的是比当前元素要大且最近的那一个元素的索引位置，为的是查询的时候能一次比较就跨过很多位。
     * 首先这个序列是无序的，也不能人为调整
     * 因此对于每一个添加的元素，往前寻找最近的比他大的那个元素，然后索引相减就是这个元素最大连续日数
     * 暴力法的话就是从列表尾开始往前找
     * 优化的话就是使用一个数组将每个元素离他最近的比他大的索引存起来，
     * 这样比较如果插入的元素比最后元素要小或者为空，则直接返回1
     * 如果插入的元素n比最后元素l要大，那么就查询最后一个元素l的跳表直接找到比这个元素l大的元素x，
     * 如果插入元素n 比 x 还要大就继续查询这个x在跳表里的索引，直接找到比x要大的元素
     * 依次类推，最后要么表找完了，要么找到一个数 a 比 n 要大，从n的索引减去a的索引即可
     *
     * 可以看出相比于暴力比较，一次跳过一个数，这种方式一次跳过了多个数，减少查询次数
     */
    class StockSpanner {
        private List<Integer> jump;
        private  List<Integer> data;
        public StockSpanner() {
            jump = new ArrayList<>();
            data = new ArrayList<>();
        }

        public int next(int price) {
            int size = data.size();
            if(size==0){
                jump.add(-1);
                data.add(price);
                return 1;
            }
            if(price<data.get(size-1)){
                jump.add(size-1);
                data.add(price);
                return 1;
            }
            int index = size-1;
            while(index!=-1&&price>=data.get(index)){
                index = jump.get(index);
            }
            jump.add(index);
            data.add(price);
            return data.size()-1-index;
        }
    }
}
