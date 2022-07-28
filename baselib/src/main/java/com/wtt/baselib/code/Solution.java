package com.wtt.baselib.code;

import java.util.HashMap;

/**
 * Created by Wangzhan on 2022/6/21
 * <p>
 * 示例 1:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
 *
 * @descr
 */
class Solution {


    /**
     * 滑动窗口法
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        int left = 0;//滑动窗口
        int index = 0;
        int max = 0;//用于记录最大不重复子串的长度
        HashMap<Character, Integer> map = new HashMap<>();
        int length = s.length();
        char[] chars = s.toCharArray();

        for (; index < length; index++) {
            char currChar = chars[index];
            if (map.containsKey(currChar)) {
                //如果包含 左指针右移 max保证每次匹配左指针保持最大值

//                1）当前字符包含在当前有效的子段中，如：abca，当我们遍历到第二个a，当前有效最长子段是 abc，我们又遍历到a，
//                那么此时更新 left 为 map.get(a)+1=1，当前有效子段更新为 bca；
//                2）当前字符不包含在当前最长有效子段中，如：abba，我们先添加a,b进map，此时left=0，我们再添加b，发现map中包含b，
//                而且b包含在最长有效子段中，就是1）的情况，我们更新 left=map.get(b)+1=2，此时子段更新为 b，而且map中仍然包含a，map.get(a)=0；
//                随后，我们遍历到a，发现a包含在map中，且map.get(a)=0，如果我们像1）一样处理，就会发现 left=map.get(a)+1=1，实际上，left此时
//                应该不变，left始终为2，子段变成 ba才对。
                left = Math.max(left, map.get(currChar)+1); //滑动左侧指针 确定最终的左侧边界 以 MAX为

                System.out.println("left:"+left);
            }
            //
            map.put(currChar, index);

            //保证得到的max是整个字符串中最大的长度
            //因为 例如 abcad  max = 3 or 2 最后一个不能覆盖前一个
            max = Math.max(max, index - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("不重复字符串最长:" + solution.lengthOfLongestSubstring("pwwkew"));
    }
}
