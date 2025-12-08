package com.hit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution3 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;  // 修复1：正确的数组长度
        if (len == 0) return new ArrayList<>();
        Arrays.sort(nums);

        // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxValIndex = 0;  // 修复2：记录最大值的索引

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {  // 修复3：内层循环从0开始
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxValIndex = i;  // 修复4：记录索引而不是值
            }
        }

        // 第 2 步：倒推获得最大子集
        List<Integer> res = new ArrayList<>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }

        int currentSize = maxSize;
        int currentVal = nums[maxValIndex];

        for (int i = maxValIndex; i >= 0; i--) {
            if (dp[i] == currentSize && currentVal % nums[i] == 0) {
                res.add(0, nums[i]);  // 修复5：保持顺序
                currentVal = nums[i];
                currentSize--;
            }
        }
        return res;
    }
}