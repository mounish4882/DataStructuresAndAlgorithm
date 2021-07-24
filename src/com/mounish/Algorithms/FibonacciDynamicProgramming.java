package com.mounish.Algorithms;

import java.util.HashMap;
import java.util.Map;

public class FibonacciDynamicProgramming {
    private static final Map<Integer,Long> result = new HashMap<>();

    public static Long fib(int n) {
        if(n == 0)
            return 0L;
        if(n <= 2)
            return 1L;
        if(result.get(n) != null) {
            return result.get(n);
        } else {
            Long v = fib(n - 1) + fib(n - 2);
            result.put(n,v);
            return v;
        }
    }
}
