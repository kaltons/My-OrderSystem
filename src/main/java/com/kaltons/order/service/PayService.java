package com.kaltons.order.service;

import com.kaltons.order.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * 订单支付业务接口
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/24 - 14:30
 */
public interface PayService {

    /**
     * 发起支付
     * @param orderDTO
     */
    PayResponse create(OrderDTO orderDTO);

    /**
     * 异步回调
     * @param notifyData
     * @return
     */
    PayResponse notify(String notifyData);

    /**
     * 退款
     * @param orderDTO
     * @return
     */
    RefundResponse refund(OrderDTO orderDTO);

}


