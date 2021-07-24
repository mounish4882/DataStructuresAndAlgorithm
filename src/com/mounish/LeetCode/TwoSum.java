package com.mounish.LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSumHashing(int[] numbers, int target) {
        //Best Solution to find the pair result
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0;i < numbers.length; i++) {
            if(map.containsKey(target - numbers[i])){
                result[0] = map.get(target - numbers[i]);
                result[1] = i;
                return result;
            }
            map.put(numbers[i], i);
        }
        throw new IllegalArgumentException("No two sum solutions");
    }

    public static int[] twoSumSorting(int[] numbers, int target) {
        //Best Solution to Check if pair exists(Coz of Sorting position is changed)
        int[] result = new int[2];
        Arrays.parallelSort(numbers);
        for (int i = 0; i < numbers.length; i++) {
            int resultIndex = Arrays.binarySearch(numbers,(target - numbers[i]));
            if(resultIndex >= 0) {
                if(resultIndex != i || (i > 0 && numbers[i-1] == numbers[i])
                        || (i < numbers.length - 1 && numbers[i+1] == numbers[i])) {
                    result[0] = resultIndex;
                    result[1] = i;
                    return result;
                }
            }
        }
        throw new IllegalArgumentException("No two sum solutions");
    }
}
