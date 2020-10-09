package com.kaltons.order.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情信息
 *
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 11:41
 */
@Entity
@Data
public class OrderDetail {

    /**
     * 订单详情编号
     */
    @Id
    private String detailId;

    /**
     * 订单编号
     */
    private String orderId;

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
     * 商品库存
     */
    private int productQuantity;

    /**
     * 商品小图
     */
    private String productIcon;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
