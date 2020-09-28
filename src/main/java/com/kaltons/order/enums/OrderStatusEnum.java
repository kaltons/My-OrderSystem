package com.kaltons.order.enums;

import lombok.Getter;

/**
 * TODO
 * 订单状态信息
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 11:27
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 消息
     */
    private final String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
