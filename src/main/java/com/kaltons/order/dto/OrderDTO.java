package com.kaltons.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kaltons.order.entity.OrderDetail;
import com.kaltons.order.enums.OrderStatusEnum;
import com.kaltons.order.enums.PayStatusEnum;
import com.kaltons.order.utils.EnumUtil;
import com.kaltons.order.utils.serializer.DateToLongSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * TODO
 * 订单数据传输对象
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 14:22
 */
@Data
public class OrderDTO {

    /**
     * 订单标号
     */
    @Id
    private String orderId;

    /**
     * 买家名称
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 微信openid
     */
    private String buyerOpenid;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态，默认新下单
     */
    private Integer orderStatus;

    /**
     * 支付状态，默认等待支付
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    /**
     * 订单详情列表
     */
    List<OrderDetail> orderDetailList;

    /**
     * 获取对应订单状态枚举值
     * @return
     */
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    /**
     * 获取对应支付状态枚举值
     * @return
     */
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
