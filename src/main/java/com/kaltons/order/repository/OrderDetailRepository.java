package com.kaltons.order.repository;

import com.kaltons.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * TODO
 * 订单详情 —— 持久化接口类
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 11:53
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 根据订单号查找订单详情信息
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
