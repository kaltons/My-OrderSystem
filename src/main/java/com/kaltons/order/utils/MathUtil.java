package com.kaltons.order.utils;

/**
 * 支付金额精度校验工具
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/28 - 0:00
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较2个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        return result < MONEY_RANGE;
    }
}
