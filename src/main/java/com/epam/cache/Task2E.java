package com.epam.cache;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.sync.RedisCommands;

/**
 * @author Andrei_Yakushin
 * @since 9/13/2016 4:20 PM
 */
public class Task2E {
    private static final RedisClient client = new RedisClient("localhost");
    private static final RedisCommands<String, String> memoCache = client.connect().sync();

    private static long factorial(int key) {
        if (key == 1) {
            return 1L;
        }
        String k = String.valueOf(key);
        String r = memoCache.get(k);
        if (r == null) {
            Long result = factorial(key - 1) * key;
            memoCache.set(k, String.valueOf(result));
            return result;
        } else {
            return Long.valueOf(r);
        }
    }

    public static void main(String[] args) throws InterruptedException {
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
