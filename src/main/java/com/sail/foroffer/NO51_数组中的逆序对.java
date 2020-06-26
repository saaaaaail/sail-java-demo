package com.sail.foroffer;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO51_数组中的逆序对 {

    public static void main(String[] args) {
        int[] nums = new int[]{7,5,6,4};
        System.out.println(reversePairs(nums));
    }

    /**
     * 归并方法计算逆序对 34ms 93.36%
     * 然后拷贝数组建议一开始就初始化好，递归当中直接使用，因为在递归中new一个数组仍然会耗费大量的时间
     * 为什么可以计算呢，当把一个数组拆分成左右两个有序对的时候，左右比较选小的出来嘛
     * 当右边j比左边i小的情况下，左边从i到mid的数都是比j要大的，因此count+=mid-i+1，就通过这种情况来计算逆序对
     */
    public static int reversePairs(int[] nums) {
        if(nums==null|| nums.length==0){
            return 0;
        }
        int[] tmp = new int[nums.length];
        return mergeSort(nums,0,nums.length-1,tmp);
    }

    public static int mergeSort(int[] nums,int l,int r,int[] tmp){
        if(l>=r){
            return 0;
        }
        int mid = (l+r)/2;
        int left = mergeSort(nums,l,mid,tmp);
        int right = mergeSort(nums,mid+1,r,tmp);
        if (nums[mid]<=nums[mid+1]){
            return left+right;
        }
        int cross = merge(nums,l,mid,r,tmp);
        return left+right+cross;
    }

    public static int merge(int[] nums,int l,int mid,int r,int[] tmp){
        for(int i=0;i<nums.length;i++){
            tmp[i]=nums[i];
        }
        int i=l;
        int j=mid+1;
        int k=l;
        int count=0;
        while (i<=mid&&j<=r){
            if(tmp[i]<=tmp[j]){
                nums[k++]=tmp[i++];
            }else{
                //所以啊，这里必须是nums[i]>nums[j]才行，因为逆序对的计数，不能包括相等的情况
                nums[k++]=tmp[j++];
                count += mid-i+1;
            }
        }
        while (i<=mid){
            nums[k++]=tmp[i++];
        }
        while (j<=r){
            nums[k++]=tmp[j++];
        }
        return count;
    }
}
