package com.kaltons.order.enums;

import lombok.Getter;

/**
 * TODO
 * 商品上架状态
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 22:42
 */
@Getter
public enum ProductStatusEnum {


    UP(0,"上架"),
    DOWN(0,"下架");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
