package com.epam.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.sleep;

/**
 * @author Andrei_Yakushin
 * @since 9/13/2016 3:54 PM
 */
public class Task2D {
    private static final Map<Integer, Long> memoCache = new ConcurrentHashMap<>();
    private static final Map<Integer, Integer> count = new ConcurrentHashMap<>();
    private static final Map<Integer, Long> leaveTime = new ConcurrentHashMap<>();

    private static final int LIMIT = 8;
    private static final long LIFE_DURATION = 100;

    private static long factorial(int key) {
        if (key == 1) {
            return 1L;
        }
        Long result = memoCache.get(key);
        if (result != null) {
            Long time = leaveTime.get(key);
            if (time < System.currentTimeMillis()) {
                memoCache.remove(key);
                count.remove(key);
                leaveTime.remove(key);
                result = null;
            }
        }
        if (result == null) {
            if (memoCache.size() == LIMIT) {
                Integer toRemove = count.entrySet().stream()
                        .min((entry1, entry2) -> Integer.compare(entry1.getValue(), entry2.getValue()))
                        .map(Map.Entry::getKey)
                        .orElseThrow(IllegalStateException::new);
                memoCache.remove(toRemove);
                count.remove(toRemove);
                leaveTime.remove(toRemove);
            }
            memoCache.put(key, result = factorial(key - 1) * key);
            leaveTime.put(key, System.currentTimeMillis() + LIFE_DURATION);
            count.put(key, 1);
        } else {
            count.put(key, count.get(key) + 1);
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                try {
                    sleep(LIFE_DURATION);
                } catch (InterruptedException e) {
                    break;
                }
                long time = System.currentTimeMillis();
                for (Iterator<Map.Entry<Integer, Long>> it = leaveTime.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<Integer, Long> entry = it.next();
                    if (entry.getValue() < time) {
                        memoCache.remove(entry.getKey());
                        count.remove(entry.getKey());
                        it.remove();
                    }
                }
            }
        }) {{
            setDaemon(true);
        }}.start();

        System.out.println(factorial(4));
        System.out.println(factorial(4));
        System.out.println(factorial(5));
        System.out.println(factorial(5));
        System.out.println(factorial(6));
        System.out.println(factorial(7));
        System.out.println(factorial(8));
        System.out.println(factorial(9));
        System.out.println(factorial(10));

        System.out.println(memoCache.size());
        sleep(1000);
        System.out.println(memoCache.size());
    }
}
