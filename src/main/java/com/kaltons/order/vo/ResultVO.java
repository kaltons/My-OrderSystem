package com.kaltons.order.vo;

import lombok.Data;

/**
 * 封装返回前端的通用模板对象
 *
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 23:33
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体封装内容
     */
    private T data;
}
