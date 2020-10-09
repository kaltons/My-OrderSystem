package com.kaltons.order.repository;

import com.kaltons.order.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单主表 —— 持久化接口类
 *
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 11:45
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    /**
     * 通过用户微信openid查找订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
