package com.day.authority.common.util;

/**
 * This tool class does not make non-null judgment on the comparison value. If there is a null value passed by a son of a bitch, wait to throw the null pointer exception!
 *
 * @author DayXhYao
 * @date 2023/3/12 18:00
 */
public class CompareUtil {


    /**
     * a > b true
     * a < b false
     * a == b false
     *
     * @param a   比较值a
     * @param b   比较值b
     * @param <T> 泛型
     * @return a > b
     */
    public static <T> boolean more(Comparable<T> a, T b) throws NullPointerException {
        return a.compareTo(b) > 0;
    }


    /**
     * a > b false
     * a < b true
     * a == b false
     *
     * @param a   比较值a
     * @param b   比较值b
     * @param <T> 泛型
     * @return a < b
     */
    public static <T> boolean less(Comparable<T> a, T b) throws NullPointerException {
        return a.compareTo(b) < 0;
    }


    /**
     * a > b false
     * a < b false
     * a == b true
     *
     * @param a   比较值a
     * @param b   比较值b
     * @param <T> 泛型
     * @return a == b
     */
    public static <T> boolean equals(Comparable<T> a, T b) throws NullPointerException {
        return a.compareTo(b) == 0;
    }


    /**
     * a > b true
     * a < b false
     * a == b true
     *
     * @param a   比较值a
     * @param b   比较值b
     * @param <T> 泛型
     * @return a >= b
     */
    public static <T> boolean moreAndEquals(Comparable<T> a, T b) throws NullPointerException {
        return more(a, b) || equals(a, b);
    }


    /**
     * a > b false
     * a < b true
     * a == b true
     *
     * @param a   比较值a
     * @param b   比较值b
     * @param <T> 泛型
     * @return a <= b
     */
    public static <T> boolean lessAndEquals(Comparable<T> a, T b) throws NullPointerException {
        return less(a, b) || equals(a, b);
    }


    /**
     * a > b true
     * a < b true
     * a == b false
     *
     * @param a   比较值a
     * @param b   比较值b
     * @param <T> 泛型
     * @return a <> b
     */
    public static <T> boolean notEquals(Comparable<T> a, T b) throws NullPointerException {
        return !equals(a, b);
    }


}
