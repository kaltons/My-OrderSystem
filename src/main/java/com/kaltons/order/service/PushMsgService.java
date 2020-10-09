package com.kaltons.order.service;

import com.kaltons.order.dto.OrderDTO;

/**
 * 微信模板消息发送业务接口
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/29 - 21:10
 */
public interface PushMsgService {

    /**
     * 订单状态变更消息推送
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
