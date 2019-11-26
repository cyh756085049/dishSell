package com.edu.cn.dishsell.utils;

/**
 * @Author: cyh
 * @Date: 2019-11-20 09:25
 * @Description: 比较订单金额
 **/
public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;

    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}
