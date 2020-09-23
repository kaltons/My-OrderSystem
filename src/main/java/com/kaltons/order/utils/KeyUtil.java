package com.kaltons.order.utils;

import java.util.Random;

/**
 * TODO
 * 生成一个唯一的Key，用于订单号，订单详情编号
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 16:33
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
