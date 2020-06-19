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


    public int[] getLeastNumbers1(int[] arr, int k) {
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


    /**
     * 堆排序的方法
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if(arr.length ==0 ||k==0){return new int[0];}
        int[] result = new int[k];
        int count=0;
        int[] copy = new int[arr.length+1];
        //堆排序必须从1位置开始
        System.arraycopy(arr,0,copy,1,arr.length);

        /**
         * 要寻找arr集合中最小的k个数，
         * 第一种使用堆的方法、构建arr集合的小跟堆，每次取出堆头，然后从排
         * 第二种使用堆的方法，构建仅包含k个数的大根堆，然后从arr-k的集合中取出元素与大根堆头比较
         * 如果新的数比大根堆头要大则直接跳过，如果比大根堆头要小则与堆头交换，重新调整堆
         * 这里是用的向下调整法
         */
        buildMinHeap(copy,k);

        for(int i=k+1;i<copy.length;i++){
            if(copy[i]<copy[1]){
                swap(copy,i,1);
                sink(copy,1,k);
            }
        }
        System.arraycopy(copy,1,result,0,result.length);
        return result;
    }

    public void buildMinHeap(int[] arr,int len){

        for(int i=len/2;i>=1;i--){
            sink(arr,i,len);
        }
    }

    public void sink(int[] arr,int k,int len){
        arr[0]=arr[k];
        for(int i=2*k;i<=len;i=i*2){
            if(i+1<=len&&arr[i]<arr[i+1]){
                i++;
            }
            if(arr[0]>=arr[i]){
                break;
            }
            arr[k]=arr[i];
            k=i;
        }
        arr[k]=arr[0];
    }

    public void swap(int[] arr,int l,int r){
        int tmp = arr[l];
        arr[l]=arr[r];
        arr[r]=tmp;
    }


    /**
     * 计数排序，如果已经知道了arr数组的数的范围，可以使用一个大数组计数
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if(arr.length ==0 ||k==0){return new int[0];}
        int[] count = new int[10001];
        int[] result = new int[k];
        int r = 0;
        for(int num:arr){
            count[num]++;
        }
        for(int i=0;i<count.length;i++){
            while(count[i]-->0&&r<k){
                result[r++]=i;
            }
            if(r>=k){
                break;
            }
        }
        return result;
    }

    /**
     * 二叉排序树
     */
}
