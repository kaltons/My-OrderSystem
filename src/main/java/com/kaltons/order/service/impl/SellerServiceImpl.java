package com.kaltons.order.service.impl;

import com.kaltons.order.entity.SellerInfo;
import com.kaltons.order.repository.SellerInfoRepository;
import com.kaltons.order.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SellerInfo 业务操作接口类的实现类
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/29 - 1:32
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
