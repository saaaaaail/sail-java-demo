package com.sail.leetcode.interview2020.图论;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一组 N 人（编号为 1, 2, ..., N）， 我们想把每个人分进任意大小的两组。
 *
 * 每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 *
 * 形式上，如果 dislikes[i] = [a, b]，表示不允许将编号为 a 和 b 的人归入同一组。
 *
 * 当可以用这种方法将每个人分进两组时，返回 true；否则返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 * 示例 2：
 *
 * 输入：N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 * 示例 3：
 *
 * 输入：N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * 对于dislikes[i] == dislikes[j] 不存在 i != j
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/possible-bipartition
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO886_可能的二分法_sail {

    public static void main(String[] args) {
        NO886_可能的二分法_sail t = new NO886_可能的二分法_sail();
        t.possibleBipartition(4,new int[][]{{1,2},{1,3},{2,4}});
    }
    /**
     * 第二种思路 遍历N
     * 染色 + dfs
     * color[N] 表示一个点的染色情况
     * color[i]==0，表示没有初始化
     * color[i]==col，表示与当前点颜色相同，相同的认为可以在一个group里
     * color[i]==-col，表示与当前点颜色相反的节点，认为在对立的group里
     * 我们构建的图是dislikes的图
     * 所以在dfs的过程中，当前节点的下一个节点应该是跟我现在的节点 颜色相反的
     * 所以如果在碰到了之前访问过的节点的时候，这个节点是我dislike列表里的，而这个节点的颜色跟我当前节点颜色相同，就说明冲突了返回false；
     */
    Map<Integer,List<Integer>> graph = new HashMap<>();
    int[] color ;
    public boolean possibleBipartition(int N, int[][] dislikes) {
        color = new int[N+1];
        /**
         * 构建dislike的图
         */
        for(int i=0;i<dislikes.length;i++){
            List<Integer> list1 = graph.getOrDefault(dislikes[i][0],new ArrayList<>());
            list1.add(dislikes[i][1]);
            graph.put(dislikes[i][0],list1);
            List<Integer> list2 = graph.getOrDefault(dislikes[i][1],new ArrayList<>());
            list2.add(dislikes[i][0]);
            graph.put(dislikes[i][1],list2);
        }
        for(int i=1;i<=N;i++){
            /**
             * color为0表示节点没有访问过，去dfs
             */
            if(color[i]==0&&!doPossibleBipartition(i,1)){
                return false;
            }
        }
        return true;
    }

    public boolean doPossibleBipartition(int N,int col){
        /**
         * 当前的颜色为col
         */
        color[N] = col;
        List<Integer> dislikes = graph.get(N);
        if(dislikes!=null){
            for(Integer d:dislikes){
                /**
                 * 如果不喜欢列表里节点的颜色跟我当前节点颜色相同的话，说明这个节点之前访问过，并且冲突了
                 */
                if(color[d]==col){
                    return false;
                }
                /**
                 * 对没有访问的节点进行访问，并且访问的节点为不喜欢节点，颜色相反
                 */
                if(color[d]==0&&!doPossibleBipartition(d,-col)){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 首先第一种思路超时了，还是记录下，我居然会想着去遍历dislike数组，不是妥妥的超时嘛
     */
    Map<Integer,Integer> hate1 = new HashMap<Integer,Integer>();
    Map<Integer,Integer> hate2 = new HashMap<Integer,Integer>();
    public boolean possibleBipartition2(int N, int[][] dislikes) {
        return doPossibleBipartition(N,dislikes,0);
    }

    public boolean doPossibleBipartition(int N, int[][] dislikes,int index){
        if(index==dislikes.length){
            return true;
        }

        int num1 = dislikes[index][0];
        int num2 = dislikes[index][1];
        boolean result = false;
        if(hate1.getOrDefault(num1,0)==0&&hate2.getOrDefault(num2,0)==0){
            hate1.put(num2,hate1.getOrDefault(num2,0)+1);
            hate2.put(num1,hate2.getOrDefault(num1,0)+1);
            result = result || doPossibleBipartition(N,dislikes,index+1);
            hate1.put(num2,hate1.getOrDefault(num2,0)-1);
            hate2.put(num1,hate2.getOrDefault(num1,0)-1);
        }
        if(result){

            return result;
        }
        if(hate1.getOrDefault(num2,0)==0&&hate2.getOrDefault(num1,0)==0){
            hate1.put(num1,hate1.getOrDefault(num1,0)+1);
            hate2.put(num2,hate2.getOrDefault(num2,0)+1);
            result  = result || doPossibleBipartition(N,dislikes,index+1);
            hate1.put(num1,hate1.getOrDefault(num1,0)-1);
            hate2.put(num2,hate2.getOrDefault(num2,0)-1);
        }
        return result;
    }
}
