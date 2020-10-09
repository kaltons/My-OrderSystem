package com.kaltons.order.service;

import com.kaltons.order.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Order 业务操作接口类
 *
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 14:15
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查看单个订单信息
     * @param orderId
     * @return
     */
    OrderDTO findOne(String orderId);

    /**
     * 根据用户微信openid查找订单列表
     * @param opendId
     * @param pageable
     * @return
     */
    Page<OrderDTO> findUserOeders(String opendId, Pageable pageable);

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 订单完成
     * @param orderDTO
     * @return
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    OrderDTO payOrder(OrderDTO orderDTO);

    /**
     * 查找全部订单
     * @param pageable
     * @return
     */
    Page<OrderDTO> findAllOrders(Pageable pageable);


}
