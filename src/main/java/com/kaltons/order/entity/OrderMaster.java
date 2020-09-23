package com.kaltons.order.entity;

import com.kaltons.order.enums.OrderStatusEnum;
import com.kaltons.order.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO
 * 订单主表
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 11:19
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态，默认等待支付
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
