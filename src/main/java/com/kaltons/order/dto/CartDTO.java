package com.kaltons.order.dto;

import lombok.Data;

/**
 * TODO
 * 购物车数据传输对象
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 17:12
 */
@Data
public class CartDTO {

    /**
     * 商品编号
     */
    private String productId;

    /**
     * 购买数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
