package com.epam.cache;

/**
 * @author Andrei_Yakushin
 * @since 9/13/2016 2:46 PM
 */
public class Task2A {
    private static long factorial(int i) {
        return i == 1
                ? 1L
                : factorial(i - 1) * i;
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
