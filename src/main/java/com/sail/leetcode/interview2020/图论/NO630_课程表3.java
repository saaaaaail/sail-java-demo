package com.sail.leetcode.interview2020.图论;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *这里有 n 门不同的在线课程，他们按从 1 到 n 编号。每一门课程有一定的持续上课时间（课程时间）t 以及关闭时间第 d 天。一门课要持续学习 t 天直到第 d 天时要完成，你将会从第 1 天开始。
 *
 * 给出 n 个在线课程用 (t, d) 对表示。你的任务是找出最多可以修几门课。
 *
 *  
 *
 * 示例：
 *
 * 输入: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 输出: 3
 * 解释:
 * 这里一共有 4 门课程, 但是你最多可以修 3 门:
 * 首先, 修第一门课时, 它要耗费 100 天，你会在第 100 天完成, 在第 101 天准备下门课。
 * 第二, 修第三门课时, 它会耗费 1000 天，所以你将在第 1100 天的时候完成它, 以及在第 1101 天开始准备下门课程。
 * 第三, 修第二门课时, 它会耗时 200 天，所以你将会在第 1300 天时完成它。
 * 第四门课现在不能修，因为你将会在第 3300 天完成它，这已经超出了关闭日期。
 *  
 *
 * 提示:
 *
 * 整数 1 <= d, t, n <= 10,000 。
 * 你不能同时修两门课程。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO630_课程表3 {

    public static void main(String[] args) {
        NO630_课程表3 t = new NO630_课程表3();
        int i = t.scheduleCourse(new int[][]{{7, 17}, {3, 12}, {10, 20}, {9, 10},{5,20},{10,19},{4,18}});
        System.out.println(i);
    }
    /**
     * 采用大根堆存持续时间每次时间来不及了，就选择最大持续时间的课程进行替换，对课程按照deadline次序从小到大依次入队
     * 有几个注意的点
     * 如果当前时间+课程持续时间<课程截止时间，则入队，认为可以选课
     * 否则就是当前课程会超时，所以拿着当前课程与已经选的课里面持续时间最大的课进行替换（首先这么换，已选课程截止时间肯定都是满足的），
     * 就是要判断前面删了一段时间，当前课程加到末尾以后能否满足自己的截止时间
     * 如果当前课程的时间已经大于等于堆里面的最大持续时间了，替换了也没有意义，肯定不会满足当前课程的截止时间（需要加上这个判断）
     * @param courses
     * @return
     */
    public int scheduleCourse(int[][] courses) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->{
            return o2[0]-o1[0];
        });
        Arrays.sort(courses,(o1,o2)->{
            return o1[1]-o2[1];
        });
        int currentTime = 0;
        for(int i=0;i<courses.length;i++){
            //当前时间轴+当前课程的持续时间<当前课程的截止时间
            if(currentTime+courses[i][0]<=courses[i][1]){
                queue.offer(courses[i]);
                currentTime = currentTime + courses[i][0];
            }else{
                //上面的不满足，想想替代方案，如果队列是空的，说明课程本身就持续时间就大于截止时间，直接跳过
                if(!queue.isEmpty()){
                    //如果队列不为空，选择堆顶持续时间最长的课，判断一下当前课程与堆顶课程能否替换来压缩一下前面的时间
                    int[] peek = queue.peek();
                    //如果当前课程持续时间比堆顶课程持续时间还要大（下面if第一个条件），就直接丢弃这门课，
                    //因为就算替换了满足了当前课程（下面if第二个条件），替换课程，数量是没变的，但是课程总的时间轴被扩大了，留给后面的课程的时间反而变少了
                    //所以能不能替换掉课程，要满足下面if的两个条件
                    if(peek[0]>courses[i][0]&&currentTime-peek[0]+courses[i][0]<=courses[i][1]){
                        queue.poll();
                        queue.offer(courses[i]);
                        currentTime = currentTime-peek[0]+courses[i][0];
                    }
                }
            }
        }
        return queue.size();

    }
}
