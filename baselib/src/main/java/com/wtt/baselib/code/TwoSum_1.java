package com.wtt.baselib.code;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wangzhan on 2022/6/7
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 输入：
 * <p>
 * nums = [2, 7, 11, 15], target = 9
 * <p>
 * 1
 * <p>
 * 输出：
 * <p>
 * [0, 1]
 * <p>
 * 1
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * ————————————————
 *
 * @descr
 */
class TwoSum_1 {

    // 方法一：暴力法 O(n^2)
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    //倒推法 o(n)
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);//把所有的数字和下标存储到map中
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];//用目标值减去数组里面遍历的值
            if (map.containsKey(complement) && map.get(complement) != i) {//如果map中有这个值，并且不是当前遍历的值
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[]{-1, -1};
    }

    //正推发 O(n)
    public int[] twoSun3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int needNum = target - nums[i];
            if (map.containsKey(needNum)) {
                return new int[]{map.get(needNum), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        TwoSum_1 twoSum_1 = new TwoSum_1();
        int[] nums = {2, 7, 11, 15};
        int target = 18;
        int[] result = twoSum_1.twoSum(nums, target);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println("\n");

        int[] ints = twoSum_1.twoSum2(nums, target);
        System.out.println(ints[0]);
        System.out.println(ints[1]);

    }
}
