package com.kaltons.order.constant;

/**
 * Redis常量
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/29 - 15:00
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE = 7200; //2小时
}
