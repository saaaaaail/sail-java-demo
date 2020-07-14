package com.sail.leetcode.interview2020.图论;

import java.util.*;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *  
 *
 * 提示：
 *
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO207_课程表 {

    /**
     * 使用map来表示图的一个节点的前驱节点与后继节点 18ms 24.83%
     * 首先找到不需要前驱课程的点入队
     * 然后一边出队一边减少出度
     * 直到找不到入度为0的课程，但是课程没有全部学完，说明有课程成环了，返回false
     * 如果最后队列为空，且所有课程都学习过了，则返回true
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> pre = new HashMap<>();
        Map<Integer,Set<Integer>> post = new HashMap<>();
        for(int i=0;i<prerequisites.length;i++){
            Set<Integer> preSet = pre.getOrDefault(prerequisites[i][1],new HashSet<>());
            preSet.add(prerequisites[i][0]);
            pre.put(prerequisites[i][1],preSet);

            Set<Integer> postSet = post.getOrDefault(prerequisites[i][0],new HashSet<>());
            postSet.add(prerequisites[i][1]);
            post.put(prerequisites[i][0],postSet);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] learn = new boolean[numCourses];
        for(int i=0;i<numCourses;i++){
            if(pre.getOrDefault(i,new HashSet<>()).size()==0){
                queue.offer(i);
            }
        }
        if(queue.isEmpty()){
            return false;
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                int no = queue.poll();
                learn[no]  =true;
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
        for(int i=0;i<learn.length;i++){
            if(!learn[i]){
                return false;
            }
        }
        return true;
    }
}
