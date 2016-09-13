package com.epam.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrei_Yakushin
 * @since 9/13/2016 2:53 PM
 */
public class Task2B {
    private static final Map<Integer, Long> memoCache = new HashMap<>();

    private static long factorial(int i) {
        if (i == 1) {
            return 1L;
        }
        Long result = memoCache.get(i);
        if (result == null) {
            memoCache.put(i, result = factorial(i - 1) * i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(factorial(4));
        System.out.println(factorial(5));
        System.out.println(factorial(6));
        System.out.println(factorial(7));
        System.out.println(factorial(8));
        System.out.println(factorial(9));
        System.out.println(factorial(10));
    }
}
