package com.kaltons.order.service;

import com.kaltons.order.dto.OrderDTO;

/**
 * 封装校验买家微信openId是否一致 接口
 *
 * @author 衍方
 * @desc
 * @date 2020/9/23 - 2:31
 */
public interface BuyerService {

    /**
     * 查询单个订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid, String orderId);
}
