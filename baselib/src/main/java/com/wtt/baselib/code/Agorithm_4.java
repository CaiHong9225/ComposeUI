package com.wtt.baselib.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wangzhan on 2022/8/8
 *
 * @descr 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。请你找出这两个有序
 * 数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。你可以假
 * 设 nums1 和 nums2 不会同时为空。
 * 测试用例：
 * nums1 = [1, 3]
 * nums2 = [2]
 * 测试结果：则中位数是 2.0
 */
class Agorithm_4 {


    /**
     * 暴力求解发 0(M+N) 先归并 在整理 求解
     *
     * @param num1
     * @param num2
     * @return
     */
    public static double Test_01(int[] num1, int[] num2) {
        List<Integer> array = new ArrayList<>();

        int size1 = num1.length;
        int size2 = num2.length;

        int index1 = 0;
        int index2 = 0;

        while (index1 < size1 && index2 < size2) {
            System.out.println("index1:" + index1 + ";index2:" + index2);
            array.add(num1[index1] < num2[index2] ? num1[index1] : num2[index2]);
            if (num1[index1] < num2[index2]) {
                if (index1 < size1) {
                    index1++;
                }
            } else {
                if (index2 < size2) {
                    index2++;
                }
            }
        }
        if (index1 == size1) {
            for (int i = index2; i < size2; i++) {
                array.add(num2[i]);
            }
        }
        if (index2 == size2) {
            for (int i = index1; i < size1; i++) {
                array.add(num1[i]);
            }
        }
        System.out.println("array:" + array);

        //
        int sizeArray = array.size() / 2;
        if (array.size() % 2 == 0) {
            return ((array.get(sizeArray - 1) + array.get(sizeArray))) / 2;
        } else {
            return (array.get(sizeArray));
        }
    }

    /**
     * 第K小数的思想，使用递归二分法进行解决。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double Test_02(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        return (kMinNum(0, n - 1, nums1, 0, m - 1, nums2, left) + kMinNum(0, n - 1, nums1, 0, m - 1, nums2, right)) * 0.5;
    }

    private static int kMinNum(int start1, int end1, int[] nums1, int start2, int end2, int[] nums2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //保证length小的数组在前面
        if (len1 > len2) return kMinNum(start2, end2, nums2, start1, end1, nums1, k);
        if (len1 == 0) return nums2[start2 + k - 1];
        //K==1返回
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            // k = k-(j-start2+1)
            return kMinNum(start1, end1, nums1, j + 1, end2, nums2, k - (j - start2 + 1));
        } else {
            // k = k-(i -start1+1) //抛去小的那部分
            return kMinNum(i + 1, end1, nums1, start2, end2, nums2, k - (i - start1 + 1));
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 4, 6, 8};
        int[] arr2 = new int[]{1, 5, 8, 10, 11};
        double v1 = Test_01(arr1, arr2);
        double v2 = Test_02(arr1, arr2);
        System.out.println("main:" + " v1:" + v1 + ";v2:" + v2);
    }
}
