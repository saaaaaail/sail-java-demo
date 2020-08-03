package com.sail.leetcode.sql;

/**
 * Employee 表包含所有员工信息，每个员工有其对应的工号 Id，姓名 Name，工资 Salary 和部门编号 DepartmentId 。
 *
 * +----+-------+--------+--------------+
 * | Id | Name  | Salary | DepartmentId |
 * +----+-------+--------+--------------+
 * | 1  | Joe   | 85000  | 1            |
 * | 2  | Henry | 80000  | 2            |
 * | 3  | Sam   | 60000  | 2            |
 * | 4  | Max   | 90000  | 1            |
 * | 5  | Janet | 69000  | 1            |
 * | 6  | Randy | 85000  | 1            |
 * | 7  | Will  | 70000  | 1            |
 * +----+-------+--------+--------------+
 * Department 表包含公司所有部门的信息。
 *
 * +----+----------+
 * | Id | Name     |
 * +----+----------+
 * | 1  | IT       |
 * | 2  | Sales    |
 * +----+----------+
 * 编写一个 SQL 查询，找出每个部门获得前三高工资的所有员工。例如，根据上述给定的表，查询结果应返回：
 *
 * +------------+----------+--------+
 * | Department | Employee | Salary |
 * +------------+----------+--------+
 * | IT         | Max      | 90000  |
 * | IT         | Randy    | 85000  |
 * | IT         | Joe      | 85000  |
 * | IT         | Will     | 70000  |
 * | Sales      | Henry    | 80000  |
 * | Sales      | Sam      | 60000  |
 * +------------+----------+--------+
 * 解释：
 *
 * IT 部门中，Max 获得了最高的工资，Randy 和 Joe 都拿到了第二高的工资，Will 的工资排第三。销售部门（Sales）只有两名员工，Henry 的工资最高，Sam 的工资排第二。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/department-top-three-salaries
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO185_部门工资前三高的所有员工 {

    /**
     * 子查询职员id，要满足什么要求呢？
     * 要查找同部门比当前员工工资高的员工的数量小于等于2的，这些员工就是目标
     * 同部门分组怎么弄，将两个Employee表e1和e2 的 DepartmentId相连，这样每个角色都是跟同部门的其他角色相连的
     * 以e1为查询基准，找e2中工资比e1员工高的员工并且数量（计数用工资去重计算，因为有相同工资）小于等于2的e1有哪些，因为这样的e1一定工资是前三的员工
     */
//    # Write your MySQL query statement below
//
//    SELECT d.Name as Department,e.Name as Employee,e.Salary as Salary FROM Employee e left join Department d on e.DepartmentId = d.Id
//    WHERE e.Id in (
//            SELECT e1.Id From Employee e1 left join Employee e2 on e1.DepartmentId = e2.DepartmentId and e1.Salary<e2.Salary
//            group by e1.Id
//            having count(distinct e2.Salary) <=2
//            )
//    and e.DepartmentId in (select id from Department)
//    ORDER BY d.Id ASC,e.Salary DESC
}
