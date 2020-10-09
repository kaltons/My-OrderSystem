package com.kaltons.order.service.impl;

import com.kaltons.order.dto.OrderDTO;
import com.kaltons.order.service.OrderService;
import com.kaltons.order.service.PushMsgService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 微信模板消息推送 —— 业务操作测试类
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/29 - 22:12
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PushMsgServiceImplTest {

    @Autowired
    private PushMsgService pushMsgService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1601267999195630352");
        pushMsgService.orderStatus(orderDTO);
    }
}