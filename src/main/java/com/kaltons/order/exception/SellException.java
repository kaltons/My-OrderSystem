package com.kaltons.order.exception;

import com.kaltons.order.enums.ResultEnum;
import lombok.Getter;

/**
 * 系统相关错误异常
 *
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 16:05
 */
@Getter
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
