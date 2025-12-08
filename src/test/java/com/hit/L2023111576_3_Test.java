package com.hit;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.List;
import java.util.Arrays;
import static org.junit.Assert.*;

/**
 * 测试用例设计总体原则：
 * 1. 等价类划分原则：
 *    - 有效等价类：存在整除关系的数组、单个元素数组、完全整除链数组
 *    - 无效等价类：空数组（已在方法内处理）、无整除关系的质数数组
 * 2. 边界值分析原则：
 *    - 最小边界：单个元素数组
 *    - 最大边界：完全整除链（如1,2,4,8）
 *    - 特殊边界：无序输入、质数集合
 * 3. 功能覆盖原则：
 *    - 验证基本整除功能
 *    - 验证多个解的情况
 *    - 验证排序不影响结果
 */
public class L2023111576_3_Test {

    /**
     * 测试目的：验证基本整除子集功能
     * 用到的测试用例：输入数组[1,2,3]，包含部分整除关系
     * 预期结果：返回包含2个元素的子集，且满足相互整除条件
     */
    @Test
    public void testBasicDivisibleSubset() {
        Solution3 solution = new Solution3();
        int[] nums = {1, 2, 3};
        List<Integer> result = solution.largestDivisibleSubset(nums);

        assertEquals("结果集应该包含2个元素", 2, result.size());
        assertTrue("结果集应该包含1", result.contains(1));
        assertTrue("结果集应该满足相互整除条件: " + result, isValidDivisibleSubset(result));
    }

    /**
     * 测试目的：验证完全整除链的情况
     * 用到的测试用例：输入数组[1,2,4,8]，形成完全整除链
     * 预期结果：返回完整的整除链[1,2,4,8]
     */
    @Test
    public void testCompleteDivisibleChain() {
        Solution3 solution = new Solution3();
        int[] nums = {1, 2, 4, 8};
        List<Integer> result = solution.largestDivisibleSubset(nums);

        // 修改为错误的预期结果
        List<Integer> expected = Arrays.asList(1, 2, 4);  // ❌ 故意少了 8
        assertEquals("应该返回[1,2,4,8]，但故意改为错误", expected, result);
    }

    /**
     * 测试目的：验证单个元素的边界情况
     * 用到的测试用例：输入数组[5]，只有一个元素
     * 预期结果：返回包含该元素的单元素列表
     */
    @Test
    public void testSingleElementBoundary() {
        Solution3 solution = new Solution3();
        int[] nums = {5};
        List<Integer> result = solution.largestDivisibleSubset(nums);

        List<Integer> expected = Arrays.asList(5);
        assertEquals("应该返回[5]", expected, result);
    }

    /**
     * 测试目的：验证质数数组的特殊情况
     * 用到的测试用例：输入数组[2,3,5,7,11]，元素间无整除关系
     * 预期结果：返回任意单个元素（最大整除子集大小为1）
     */
    @Test
    public void testPrimeNumbersSpecialCase() {
        Solution3 solution = new Solution3();
        int[] nums = {2, 3, 5, 7, 11};
        List<Integer> result = solution.largestDivisibleSubset(nums);

        assertEquals("质数数组的最大整除子集大小应该为1", 1, result.size());
    }

    /**
     * 测试目的：验证输入排序不影响结果
     * 用到的测试用例：输入数组[4,8,2,1]，无序但存在整除关系
     * 预期结果：返回有序的整除子集[1,2,4,8]
     */
    @Test
    public void testUnsortedInputOrderIndependence() {
        Solution3 solution = new Solution3();
        int[] nums = {4, 8, 2, 1};
        List<Integer> result = solution.largestDivisibleSubset(nums);

        List<Integer> expected = Arrays.asList(1, 2, 4, 8);
        assertEquals("无论输入顺序如何，都应该返回相同的整除子集", expected, result);
    }

    /**
     * 测试空数组边界情况
     */
    @Test
    public void testEmptyArray() {
        Solution3 solution = new Solution3();
        List<Integer> result = solution.largestDivisibleSubset(new int[]{});
        assertTrue("空数组应该返回空列表", result.isEmpty());
    }

    /**
     * 辅助方法：验证结果集是否满足相互整除条件
     */
    private static boolean isValidDivisibleSubset(List<Integer> subset) {
        for (int i = 0; i < subset.size(); i++) {
            for (int j = i + 1; j < subset.size(); j++) {
                if (subset.get(j) % subset.get(i) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 如果需要保留原来的 main 方法作为备选（可选）
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(L2023111576_3_Test.class);

        for (Failure failure : result.getFailures()) {
            System.out.println("❌ 测试失败: " + failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("✅ 所有 " + result.getRunCount() + " 个测试通过！");
        } else {
            System.out.println("❌ " + result.getFailureCount() + " 个测试失败");
        }
    }
}