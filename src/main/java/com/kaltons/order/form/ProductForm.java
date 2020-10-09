package com.kaltons.order.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 存储表单提交过来的商品模型
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/28 - 22:51
 */
@Data
public class ProductForm {

    /**
     * 商品编号
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Integer productStock;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 类目编号
     */
    private Integer categoryType;
}
