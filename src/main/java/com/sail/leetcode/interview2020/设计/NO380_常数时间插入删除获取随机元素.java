package com.sail.leetcode.interview2020.设计;

import java.util.*;


/**
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 *
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 * 示例 :
 *
 * // 初始化一个空的集合。
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomSet.insert(1);
 *
 * // 返回 false ，表示集合中不存在 2 。
 * randomSet.remove(2);
 *
 * // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomSet.insert(2);
 *
 * // getRandom 应随机返回 1 或 2 。
 * randomSet.getRandom();
 *
 * // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomSet.remove(1);
 *
 * // 2 已在集合中，所以返回 false 。
 * randomSet.insert(2);
 *
 * // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 * randomSet.getRandom();
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO380_常数时间插入删除获取随机元素 {


    class RandomizedSet {

        /** Initialize your data structure here. */
        /**
         * map的key保存元素值，value保存在list里的索引位置
         */
        Map<Integer,Integer> map;
        List<Integer> list;
        Random random;
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            random = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(!map.containsKey(val)){
                list.add(val);
                map.put(val,list.size()-1);
                return true;
            }
            return false;
        }

        /**
         * 这里为了保证ArrayList的删除是O(1)，做了一些处理
         * @param val
         * @return
         */
        public boolean remove(int val) {
            if(map.containsKey(val)){
                int index = map.get(val);
                int last = list.get(list.size()-1);
                /**
                 * 首先拿最后一个元素覆盖了要删除的目标元素，然后删除最后一个元素
                 */
                list.set(index,last);
                list.remove(list.size()-1);
                /**
                 * 处理完list以后，要更新最后一个元素在map里的索引位置
                 * 并删除目标元素
                 */
                map.put(last,index);
                map.remove(val);
                return true;
            }
            return false;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            /**
             * 求list size以内的一个随机数
             */
            int index = random.nextInt(list.size());
            return list.get(index);
        }
    }
}
