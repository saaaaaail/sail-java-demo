package com.sail.leetcode.interview2020.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class NO40_组合总和2_数组中任意几个元素的和 {

    /**
     * 这一题咋一看 还以为是两数之和、三数之和，，结果是任意长度个数的和
     * <p>
     * 所以还是用dfs
     * 首先排序 从0开始dfs，记录剩余的sum，记录路径
     * dfs结束的标志是sum==0，说明找到了一条路径的和等于target
     *
     */
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        doCombinationSum2(candidates, 0, target, new ArrayList<>());
        return result;
    }

    public void doCombinationSum2(int[] candidates, int index, int sum, List<Integer> path) {
        if (sum == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (index == candidates.length) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int num = candidates[i];
            /**
             * 剪个枝，当前及以后的数num都大于sum了，直接结束这一dfs分支
             */
            if (sum < num) {
                break;
            }
            path.add(num);
            doCombinationSum2(candidates, i + 1, sum - num, path);
            path.remove(path.size() - 1);
            /**
             * 这里是将num后面与num相同的数去重掉
             */
            while (i + 1 < candidates.length && candidates[i + 1] == num) {
                i++;
            }
        }

    }
}