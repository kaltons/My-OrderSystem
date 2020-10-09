package com.kaltons.order.repository;

import com.kaltons.order.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * TODO
 * OrderMaster —— 持久化接口测试类
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 11:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "1008611";

    /**
     * 简单添加测试
     */
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("衍方");
        orderMaster.setBuyerPhone("14725896300");
        orderMaster.setBuyerAddress("福建省武夷山上大红袍母树上的鸟巢");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    /**
     * 通过用户微信openId查询订单
     */
    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0,2);

        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID,request);

        Assert.assertNotEquals(0,result.getTotalElements());

    }
}
