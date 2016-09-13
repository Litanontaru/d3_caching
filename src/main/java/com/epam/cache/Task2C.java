package com.epam.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrei_Yakushin
 * @since 9/13/2016 2:55 PM
 */
public class Task2C {
    private static final Map<Integer, Long> memoCache = new HashMap<>();
    private static final Map<Integer, Integer> count = new HashMap<>();
    private static final int LIMIT = 8;

    private static long factorial(int i) {
        if (i == 1) {
            return 1L;
        }
        Long result = memoCache.get(i);
        if (result == null) {
            if (memoCache.size() == LIMIT) {
                Integer key = count.entrySet().stream()
                        .min((entry1, entry2) -> Integer.compare(entry1.getValue(), entry2.getValue()))
                        .map(Map.Entry::getKey)
                        .orElseThrow(IllegalStateException::new);
                memoCache.remove(key);
                count.remove(key);
            }
            memoCache.put(i, result = factorial(i - 1) * i);
            count.put(i, 1);
        } else {
            count.put(i, count.get(i) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(factorial(4));
        System.out.println(factorial(4));
        System.out.println(factorial(5));
        System.out.println(factorial(5));
        System.out.println(factorial(6));
        System.out.println(factorial(7));
        System.out.println(factorial(8));
        System.out.println(factorial(9));
        System.out.println(factorial(10));
    }
}
