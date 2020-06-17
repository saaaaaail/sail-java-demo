package com.sail.foroffer;

import java.util.Arrays;

/**
 * top-k问题
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO40_最小的k个数 {


    public int[] getLeastNumbers(int[] arr, int k) {
        if(arr.length ==0 ||k==0){return new int[0];}
//        ##快排
        //找的是数组的第k-1个数，一共k个数
        return partitionSearch(arr,0,arr.length-1,k-1);

    }



    /**
     * 快排 划分区域
     * @param arr
     * @param left
     * @param right
     * @param k
     * @return
     */
    public int[] partitionSearch(int[] arr,int left,int right,int k){

        int part = partition(arr,left,right);
        if(part==k){
            return Arrays.copyOf(arr,part+1);
        }

        return part>k?partitionSearch(arr,left,part-1,k):partitionSearch(arr,part+1,right,k);

    }

    public int partition(int[] arr,int left,int right){
        int part = arr[left];
        int l = left;
        int r = right;
        while(true){
            while(l<r&&arr[r]>=part){
                r--;
            }
            while(l<r&&arr[l]<=part){
                l++;
            }
            if(l>=r){
                break;
            }
            int tmp = arr[r];
            arr[r]=arr[l];
            arr[l] = tmp;
        }
        arr[left] = arr[r];
        arr[r]= part;

        return r;
    }
}
