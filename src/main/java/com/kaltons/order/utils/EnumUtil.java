package com.kaltons.order.utils;

import com.kaltons.order.enums.CodeEnum;

/**
 * 通过Code值获取对应枚举的工具类
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/28 - 2:37
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
