package com.kaltons.order.repository;

import com.kaltons.order.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商家后台管理员 —— 持久化接口类
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/29 - 1:22
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    /**
     * 通过openid查找商家后台管理员信息
     * @param openid
     * @return
     */
    SellerInfo findByOpenid(String openid);
}
