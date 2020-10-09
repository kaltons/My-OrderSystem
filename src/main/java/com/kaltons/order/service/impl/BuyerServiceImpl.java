package com.kaltons.order.service.impl;

import com.kaltons.order.dto.OrderDTO;
import com.kaltons.order.enums.ResultEnum;
import com.kaltons.order.exception.SellException;
import com.kaltons.order.service.BuyerService;
import com.kaltons.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 封装校验买家微信openId是否一致 实现
 *
 * @author 衍方
 * @desc
 * @date 2020/9/23 - 2:34
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;


    /**
     * 查询单个订单
     * @param openid
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
