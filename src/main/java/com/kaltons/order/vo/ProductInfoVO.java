package com.kaltons.order.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * TODO
 * 返回给前端的商品详情视图对象
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 0:07
 */
@Data
public class ProductInfoVO {

    /**
     * 商品编号
     */
    @JsonProperty("id")
    private String productId;

    /**
     * 商品名称
     */
    @JsonProperty("name")
    private String productName;

    /**
     * 商品单价
     */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /**
     * 商品描述
     */
    @JsonProperty("description")
    private String productDescription;

    /**
     * 商品小图
     */
    @JsonProperty("icon")
    private String productIcon;

}
