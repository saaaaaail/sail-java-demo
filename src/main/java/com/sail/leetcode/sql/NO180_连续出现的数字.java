package com.sail.leetcode.sql;

/**
 * 编写一个 SQL 查询，查找所有至少连续出现三次的数字。
 *
 * +----+-----+
 * | Id | Num |
 * +----+-----+
 * | 1  |  1  |
 * | 2  |  1  |
 * | 3  |  1  |
 * | 4  |  2  |
 * | 5  |  1  |
 * | 6  |  2  |
 * | 7  |  2  |
 * +----+-----+
 * 例如，给定上面的 Logs 表， 1 是唯一连续出现至少三次的数字。
 *
 * +-----------------+
 * | ConsecutiveNums |
 * +-----------------+
 * | 1               |
 * +-----------------+
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/consecutive-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO180_连续出现的数字 {

    /**
     * 354ms 72.08%
     * 思路就是Logs 自己三联表，内联，id依次加一相连  查询条件是三联表的Num都相等
     */
//    select distinct l1.Num as ConsecutiveNums from Logs l1 inner join Logs l2 on l1.Id = l2.Id+1 inner join Logs l3 on l2.Id=l3.Id+1
//    where l1.Num=l2.Num and l2.Num = l3.Num;
}
