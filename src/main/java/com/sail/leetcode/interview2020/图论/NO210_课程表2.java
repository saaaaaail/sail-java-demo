package com.sail.leetcode.interview2020.图论;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 *
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 *
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 说明:
 *
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 *
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO210_课程表2 {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        try {
            Date parse = sdf.parse("2020-07-14");
            System.out.println(sdf.format(parse));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
    /**
     * 通过BFS来实现拓扑排序  16ms 39.3%
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> pre = new HashMap<>();
        Map<Integer,Set<Integer>> post = new HashMap<>();
        for(int i=0;i<prerequisites.length;i++){
            Set<Integer> preSet = pre.getOrDefault(prerequisites[i][0],new HashSet<>());
            preSet.add(prerequisites[i][1]);
            pre.put(prerequisites[i][0],preSet);

            Set<Integer> postSet = post.getOrDefault(prerequisites[i][1],new HashSet<>());
            postSet.add(prerequisites[i][0]);
            post.put(prerequisites[i][1],postSet);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int[] result = new int[numCourses];
        int psi = 0;
        for(int i=0;i<numCourses;i++){
            if(pre.getOrDefault(i,new HashSet<>()).size()==0){
                queue.offer(i);
            }
        }
        if(queue.isEmpty()){
            return new int[0];
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                int no = queue.poll();
                result[psi++] = no;
                Set<Integer> postSet = post.getOrDefault(no,new HashSet<>());
                for(Integer i:postSet){
                    Set<Integer> preSet = pre.get(i);
                    if(preSet!=null){
                        preSet.remove(no);
                        if(preSet.isEmpty()){
                            queue.offer(i);
                        }
                    }
                }
                size--;
            }
        }
        if(psi==numCourses){
            return result;
        }
        return new int[0];
    }
}
