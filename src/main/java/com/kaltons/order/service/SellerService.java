package com.kaltons.order.service;

import com.kaltons.order.entity.SellerInfo;

/**
 * SellerInfo 业务操作接口类
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/29 - 1:31
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
