package com.sail.leetcode.interview2020.数组;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 *  
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO4_寻找两个正序数组的中位数 {


    public static void main(String[] args) {

//        System.out.println(findMedianSortedArrays(
//                new int[]{1,2},
//                new int[]{-1,3}
//        ));
    }

    /**
     * 思路就是对中位数的二分 3ms 60.99%
     * 详细解释一下 mid 表示中位数前面有多少个元素
     * 根据数组1和数组2求得其中位数mid在什么位置，具体描述mid的含义就是mid的后一位或者两位是中位数
     * 如果两个数组和为偶数，例10，中位数为5，6  那么mid为4，表示中位数前面有4个元素  这么算allSize/2-1
     * 如果两个数组和allSize为奇数，例9，中位数为5，那么mid为4，表示中位数前面有4个数，这么算allSize/2
     * 然后呢 把 中位数前面的元素全部找出来排除掉，剩在数组头部的就是中位数相关的一个数或者两个数了
     * 排除的方法采用二分法，
     * 分别在nums1和nums2里面找从起始位置l开始，l+mid/2位置的元素，num1和num2
     * 如果num1大于num2，说明num2从起始位置l2到l2+mid/2位置的元素，都不可能是中位数，直接排除一串数，如果num2大于num1同理
     * mid呢减去上面排除的数，后继续找新的索引位置 两个数组mid/2处的大小，再次排除一半
     * 直到mid==1 就不用再二分，直接两个数组头部挑一个小的排除，排除以后
     * 剩下的两个数组的数，如果一开始总和是偶数，就从剩下两个数组的头部挑选 最小的两个数出来，求平均数即为中位数
     * 如果一开始总和是奇数，就从剩下的两个数组头部选择一个小的数返回即为中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        int all = size2+size1;
        int mid = (size1+size2)/2+1;
        if(all%2==0){
            mid= all/2-1;
        }else{
            mid = all/2;
        }
        int l1 = 0;
        int l2 = 0;
        int pl1 = 0;
        int pl2 = 0;
        while(mid>0){

            if(l1==nums1.length||l2==nums2.length){
                break;
            }

            if(mid!=1&&pl1+mid/2-1<nums1.length){
                l1 = pl1 + mid/2-1;
            }else if(mid==1){
                l1 = pl1;
            }else{
                l1 = nums1.length-1;
            }
            if(mid!=1&&pl2+mid/2-1<nums2.length){
                l2 = pl2 + mid/2-1;
            }else if(mid==1){
                l2 = pl2;
            }else{
                l2 = nums2.length-1;
            }
            if(nums1[l1]>nums2[l2]){
                mid = mid-l2+pl2-1;
                l1=pl1;
                l2++;
                pl2 = l2;

            }else{
                mid = mid-l1+pl1-1;
                l1++;
                pl1 = l1;
                l2=pl2;
            }
        }

        if(all%2==0){
            if(l1==nums1.length){
                l2+=mid;
                return (double)(nums2[l2]+nums2[l2+1])/2;
            }
            if(l2==nums2.length){
                l1+=mid;
                return (double)(nums1[l1]+nums1[l1+1])/2;
            }
            int[] lnum =new int[2];
            int ln=0;
            while(ln<2&&l1<nums1.length&&l2<nums2.length){
                if(nums1[l1]>nums2[l2]){
                    lnum[ln++]=nums2[l2++];
                }else{
                    lnum[ln++]=nums1[l1++];
                }
            }
            if(ln<2){
                while(ln<2&&l1<nums1.length){
                    lnum[ln++]=nums1[l1++];
                }
                while(ln<2&&l2<nums2.length){
                    lnum[ln++]=nums2[l2++];
                }
            }
            return (double)(lnum[0]+lnum[1])/2;
        }else{
            if(l1==nums1.length){
                l2+=mid;
                return (double)nums2[l2];
            }
            if(l2==nums2.length){
                l1+=mid;
                return (double)nums1[l1];
            }
            return nums1[l1]<nums2[l2]?(double)nums1[l1]:(double)nums2[l2];
        }
    }


    /**
     * 两个有序的，自然而然归并排到中间元素的时候停止 时间复杂度O((m+n)/2) 差不多O(n)
     * 时间复杂度达不到题目要求的O(log(m+n))
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        int all = size1+size2;
        int mid = all/2;
        double[] nums = new double[mid+1];
        int l=0;
        int r=0;
        int k=0;
        while(l<size1&&r<size2){
            if(nums1[l]<nums2[r]){
                nums[k++]=nums1[l++];
            }else{
                nums[k++]=nums2[r++];
            }
            if(k>=nums.length){
                break;
            }
        }
        if(k<nums.length){
            while(l<size1){
                nums[k++]=nums1[l++];
                if(k>=nums.length){
                    break;
                }
            }
            while(r<size2){
                nums[k++]=nums2[r++];
                if(k>=nums.length){
                    break;
                }
            }
        }
        if(all%2==0){
            return (nums[nums.length-1]+nums[nums.length-2])/2;
        }else{
            return nums[nums.length-1];
        }
    }
}
