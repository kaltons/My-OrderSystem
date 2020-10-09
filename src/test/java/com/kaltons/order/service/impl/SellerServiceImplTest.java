package com.kaltons.order.service.impl;

import com.kaltons.order.entity.SellerInfo;
import com.kaltons.order.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SellerInfo —— 业务操作测试类
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/29 - 1:34
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerServiceImplTest {

    private static final String openid = "kalton";

    @Autowired
    private SellerService sellerService;

    @Test
    public void findSellerInfoByOpenid() {

        SellerInfo result = sellerService.findSellerInfoByOpenid(openid);
        Assert.assertEquals(openid, result.getOpenid());
    }
}