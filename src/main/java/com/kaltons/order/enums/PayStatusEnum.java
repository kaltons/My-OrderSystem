package com.kaltons.order.enums;

import lombok.Getter;

/**
 * TODO
 * 订单支付状态
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 11:31
 */
@Getter
public enum  PayStatusEnum implements CodeEnum{

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),;
    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 消息
     */
    private final String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
